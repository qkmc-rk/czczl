package cn.edu.sicau.czczl.service.impl;//                            _ooOoo_
//                           o8888888o
//                           88" . "88
//                           (| -_- |)
//                           O\  =  /O
//                        ____/`---'\____
//                      .'  \\|     |//  `.
//                     /  \\|||  :  |||//  \
//                    /  _||||| -:- |||||-  \
//                    |   | \\\  -  /// |   |
//                    | \_|  ''\---/''  |   |
//                    \  .-\__  `-`  ___/-. /
//                  ___`. .'  /--.--\  `. . __
//               ."" '<  `.___\_<|>_/___.'  >'"".
//              | | :  `- \`.;`\ _ /`;.`/ - ` : | |
//              \  \ `-.   \_ __\ /__ _/   .-` /  /
//         ======`-.____`-.___\_____/___.-`____.-'======
//                            `=---='
//        ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//                      Buddha Bless, No Bug !

import cn.edu.sicau.czczl.entity.User;
import cn.edu.sicau.czczl.repository.UserRepository;
import cn.edu.sicau.czczl.service.UserService;
import cn.edu.sicau.czczl.service.redis.RedisService;
import cn.edu.sicau.czczl.util.Constant;
import cn.edu.sicau.czczl.util.FieldValidator;
import cn.edu.sicau.czczl.util.MD5Util;
import cn.edu.sicau.czczl.vo.BindUserInfoVO;
import cn.edu.sicau.czczl.vo.ResponseEntity;
import cn.edu.sicau.czczl.vo.UserScoreVO;
import cn.edu.sicau.czczl.vo.WxServerResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

/**
 * TODO
 *
 * @author qkmc
 * @version 1.0
 * @date 2021-06-09 12:45
 */
@Service
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Value("${weixin.secret}")
    private String SECRET;
    @Value("${weixin.appid}")
    private String APPID;
    @Value("${weixin.api}")
    private String API;

    @Value("${weixin.api.code_start}")
    private Integer code_start;
    @Value("${weixin.api.code_end}")
    private Integer code_end;

    @Value("${weixin.api.secret_start}")
    private Integer secret_start;
    @Value("${weixin.api.secret_end}")
    private Integer secret_end;

    @Value("${weixin.api.appid_start}")
    private Integer appid_start;
    @Value("${weixin.api.appid_end}")
    private Integer appid_end;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisService redisService;

    @Override
    public User findUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()){
            return optionalUser.get();
        }
        return null;
    }

    @Override
    public Integer login(String code) {
        WxServerResult wxServerResult = requestWxServerWithCode(code);
        Integer errCode = wxServerResult.getErrcode();
        if (null != errCode) {
            if (errCode.equals(Constant.WX_ERROR_CODE)){ return Constant.LOGIN_CODE_ERROR;}
            if (errCode.equals(Constant.WX_USED_CODE)){ return Constant.LOGIN_CODE_USED;}
        }
        //???????????????????????????????????????session_key???openid
        User user = userRepository.findUserByOpenid(wxServerResult.getOpenid());
        if (null == user){
            // ??????????????????, ??????????????????
            User newUser = new User();
            newUser.setOpenid(wxServerResult.getOpenid());
            newUser.setScore(60);
            newUser.setAddScore(60);
            newUser.setStep(0);
            newUser.setIsBind(false);
            newUser.setIsDelete(false);
            user = userRepository.save(newUser);
            if (null == user){
                return Constant.LOGIN_NO_USER;
            }
        }
        //?????????????????????token
        String token = MD5Util.md5(new Date().toString());
        if (redisService.refreshToken((int)user.getId(), token, 15)) {
            return (int)user.getId();
        }
        return Constant.LOGIN_SERVER_ERROR;
    }

    @Override
    public cn.edu.sicau.czczl.vo.ResponseEntity bindUserInfo(String token, BindUserInfoVO bindUserInfoVO) {

        ResponseEntity responseEntity = new ResponseEntity();
        String userid = redisService.readDataFromRedis(token);
        User user = userRepository.findById(Long.parseLong(userid)).orElse(null);
        // ?????????????????????
        if(null == user || user.getIsBind()){
            responseEntity.error(Constant.LOGIN_NO_USER, "????????????, ????????????????????????,???????????????????????????,???????????????", null);
            return  responseEntity;
        }
        // ??????????????????????????????
        if (new FieldValidator<BindUserInfoVO>()
                .hasNullOrEmptyField(bindUserInfoVO)){
            responseEntity.error(Constant.FORM_NULL_EMPTY_VALUE, "?????????????????????,?????????: " + bindUserInfoVO.toString(), null);
            return responseEntity;
        }
        // ????????????
        // ?????????????????????????????? ??
        user = new FieldValidator<BindUserInfoVO>().updateAWithB(user, bindUserInfoVO);
        user.setIsBind(true);
        user = userRepository.saveAndFlush(user);
        responseEntity.success(Constant.SUCCESS_CODE, "????????????", user);
        return responseEntity;
    }

    @Override
    public ResponseEntity getUserInfo(String token) {

        Long userid = Long.parseLong(redisService.readDataFromRedis(token));
        ResponseEntity responseEntity = new ResponseEntity();
        String serverUserId = redisService.readDataFromRedis(token);
        if (!(("" + userid).equals(serverUserId))){
            responseEntity.error(Constant.AUTH_ROLE_ERROR, "?????????????????????", null);
        }
        User user = userRepository.findById(userid).orElse(null);
        if (null == user){
            responseEntity.error(Constant.FAILURE_CODE, "????????????????????????", null);
        }
        responseEntity.success(Constant.SUCCESS_CODE, "????????????", user);
        return responseEntity;
    }

    @Override
    public ResponseEntity getUserScore(String token) {
        ResponseEntity responseEntity = getUserInfo(token);
        UserScoreVO userScoreVO = new FieldValidator<User>().updateAWithB(new UserScoreVO(), (User) responseEntity.getData());
        responseEntity.setData(userScoreVO);

        return responseEntity;
    }

    @Override
    public ResponseEntity getUserStep(String token) {
        ResponseEntity responseEntity = new ResponseEntity();
        Long userid = Long.parseLong(redisService.readDataFromRedis(token));
        User user = userRepository.findById(userid).orElse(null);
        assert user != null;
        responseEntity.setData(user.getStep());
        return responseEntity;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.saveAndFlush(user);
    }


    /**
     * ????????????????????????????????????????????????code??????????????????????????????????????????WxServerResult????????????
     *
     * @param code
     * @return ??????????????????????????????
     */
    private WxServerResult requestWxServerWithCode(String code) {
        StringBuilder uri = new StringBuilder();
        uri.append(API);
        //??????CODE
        uri.replace(code_start, code_end, code);
        //??????SECRET
        uri.replace(secret_start, secret_end, SECRET);
        //??????APPID
        uri.replace(appid_start, appid_end, APPID);
        String uriStr = uri.toString();
        logger.info(uriStr);
        String strBody = getBody(restTemplate, uriStr);
        logger.info(strBody);
        //??????????????????????????????openId?????????????????????
        ObjectMapper objectMapper = new ObjectMapper();
        WxServerResult wxServerResult = null;
        try {
            wxServerResult = objectMapper.readValue(strBody, WxServerResult.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wxServerResult;
    }

    /**
     * ????????????????????????get??????????????????????????????
     * @param restTemplate
     * @param uri
     * @return ?????????????????????
     */
    private String getBody(RestTemplate restTemplate, String uri) {
        org.springframework.http.ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
        if (responseEntity.getStatusCodeValue() == 200) {
            return responseEntity.getBody();
        }
        return null;
    }
}

package cn.edu.sicau.czczl.service.redis;//                            _ooOoo_
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author qkmc
 * @version 1.0
 * @date 2021-06-09 12:51
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String readDataFromRedis(String key) {
        ValueOperations<String, String> valueOperations =  stringRedisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    @Override
    public void setDataToRedis(String key, String value, Integer min) {
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        valueOperations.set(key, value, min, TimeUnit.MINUTES);
    }

    @Override
    public Boolean refreshToken(Integer userId, String token, Integer period) {
        //要存入redis的数据有四条：
        //既可以通过userid找到token和session_key又可以反过来通过这两个找userId
        setDataToRedis("token" + userId, token, period);
        setDataToRedis(token, String.valueOf(userId), period);
        return Boolean.TRUE;
    }
}

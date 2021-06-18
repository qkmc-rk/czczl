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

/**
 * TODO
 *
 * @author qkmc
 * @version 1.0
 * @date 2021-06-09 12:48
 */
public interface RedisService {
    /**
     *一个从redis中根据key读取数据的方法
     * @param key redis Key
     * @return redis中的Value
     */
    String readDataFromRedis(String key);

    /**
     *  往redis中写入kv数据
     * @param key key
     * @param value value
     * @param min 分钟,过期时间
     */
    void setDataToRedis(String key, String value, Integer min);

    /**
     *  更新用户的token, 用户每次请求时对token进行刷新, 保证在访问过程中token不会失效过期
     * @param userId 用户ID
     * @param token 用户的token
     * @param period 刷新时常 单位为分钟
     * @return 刷新缓存是否成功
     */
    Boolean refreshToken(Integer userId, String token, Integer period);

}

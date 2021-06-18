package cn.edu.sicau.czczl.config;
//                            _ooOoo_
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

import cn.edu.sicau.czczl.vo.ResponseEntity;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

/**
 * 用于请求第三方API
 * 比如根据code获取openid
 * 该类用于配置RestTemplate
 * @author qkmc
 */
@Configuration
public class RestConfiguration {


    /**
     * 在@Bean标注的方法上, 如果只有一个参数,那么这
     * 个参数可以省略@Autowired不写,方法会自动从容器
     * 中获取这个参数的Bean --> RestTemplateBuilder
     * @param builder RestTemplateBuilder
     * @return RestTemplate
     * @see RestTemplate
     * @see RestTemplateBuilder
     */
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        RestTemplate restTemplate = builder.build();
        restTemplate.getMessageConverters().set(1,new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplate;
    }


    @Bean("responseEntity")
    @Scope("request")
    public ResponseEntity responseEntity(){
        //注入responseEntity Bean 似乎没什么用
        return new ResponseEntity();
    }
}

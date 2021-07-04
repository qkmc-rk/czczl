package cn.edu.sicau.czczl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;
import java.util.Set;

/**
 * @author qkmc
 */
@EnableSwagger2
@EnableTransactionManagement
@SpringBootApplication
public class CzczlApplication {
	public static void main(String[] args) {
		SpringApplication.run(CzczlApplication.class, args);
	}
}

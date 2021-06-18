package cn.edu.sicau.czczl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;
import java.util.Set;

/**
 * @author qkmc
 */
@EnableSwagger2
@SpringBootApplication
public class CzczlApplication {
	public static void main(String[] args) {
		SpringApplication.run(CzczlApplication.class, args);
	}
}

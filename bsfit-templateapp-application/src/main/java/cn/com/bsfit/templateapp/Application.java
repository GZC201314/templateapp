package cn.com.bsfit.templateapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 服务的启动类
 *
 * @Author: yujl
 * @Date: 2023/4/10 15:32
 */
@SpringBootApplication(scanBasePackages = {"cn.com.bsfit"}, exclude = {SecurityAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class})
@EnableFeignClients(basePackages = {"cn.com.bsfit"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}

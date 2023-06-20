package cn.com.bsfit.templateapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.nio.charset.StandardCharsets;

/**
 * 服务的启动类
 *
 * @Author: yujl
 * @Date: 2023/4/10 15:32
 */
@SpringBootApplication(scanBasePackages = {"cn.com.bsfit"}, exclude = {SecurityAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class})
@EnableFeignClients(basePackages = {"cn.com.bsfit"})
@MapperScan("cn.com.bsfit.templateapp.service.mapper")
public class Application {
    public static void main(String[] args) {
        System.setProperty("file.encoding", StandardCharsets.UTF_8.name());
        SpringApplication.run(Application.class);
    }
}

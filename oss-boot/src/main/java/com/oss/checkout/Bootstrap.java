package com.oss.checkout;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = "com")
@EnableSwagger2
public class Bootstrap
{
    public static void main( String[] args )
    {
        SpringApplication.run(Bootstrap.class, args);
    }
}

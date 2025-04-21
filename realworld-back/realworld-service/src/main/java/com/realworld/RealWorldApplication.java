package com.realworld;

import com.realworld.properties.ExcludePathProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author YYJYP
 */
@EnableConfigurationProperties({ExcludePathProperties.class})
@SpringBootApplication
public class RealWorldApplication {
    public static void main( String[] args ) {
        SpringApplication.run(RealWorldApplication.class, args);
    }
}

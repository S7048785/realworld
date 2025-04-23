package com.realworld;

import com.realworld.properties.ExcludePathProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author YYJYP
 */
@EnableCaching
@EnableConfigurationProperties({ExcludePathProperties.class})
@SpringBootApplication
public class RealWorldApplication {
    public static void main( String[] args ) {
        SpringApplication.run(RealWorldApplication.class, args);
    }
}

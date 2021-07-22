package com.minakov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Starter point
 */
@SpringBootApplication
public class SpringCourseraFinalLibraryApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(SpringCourseraFinalLibraryApplication.class, args);
    }
}

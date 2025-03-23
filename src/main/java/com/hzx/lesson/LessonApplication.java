package com.hzx.lesson;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 * @author zexiao.huang
 * @since 2025/3/23 下午3:49
 */
@SpringBootApplication
@MapperScan("com.hzx.lesson.mapper")
public class LessonApplication {

    public static void main(String[] args) {
        SpringApplication.run(LessonApplication.class, args);
    }

}

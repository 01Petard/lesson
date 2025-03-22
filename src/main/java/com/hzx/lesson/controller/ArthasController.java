package com.hzx.lesson.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zexiao.huang
 * @date 2025/3/22 15:20
 */
@RestController
public class ArthasController {

    @GetMapping("/arthas")
    public String test() {
        this.m1();
        this.m2();
        this.m3();
        return "hello, arthas!";
    }

    private void m1(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void m2(){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    private void m3(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}

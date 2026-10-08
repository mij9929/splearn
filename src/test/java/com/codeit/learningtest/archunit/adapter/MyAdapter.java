package com.codeit.learningtest.archunit.adapter;

import com.codeit.learningtest.archunit.application.MyService;

public class MyAdapter {
    MyService myService;

    void run() {
        myService = new MyService();
        System.out.println(myService);
    }
}

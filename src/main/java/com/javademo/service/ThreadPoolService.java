package com.javademo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;

/**
 * @author shuyi
 * @date 2020/5/7
 */
@Service
public class ThreadPoolService {
    @Autowired
    private ExecutorService executorService;

}

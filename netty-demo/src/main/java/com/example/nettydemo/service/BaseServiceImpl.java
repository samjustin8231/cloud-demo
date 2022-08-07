package com.example.nettydemo.service;

import org.springframework.stereotype.Service;

/**
 * @author sunyajun
 * @date 2022/8/8 上午12:09
 */
@Service
public class BaseServiceImpl implements BaseService {

    @Override
    public void test() {
        System.out.println("调用service服务");
    }
}


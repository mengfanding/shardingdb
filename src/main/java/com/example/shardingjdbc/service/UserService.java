package com.example.shardingjdbc.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.shardingjdbc.dao.UserMapper;
import com.example.shardingjdbc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper mapper;

    public List<User> queryAll() {
        return mapper.selectList(new QueryWrapper<>());
    }
}

package com.example.demo.domain.service;

import com.example.demo.domain.mapper.MenuMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MenuService {

    @Autowired
    private MenuMapper mapper;

}

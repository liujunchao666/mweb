package com.ibm.service.impl;

import com.ibm.service.interfaces.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{


    @Override
    public String getName() {
        return "tom";
    }

    @Override
    public String getAddress() {
        return "china";
    }
}

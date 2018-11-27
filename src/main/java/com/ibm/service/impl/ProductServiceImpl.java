package com.ibm.service.impl;

import com.ibm.service.interfaces.ProductService;
import com.ibm.service.interfaces.UserService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {


    @Override
    public String getSize() {
        return "12cm";
    }

    @Override
    public String getPrice() {
        return "888";
    }
}

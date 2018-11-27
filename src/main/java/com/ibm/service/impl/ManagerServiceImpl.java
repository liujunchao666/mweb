package com.ibm.service.impl;

import org.springframework.stereotype.Service;

import com.ibm.service.interfaces.ManagerService;

@Service
public class ManagerServiceImpl implements ManagerService {
	
	
	public String   get(){
		
		return "wuwu";
	}

	
	@Override
	public String say() {
		return "hello";
	}
	
	public static void main(String[] args) {
		
		String paytype="x";
		if (paytype==null||"B".equals(paytype)||"B|".equals(paytype)||paytype==""||paytype=="DEFAULT"||paytype.trim().equals("")) { 
			paytype=""; //兼容以前不传 是为了不签名。以前没有签名
			System.out.println("11");
		}
	}


	
}

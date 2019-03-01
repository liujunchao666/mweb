package com.ibm.service.action;

import com.ibm.service.interfaces.ManagerService;
import com.ibm.service.interfaces.ProductService;
import com.ibm.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ManagerAction {
	
	@Autowired
	private ManagerService managerService;
	@Autowired
	private UserService userService;
	@Autowired
    ProductService productService;

		@RequestMapping("/manager")
	@ResponseBody
	public String getItemList() {

		String result = managerService.say();
		String address = 	userService.getAddress();
		String price = 		productService.getPrice();
		System.out.print(address +""+price);
		return address +""+price;
	}

}

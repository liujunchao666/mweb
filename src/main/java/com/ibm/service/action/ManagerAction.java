package com.ibm.service.action;

import com.ibm.service.interfaces.ManagerService;
import com.ibm.service.interfaces.ProductService;
import com.ibm.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	public String getItemList(HttpServletRequest req, HttpServletResponse resp) {
		    System.out.println("getItemList "+req.hashCode());
		String result = managerService.say();
		String address = 	userService.getAddress();
		String price = 		productService.getPrice();
		System.out.print(address +""+price);
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return address +""+price;
	}

	@RequestMapping("/say")
	@ResponseBody
	public String say(HttpServletRequest req, HttpServletResponse resp) {
		String price = 		productService.getPrice();
        System.out.println("say "+req.hashCode());
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "say"+price;
	}




}

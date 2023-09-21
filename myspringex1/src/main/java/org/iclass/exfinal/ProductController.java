package org.iclass.exfinal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductController {
	
	private final ProductService service;
	
	@Autowired
	public ProductController(ProductService service) {
		System.out.println("111 - Controller");
		this.service = service;
	}
	
	public int product() {
		System.out.println("111 - 메소드");
		int result = service.product();
		
		return result;
	}
	
}

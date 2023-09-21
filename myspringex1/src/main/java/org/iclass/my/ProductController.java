package org.iclass.my;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductController {
	
	private ProductService service;
	
	public ProductController() {
		System.out.println("::::: Controller - 기본생성자");
	}
	
	@Autowired
	public void setService(ProductService service) {
		System.out.println("::::: Controller - setter");
		this.service = service;
	}

	public ProductController(ProductService service) {
		System.out.println("::::: Controller - 커스텀 생성자");
		this.service = service;
	}
	
	public void product() {
		System.out.println("::::: Controller - product()");
		service.product();
	}
	
}

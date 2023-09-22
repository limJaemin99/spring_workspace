package org.iclass.exfinal;

import org.springframework.stereotype.Component;

@Component
public class ProductService {
	
	private final ProductDao dao;
	
	
	public ProductService(ProductDao dao) {
		System.out.println("222 - Service");
		this.dao = dao;
	}
	
	public int product() {
		System.out.println("222 - 메소드");
		int result = dao.product();
		
		return result;
	}
	
}

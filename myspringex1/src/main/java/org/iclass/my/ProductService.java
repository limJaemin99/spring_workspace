package org.iclass.my;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductService {
	
	private ProductDao dao;
	
	public ProductService() {
		System.out.println("::::: service - 생성자");
	}
	
	@Autowired
	public void setDao(ProductDao dao) {
		System.out.println("::::: service - setter");
		this.dao = dao;
	}

	public ProductService(ProductDao dao) {
		System.out.println("::::: service - 커스텀 생성자");
		this.dao = dao;
	}
	
	public void product() {
		System.out.println("::::: service - product()");
		dao.product();
	}
	
}

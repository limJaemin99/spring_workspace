package org.iclass.exfinal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProductDao {
	
	private final int count;
	
	public ProductDao(@Value("111") int count) {
		System.out.println("333 - Dao");
		this.count = count;
	}
	
	public int product() {
		System.out.println("333 - 메소드");
		int result = this.count;
		
		return result;
	}
	
}

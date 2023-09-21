package org.iclass.my;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Setter;

@Setter
@Component
public class ProductDao {
	
	private int count;

	public ProductDao() {
		System.out.println("::::: DAO - 생성자");
	}

	@Value("111")
	public void setCount(int count) {
		System.out.println("::::: DAO - setter");
		this.count = count;
	}

//	public ProductDao(@Value("999") int count) {
//		System.out.println("::::: DAO - 커스텀 생성자");
//		this.count = count;
//	}
	
	public void product() {
		System.out.println("::::: DAO - product()");
		System.out.println("━━━━━ 수량 : "+count+"━━━━━");
	}
	
}

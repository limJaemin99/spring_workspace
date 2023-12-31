package org.iclass.di.setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Buy2Service {
	
	private Buy2Dao dao;
	
	public Buy2Service(Buy2Dao dao) {
		System.out.println("::: Buy2Service 커스텀 생성자 :::");
		this.dao = dao;
	}
	
	public Buy2Service() {
		System.out.println("::: Buy2Service 기본 생성자 :::");
	}
	
	@Autowired	//setter 주입에 필요한 애노테이션
	public void setDao(Buy2Dao dao) {
		System.out.println("::: Buy2Service 다오 setter :::");
		this.dao = dao;
	}
	
	public void buy() {
		System.out.println("--- Buy2Service buy() 메소드 ---");
		dao.buy();
	}

}

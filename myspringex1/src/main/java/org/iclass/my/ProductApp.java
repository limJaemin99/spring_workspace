package org.iclass.my;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductApp {
	public static void main(String[] args) {
		
		//ApplicationContext ▶ 스프링 컨테이너 (인터페이스)
		ApplicationContext context = new AnnotationConfigApplicationContext(ProductConfig.class);
		
		ProductDao dao = context.getBean(ProductDao.class);
		dao.product();
		System.out.println("----- 줄바꿈 -----");
		ProductService service = context.getBean(ProductService.class);
		service.product();
		log.info("[org.iclass 패키지의 로그 수준은 log4j.xml 에서 level 태그 값으로 설정한다]");
		
		((AbstractApplicationContext) context).close();
	}
}

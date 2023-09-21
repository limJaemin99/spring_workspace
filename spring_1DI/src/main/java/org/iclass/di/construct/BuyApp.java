package org.iclass.di.construct;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

//자동 주입을 확인하기 위한 main
public class BuyApp {
	public static void main(String[] args) {
		// context 라는 단어는 '서블릿 컨텍스트' , '스프링 컨텍스트' 와 같이 실행이 되는 환경을 나타내는 단어입니다.
			// ▶ 한글 뜻 : 문맥 , 환경
		// spring-context : bean 에 접근하기 위해 사용. (bean 은 spring 컨테이너에 저장)
		ApplicationContext context = new AnnotationConfigApplicationContext(BuyConfig.class);
		
		//AnnotationConfigApplicationContext : ApplicationContext 를 구성하는 설정파일은 어노테이션으로 한다.
		
		System.out.println("::::: 스프링 컨테이너에 저장된 bean 의 이름 확인 :::::");
		String[] beans = context.getBeanDefinitionNames();	//bean 의 이름들 가져오기
		for (String bean : beans) {	//bean 의 이름은 클래스 이름과 동일하나 [앞글자가 소문자]
			System.out.println(bean);
		}
		
		//빈 컨테이너에서 buy2Controller 빈을 가져오기 : 타입으로 가져오기 또는 이름으로 가져오기 (2가지 방법)
		System.out.println("Buy2Controller 빈 가져와서 buy() 메소드 실행하기 -----------------");
		Buy2Controller buy2Controller = context.getBean(Buy2Controller.class);	//타입으로 가져오기
		buy2Controller.buy();
		
		((AbstractApplicationContext) context).close();	//노란줄 없애기 위해 close 해줌
		//AnnotationConfigApplicationContext 의 부모 클래스 AbstractApplicationContext 로 캐스팅
	}
}

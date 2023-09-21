package org.iclass.exfinal;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class ProductApp {
	public static void main(String[] args) {
		
		ApplicationContext context = new AnnotationConfigApplicationContext(ProductConfig.class);
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━");
		
		ProductController controller = context.getBean(ProductController.class);
		System.out.println(controller.product());
		
		((AbstractApplicationContext) context).close();
	}
}

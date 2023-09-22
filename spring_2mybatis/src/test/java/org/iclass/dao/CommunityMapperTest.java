package org.iclass.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.iclass.config.MybatisConfig;
import org.iclass.dto.Community;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.slf4j.Slf4j;

@DisplayName("CommunityMapper 구현체 생성확인합니다.")
//spring의 test클래스 설정
@ExtendWith(SpringExtension.class)	//테스트 클래스 기능을 위한 어노테이션
//▼ MybatisConfig.java 에서 @Bean을 전부 지워야 사용 가능 ▼
//@ContextConfiguration(locations ="classpath:META-INF/spring/applicationContext.xml" )
@ContextConfiguration(classes = MybatisConfig.class)	//테스트 클래스에서 설정파일 가져오기
@Slf4j
class CommunityMapperTest {
	
	@Autowired	//테스트 코드에서는 필드 주입을 사용
	private ApplicationContext context;
	
	@Autowired
	private CommunityMapper dao;
	
	@DisplayName("context, dao bean 생성 확인합니다.")	//설명
	@Test
	//@Disabled	//테스트에서 제외할 때 사용하는 어노테이션
	void bean() {
		log.debug("mapper 인터페이스의 구현체 - {}",dao.getClass().getName());
		assertNotNull(context);
		assertNotNull(dao);
	}
	
	
	@DisplayName("list 로 가져온 개수와 count 함수 결과는 같아야 합니다.")
	@Test
	//@Disabled
	void list() {
		List<Community> list = dao.list();
		int size = list.size();
		int count = dao.count();
		log.info("select 전체 크기 : {} , select count : {}",size, count);
		
		assertEquals(count, size);
	}
	
	
	@DisplayName("insert 테스트")
	@Test
	void insert() {
		//Community vo = new Community(0,"글쓴이","제목","내용",0,null,"123.12.0.123",null);
		Community vo = Community.builder()
					.writer("글쓴이")
					.title("제목")
					.content("내용")
					.ip("123.123.0.123")
					.build();
		log.info("insert 전 idx 값 : {}",vo.getIdx());
		int result = dao.insert(vo);
		log.info("insert 결과 : {}",result);
		log.info("insert 후 idx 값 : {}",vo.getIdx());
		
		assertNotEquals(0,result);
		assertNotEquals(0, vo.getIdx());
	}
}

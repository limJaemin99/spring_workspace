package org.iclass.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import org.iclass.config.MybatisConfig;
import org.iclass.dto.PageRequestDTO;
import org.iclass.dto.TodoDto;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.slf4j.Slf4j;

@DisplayName("TodoMapper 의 CRUD 동작 테스트")
@ExtendWith(SpringExtension.class)	//테스트 클래스 기능을 위한 어노테이션
@ContextConfiguration(classes = MybatisConfig.class)	//테스트 클래스에서 설정파일 가져오기
@Slf4j
class TodoMapperTest {

	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private TodoMapper dao;
	
	@DisplayName("contest, dao bean 생성 확인")
	@Test
	@Disabled
	void bean() {
		log.debug("mapper 인터페이스의 구현체 : {}",dao.getClass().getName());
		assertNotNull(context);
		assertNotNull(dao);
	}
	
	
	@DisplayName("insert 를 여러 행 하기 - 페이징 테스트를 위한 데이터 추가임")
	@Test
	@Disabled
	void insertMany() {	//▶ i 는 1~19까지 실행됨
		IntStream.range(1, 20).forEach(i -> {
			TodoDto vo = TodoDto.builder()
					.title("제목 " + i)
					.dueDate(LocalDate.of(2023,10,3))
					.writer("글쓴이" + (char)('A'+i))
					.build();
			dao.insert(vo);
		});
		
	}
	
	
	@DisplayName("다양한 조건으로 검색")
	@Test
	void search() {
//		PageRequestDTO pageRequestDTO = PageRequestDTO.of(1, 100, new String[] {"t","w"}, "매퍼", false, null, null);
		PageRequestDTO pageRequestDTO = PageRequestDTO.of(1, 100, null, null, false,
											LocalDate.of(2023, 9, 20), LocalDate.of(2023, 10, 2));
		
		List<TodoDto> list = dao.selectPageList(pageRequestDTO);
		list.forEach(dto -> log.info("▶▶▶▶▶ search result : {}",dto));
		
		log.info("▶▶▶▶▶ total count : {}",dao.getCount(pageRequestDTO));
	}
	
	
	@DisplayName("insert 테스트")
	@Test
	@Disabled
	void insert() {
		TodoDto vo = TodoDto.builder()
					.title("제목")
					.dueDate(LocalDate.of(2023,10,01))
//					.dueDate(Date.valueOf("2023-10-01"))
					.writer("글쓴이")
					.build();
		log.info("dueDate : {}",vo.getDueDate());
		int result = dao.insert(vo);
		log.info("insert 결과 : {}",result);
		
		assertEquals(1, result);
	}
	
	
	@DisplayName("selectAll 테스트")
	@Test
	@Disabled
	void selectAll() {
		List<TodoDto> list = dao.selectAll();
		
		assertNotEquals(0, list.size());
	}
	
	
	@DisplayName("selectOne 테스트")
	@Test
	@Disabled
	void selectOne() {
		TodoDto dto = dao.selectOne(20111L);
		log.info("조회된 dto : {}",dto);
		
		assertNotNull(dto);
	}
	
	
	@DisplayName("getCount 테스트")
	@Test
	@Disabled
	void getCount() {
		PageRequestDTO dto = PageRequestDTO.of(1, 3);
		long result = dao.getCount(dto);
		log.info("getCount() 결과 : {}",result);
		
		assertNotEquals(0, result);
	}
	
	
	@DisplayName("selectPageList [MAP] 테스트")
	@Test
	@Disabled
	void selectPageListMap() {
		int count = 1;	//for-each문 Count
		
		Map<String, Integer> map = new HashMap<>();
		map.put("start",1);
		map.put("end",6);
		List<TodoDto> list = dao.selectPageList(map);
		
		for (TodoDto lists : list) {
			log.info("list {} : {}",count,lists.toString());
			count++;
		}
		
		assertNotEquals(0, list.size());
	}
	
	
	@DisplayName("selectPageList [Page] 테스트")
	@Test
	@Disabled
	void selectPageListPage() {
		int count = 1;	//for-each문 Count
		
		PageRequestDTO dto = PageRequestDTO.of(1, 6);
		List<TodoDto> list = dao.selectPageList(dto);

		for (TodoDto lists : list) {
			log.info("list {} : {}",count,lists.toString());
			count++;
		}
		
		assertNotEquals(0, list.size());
	}
	
}

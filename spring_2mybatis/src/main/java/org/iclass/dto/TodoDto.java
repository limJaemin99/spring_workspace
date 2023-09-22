package org.iclass.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoDto {
	
	private long tno;
	private String title;		//제목
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dueDate;	//할일 기한 날짜
//	private Date dueDate;			//▶ mybatis 에서 날짜 타입을 Date 가 아닌 LocalDate 가 적용되는지 확인 필요
									//▶ spring 의 다른 기능 사용을 위해 날짜타입 LocalDate 로 함.
	private String writer;		//작성
	private boolean finished;	//완료여부
}

/*
	//테이블 생성
	CREATE TABLE tbl_todo (
		tno NUMBER(6) PRIMARY KEY,
		title varchar2(100) NOT NULL,
		dueDate DATE NOT NULL,
		writer varchar2(50) NOT NULL,
		finished NUMBER(2) DEFAULT 0
	)
	
	//시퀀스 생성
	CREATE SEQUENCE todoSeq START WITH 20111;
*/

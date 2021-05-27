package com.bit;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.bit.domain.WebBoard;
import com.bit.presistence.WebBoardRepository;

import lombok.extern.java.Log;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Log
@Commit
public class WebBoardRepositoryTests {

	@Autowired
	WebBoardRepository repo;
	
	@Test
	public void insertBoardDummies() {
		IntStream.range(0, 300).forEach(i->{
			WebBoard board = new WebBoard();
			board.setTitle("Sample Board Title"+i);
			board.setContent("Content Sample..."+i+"of Board");
			board.setWriter("user0"+(i%10));
			//repo.save(board);
			
		});
	}
	
	@Test
	public void testList1() {
		//페이징 -> 10개씩 bno 기준 내림차순으로
		Pageable pageable = PageRequest.of(0, 10, Direction.DESC,"bno");
		
		Page<WebBoard> result = repo.findAll(repo.makePredicate(null, null),pageable);
		log.info("PAGE :"+result.getPageable());
		log.info("-------------------------------");
		//시작 번호 0
		log.info("PageNumber :"+result.getPageable().getPageNumber());
		//총 블럭의 개수 300/10
		log.info("TotalPages : "+result.getTotalPages());
		log.info(" "+result.getPageable());
		result.getContent().forEach(board->log.info(" "+board));
	}
	
	@Test
	public void testList2() {
		//페이징 -> 10개씩 bno 기준 내림차순으로
		Pageable pageable = PageRequest.of(0, 10, Direction.DESC,"bno");
		
		Page<WebBoard> result = repo.findAll(repo.makePredicate("t", "10"),pageable);
		log.info("PAGE :"+ result.getPageable());
		log.info("-------------------------------------");
		result.getContent().forEach(board-> log.info(""+board));
	}
}

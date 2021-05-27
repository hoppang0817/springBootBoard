package com.bit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bit.domain.WebBoard;
import com.bit.presistence.WebBoardRepository;
import com.bit.vo.PageMaker;
import com.bit.vo.PageVO;

import lombok.extern.java.Log;

@Controller
@RequestMapping("/boards/")
@Log
public class WebBoardController {

	@Autowired
	WebBoardRepository repo;
	
	@GetMapping("/list0")//templates/boards/list0.html
	public void list( 
		@PageableDefault(
				direction = Sort.Direction.DESC,sort="bno"				
				,size=10,page=0) Pageable page) {
		log.info("list() called");
	}
	
	@GetMapping("/list")//templates/boards/list.html
	public void list(PageVO vo,Model model) {
		Pageable page = vo.makePageable(0, "bno");
		Page<WebBoard> result = repo.findAll(repo.makePredicate(null, null), page);
		log.info(""+result);
		log.info(""+page);
		log.info(""+result.getTotalPages());
		model.addAttribute("result", new PageMaker(result));
	}
	
}

package com.bit.vo;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public class PageVO {

	
	private static final int DEFAULT_SIZE=10;
	private static final int DEFAULT_MAX_SIZE=50;
	
	private int page;
	private int size;
	
	private String type;
	private String keyword;
	
	public PageVO() {
		this.page=1;
		this.size=DEFAULT_SIZE;
	}

	public int getPage() {
		return page;
	}


	public int getSize() {
		return size;
	}


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public void setPage(int page) {
		//page가 0보다 작으면 1로 그것이아니며 page번호로
		this.page = page <= 0? 1: page;
	}
	
	public void setSize(int size) {
		this.size = size <DEFAULT_SIZE || size> DEFAULT_MAX_SIZE? DEFAULT_SIZE:size;
	}
	
	public Pageable makePageable(int direction,String...props) {
		Sort.Direction dir = direction == 0 ? Sort.Direction.DESC:
			Sort.Direction.ASC;
		return PageRequest.of(this.page-1, this.size,dir,props);
	}
	
}

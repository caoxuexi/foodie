package com.caostudy.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 
 * @Title: PagedGridResult.java
 * @Package com.caostudy.utils
 * @Description: 用来返回分页Grid的数据格式
 * Copyright: Copyright (c) 2019
 */
@Getter
@Setter
public class PagedGridResult {
	
	private int page;			// 当前页数
	private int total;			// 总页数	
	private long records;		// 总记录数
	private List<?> rows;		// 每行显示的内容
}

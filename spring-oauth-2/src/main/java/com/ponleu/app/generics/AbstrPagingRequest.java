package com.ponleu.app.generics;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AbstrPagingRequest<T extends Object> {
	/**
	 * Page number
	 * Start from 1
	 */
	private Integer page;
	
	/**
	 * Page size /page
	 * Default is 20
	 */
	private Integer pageSize = 20;
	
	/**
	 * Search parameter
	 */
	private T search;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public T getSearch() {
		return search;
	}

	public void setSearch(T search) {
		this.search = search;
	}

}

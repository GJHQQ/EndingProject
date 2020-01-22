package com.hqq.demo;

public class PageBean {
	private int currentpage;
	private int pagesize;
	private int pagecount;
	private int count;
	public int getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public int getPagecount() {
		return pagecount;
	}
	public void setPagecount(int pagecount) {
		this.pagecount = pagecount;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "PageBean [count=" + count + ", currentpage=" + currentpage
				+ ", pagecount=" + pagecount + ", pagesize=" + pagesize + "]";
	}
	
}

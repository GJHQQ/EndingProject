package com.hqq.demo;

public class Article {
	private String ar_id;
	private int ca_id;
	private String ar_number;
	private String ar_title;
	private String ar_image;
	private String ar_content;
	private String ar_recontent;
	private String ar_user;
	private String ar_time;
	private String ar_state;
	private int clicks;
	private int max;
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public String getAr_id() {
		return ar_id;
	}
	public void setAr_id(String arId) {
		ar_id = arId;
	}
	public int getCa_id() {
		return ca_id;
	}
	public void setCa_id(int caId) {
		ca_id = caId;
	}
	public String getAr_number() {
		return ar_number;
	}
	public void setAr_number(String arNumber) {
		ar_number = arNumber;
	}
	public String getAr_title() {
		return ar_title;
	}
	public void setAr_title(String arTitle) {
		ar_title = arTitle;
	}
	public String getAr_image() {
		return ar_image;
	}
	public void setAr_image(String arImage) {
		ar_image = arImage;
	}
	public String getAr_content() {
		return ar_content;
	}
	public void setAr_content(String arContent) {
		ar_content = arContent;
	}
	public String getAr_user() {
		return ar_user;
	}
	public void setAr_user(String arUser) {
		ar_user = arUser;
	}
	public String getAr_time() {
		return ar_time;
	}
	public void setAr_time(String arTime) {
		ar_time = arTime;
	}
	public String getAr_state() {
		return ar_state;
	}
	public void setAr_state(String arState) {
		ar_state = arState;
	}
	public int getClicks() {
		return clicks;
	}
	public void setClicks(int clicks) {
		this.clicks = clicks;
	}
	public String getAr_recontent() {
		return ar_recontent;
	}
	public void setAr_recontent(String arRecontent) {
		ar_recontent = arRecontent;
	}
	@Override
	public String toString() {
		return "Article [ar_content=" + ar_content + ", ar_id=" + ar_id
				+ ", ar_image=" + ar_image + ", ar_number=" + ar_number
				+ ", ar_state=" + ar_state + ", ar_time=" + ar_time
				+ ", ar_title=" + ar_title + ", ar_user=" + ar_user
				+ ", ca_id=" + ca_id + ", clicks=" + clicks + "]";
	}
	
}

package com.hqq.demo;

public class Catalog {
	private int ca_id;
	private String ca_name;
	private int ca_number;
	private String ca_state;
	private int max;
	public int getCa_id() {
		return ca_id;
	}
	public void setCa_id(int caId) {
		ca_id = caId;
	}
	public String getCa_name() {
		return ca_name;
	}
	public void setCa_name(String caName) {
		ca_name = caName;
	}
	public int getCa_number() {
		return ca_number;
	}
	public void setCa_number(int caNumber) {
		ca_number = caNumber;
	}
	public String getCa_state() {
		return ca_state;
	}
	public void setCa_state(String caState) {
		ca_state = caState;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	@Override
	public String toString() {
		return "Catalog [ca_id=" + ca_id + ", ca_name=" + ca_name
				+ ", ca_number=" + ca_number + ", ca_state=" + ca_state + "]";
	}
	
}

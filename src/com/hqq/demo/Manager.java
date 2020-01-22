package com.hqq.demo;

public class Manager {
	private int manager_id;
	private String manager_name;
	private String passwd;
	private String oncepasswd;
	private String mstate;

	public int getManager_id() {
		return manager_id;
	}
	public void setManager_id(int managerId) {
		manager_id = managerId;
	}
	public String getManager_name() {
		return manager_name;
	}
	public void setManager_name(String managerName) {
		manager_name = managerName;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getOncepasswd() {
		return oncepasswd;
	}
	public void setOncepasswd(String oncepasswd) {
		this.oncepasswd = oncepasswd;
	}
	public String getMstate() {
		return mstate;
	}
	public void setMstate(String mstate) {
		this.mstate = mstate;
	}
	@Override
	public String toString() {
		return "Manager [manager_id=" + manager_id + ", manager_name="
				+ manager_name + ", mstate=" + mstate + ", passwd=" + passwd
				+ "]";
	}
	
	
}

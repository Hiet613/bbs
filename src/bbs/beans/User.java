package bbs.beans;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String loginId;
	private String password;
	private String name;
	private int branch;
	private int division;
	private int isStopped;
	private Date insertDate;
	private Date updateDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBranch(){
		return branch;
	}

	public void setBranch(String branch){
		int i = Integer.parseInt(branch);
		this.branch = i;
	}

	public int getDivision() {
		return division;
	}

	public void setDivision(String division) {
		int i = Integer.parseInt(division);
		this.division = i;
	}

	public int getIsStopped(){
		return isStopped;
	}

	public void setIsStopped(String isStopped){
		int i = Integer.parseInt(isStopped);
		this.isStopped = i;
	}


	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}

package bbs.beans;

import java.io.Serializable;
import java.util.Date;

public class UserMessages implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String title;
	private String message;
	private String category;
	private int userId;
	private String name;
	private Date insertDate;
	private Date updateDate;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessages(){
		return message;
	}

	public void setMessages(String message){
		this.message = message;
	}

	public String getCategory(){
		return category;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public int getUserId(){
		return userId;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public Date getUpdateDate(){
		return updateDate;
	}

	public void setUpdateDate(Date updateDate){
		this.updateDate = updateDate;
	}

}

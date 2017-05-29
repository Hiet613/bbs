package bbs.beans;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private int user_id;
	private String title;
	private String message;
	private String category;
	private Date insertDate;
	private Date updateDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return user_id;
	}

	public void setUserId(int userId) {
		this.user_id = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

package bbs.beans;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private int messageId;
	private String comment;
	private int userId;
	private Date insertDate;
	private Date updateDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		int i = Integer.parseInt(messageId);
		this.messageId = i;
	}

		public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}


	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

package bbs.beans;

import java.io.Serializable;
import java.util.Date;

public class UsersMessagesComments implements Serializable {
	private static final long serialVersionUID = 1L;

	private int commentId;
	private int userId;
	private int messageId;
	private String name;
	private String comment;
	private int branch;
	private int division;
	private Date insertDate;
	private Date updateDate;


	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
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
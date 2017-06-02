package bbs.dao;

import static bbs.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bbs.beans.UsersMessagesComments;
import bbs.exception.SQLRuntimeException;

public class UsersMessagesCommentsDao {
//DBのuser_nessages ビューから値を取得するＤＡＯ
	public List<UsersMessagesComments> getUsersMessagesComments(Connection connection, int num){

		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM users_messages_comments ");
			sql.append("ORDER BY created_at ASC limit " + num);

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<UsersMessagesComments> ret = toUsersMessagesCommentsList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException (e) ;
		} finally {
			close(ps);
		}
	}

	private List<UsersMessagesComments> toUsersMessagesCommentsList(ResultSet rs) throws SQLException {

		List<UsersMessagesComments> ret = new ArrayList<UsersMessagesComments>();
		try{
			while (rs.next()){
				int commentId = rs.getInt("comment_id");
				int userId = rs.getInt("user_id");
				int messageId = rs.getInt("message_id");
				String name= rs.getString("name");
				String comment = rs.getString("comment");
				String branch = rs.getString("branch_id");
				String division = rs.getString("division_id");
				Timestamp insertDate = rs.getTimestamp("created_at");
				Timestamp updateDate = rs.getTimestamp("updated_at");

				UsersMessagesComments usersMessagesComments = new UsersMessagesComments();
				usersMessagesComments.setCommentId(commentId);
				usersMessagesComments.setUserId(userId);
				usersMessagesComments.setMessageId(messageId);
				usersMessagesComments.setName(name);
				usersMessagesComments.setComment(comment);
				usersMessagesComments.setBranch(branch);
				usersMessagesComments.setDivision(division);
				usersMessagesComments.setInsertDate(insertDate);
				usersMessagesComments.setUpdateDate(updateDate);

				ret.add(usersMessagesComments);
			}
			return ret;
		} finally {
			close(rs);
		}
	}
}
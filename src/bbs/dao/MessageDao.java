package bbs.dao;

import static bbs.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bbs.beans.Message;
import bbs.exception.SQLRuntimeException;

public class MessageDao {

	//新規投稿をＤＢに入れる
	public void insert(Connection connection, Message message) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO messages ( ");
			sql.append("  title");
			sql.append(", message");
			sql.append(", category");
			sql.append(", user_id");
			sql.append(", created_at");
			sql.append(", updated_at");
			sql.append(") VALUES (");
			sql.append("  ?"); // message
			sql.append(", ?"); // title
			sql.append(", ?"); // category
			sql.append(", ?"); // user_id
			sql.append(", CURRENT_TIMESTAMP"); // created_at
			sql.append(", CURRENT_TIMESTAMP"); // updated_at
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());


			ps.setString(1, message.getTitle());
			ps.setString(2, message.getMessage());
			ps.setString(3, message.getCategory());
			ps.setInt(4, message.getUserId());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public void deleteMessage(Connection connection, Message message){

		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM messages ");
			sql.append(" WHERE ");
			sql.append(" id = ? ");

			ps = connection.prepareStatement(sql.toString());
			ps.setInt(1, message.getId());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
}

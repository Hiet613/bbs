package bbs.dao;

import static bbs.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bbs.beans.Comment;
import bbs.exception.SQLRuntimeException;

public class CommentDao {

	//新規投稿をＤＢに入れる
	public void insert(Connection connection, Comment comment) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO comments ( ");
			sql.append("  messege_id");
			sql.append(", text");
			sql.append(", user_id");
			sql.append(", created_at");
			sql.append(", updated_at");
			sql.append(") VALUES (");
			sql.append("  ?"); // message_id
			sql.append(", ?"); // text
			sql.append(", ?"); // user_id
			sql.append(", CURRENT_TIMESTAMP"); // created_at
			sql.append(", CURRENT_TIMESTAMP"); // updated_at
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, comment.getMessageId());
			ps.setString(2, comment.getComment());
			ps.setInt(3, comment.getUserId());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

}

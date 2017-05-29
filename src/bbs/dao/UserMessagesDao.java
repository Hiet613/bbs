package bbs.dao;

import static bbs.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bbs.beans.UserMessages;
import bbs.exception.SQLRuntimeException;

public class UserMessagesDao {
//DBのuser_nessages ビューから値を取得するＤＡＯ
	public List<UserMessages> getUserMessages(Connection connection, int num){

		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM user_messages ");
			sql.append("ORDER BY created_at DESC limit " + num);

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<UserMessages> ret = toUserMessagesList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException (e) ;
		} finally {
			close(ps);
		}
	}

	private List<UserMessages> toUserMessagesList(ResultSet rs) throws SQLException {

		List<UserMessages> ret = new ArrayList<UserMessages>();
		try{
			while (rs.next()){
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String messages= rs.getString("message");
				String category = rs.getString("category");
				int userId = rs.getInt("user_id");
				String name = rs.getString("name");
				Timestamp insertDate = rs.getTimestamp("created_at");
				Timestamp updateDate = rs.getTimestamp("updated_at");

				UserMessages message = new UserMessages();
				message.setId(id);
				message.setTitle(title);
				message.setMessages(messages);
				message.setCategory(category);
				message.setUserId(userId);
				message.setName(name);
				message.setInsertDate(insertDate);
				message.setUpdateDate(updateDate);

				ret.add(message);
			}
			return ret;
		} finally {
			close(rs);
		}
	}
}
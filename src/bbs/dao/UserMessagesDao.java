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
			sql.append("SELECT * FROM users_messages ");
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
				String branch = rs.getString("branch_id");
				String division = rs.getString("division_id");
				Timestamp insertDate = rs.getTimestamp("created_at");
				Timestamp updateDate = rs.getTimestamp("updated_at");


				UserMessages message = new UserMessages();
				message.setId(id);
				message.setTitle(title);
				message.setMessages(messages);
				message.setCategory(category);
				message.setUserId(userId);
				message.setBranch(branch);
				message.setDivision(division);
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

	public List<UserMessages> getNarrowedMessages(Connection connection, int num, String start, String end){

		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM users_messages ");
			sql.append(" WHERE ? <= created_at AND ");
			sql.append(" created_at <= ? ");
			sql.append(" ORDER BY created_at DESC limit " + num);

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, start);
			ps.setString(2, end);

			ps.executeQuery();


			ResultSet rs = ps.executeQuery();
			List<UserMessages> ret = toUserMessagesList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException (e) ;
		} finally {
			close(ps);
		}
	}

	public List<UserMessages> getNarrowedMessagesCategory(Connection connection, int num, String start, String end, String category){

		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM users_messages ");
			sql.append(" WHERE ? <= created_at AND ");
			sql.append(" created_at <= ? ");
			sql.append(" AND category = ? ");
			sql.append(" ORDER BY created_at DESC limit " + num);

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, start);
			ps.setString(2, end);
			ps.setString(3, category);

			ps.executeQuery();


			ResultSet rs = ps.executeQuery();
			List<UserMessages> ret = toUserMessagesList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException (e) ;
		} finally {
			close(ps);
		}
	}
}
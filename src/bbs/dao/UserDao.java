package bbs.dao;

import static bbs.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bbs.beans.User;
import bbs.exception.NoRowsUpdatedRuntimeException;
import bbs.exception.SQLRuntimeException;



public class UserDao {


//暗号化したデータ同士が等しいことを判断
	public User getUser(Connection connection, String loginId, String password){
		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users WHERE login_id = ? AND password = ?";

			ps = connection.prepareStatement(sql);
			ps.setString(1, loginId);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);
			if (userList.isEmpty() == true ){
				return null;
			} else if (2 <= userList.size()){
				throw new IllegalStateException("2  <= userLsit.size()");

			} else {
				return userList.get(0);
			}
		} catch(SQLException e) {
			throw new SQLRuntimeException(e);

		} finally{
			close(ps);
		}
	}

//ユーザ情報を取得

	private List<User> toUserList(ResultSet rs) throws SQLException{

		List<User> ret = new ArrayList<User>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String loginId = rs.getString("login_id");
				String password = rs.getString("password");
				String name = rs.getString("name");
				String branch = rs.getString("branch_id");
				String division = rs.getString("division_id");
				String isStopped = rs.getString("is_stopped");
				Timestamp createdDate = rs.getTimestamp("created_at");
				Timestamp updatedDate = rs.getTimestamp("created_at");

				User user = new User();
				user.setId(id);
				user.setLoginId(loginId);
				user.setPassword(password);
				user.setName(name);
				user.setBranch(branch);
				user.setDivision(division);
				user.setIsStopped(isStopped);
				user.setInsertDate(createdDate);
				user.setInsertDate(updatedDate);

				ret.add(user);
			}
				return ret;
			} finally {
				close(rs);
		}
	}

//ユーザー情報を取得して並び替え
	public List<User> getUserList(Connection connection,int num){

		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM users ");
			sql.append("ORDER BY id ASC limit " +num);

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<User> ret = toUserList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException (e) ;
		} finally {
			close(ps);
		}
	}


//ユーザー情報を登録する

	public void insert(Connection connection, User user) {

		PreparedStatement ps = null;

		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO users( ");
			sql.append(" login_id");
			sql.append(", password");
			sql.append(", name");
			sql.append(", branch_id");
			sql.append(", division_id");
			sql.append(", created_at");
			sql.append(", updated_at");
			sql.append(") VALUES (");
			sql.append(" ?"); // login_id
			sql.append(", ?"); // passwprd
			sql.append(", ?"); // name
			sql.append(", ?"); // branch_id
			sql.append(", ?"); // division_id
			sql.append(", CURRENT_TIMESTAMP"); // created_date
			sql.append(", CURRENT_TIMESTAMP"); // update_date
			sql.append(");");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, user.getLoginId());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setInt(4, user.getBranch());
			ps.setInt(5, user.getDivision());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	public User getUsers(Connection connection, int id) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users WHERE id = ?";

			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);
			if (userList.isEmpty() == true) {
				return null;
			} else if (2 <= userList.size()) {
				throw new IllegalStateException("2 <= userList.size()");
			} else {
				return userList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}


//ユーザー情報の更新

	public void upDate(Connection connection,User user){

		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE users SET  ");
			sql.append(" login_id = ?");
			sql.append(", password = ?");
			sql.append(", name = ? ");
			sql.append(", branch_id = ?");
			sql.append(", division_id = ?");
			sql.append(" WHERE id = ? ;");
			ps= connection.prepareStatement(sql.toString());

			ps.setString(1, user.getLoginId());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setInt(4, user.getBranch());
			ps.setInt(5, user.getDivision());
			ps.setInt(6, user.getId());


			int count = ps.executeUpdate();
			if (count == 0) {
				throw new NoRowsUpdatedRuntimeException();
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

//ユーザー停止復活の更新
	public void isStopped(Connection connection,User user){

		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE users SET  ");
			sql.append(" is_stopped = ?");
			sql.append("  WHERE id = ? ;");

			ps= connection.prepareStatement(sql.toString());

			ps.setInt(1, user.getIsStopped());
			ps.setInt(2, user.getId());


			int count = ps.executeUpdate();
			if (count == 0) {
				throw new NoRowsUpdatedRuntimeException();
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
}

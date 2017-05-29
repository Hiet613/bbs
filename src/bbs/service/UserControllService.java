package bbs.service;

import static bbs.utils.CloseableUtil.*;
import static bbs.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import bbs.beans.User;
import bbs.dao.UserDao;

public class UserControllService {

	private static final int LIMIT_NUM = 1000;

	public List<User> getUserInfomarion(){

		Connection connection = null;
		try{
			connection =getConnection();

			UserDao userDao = new UserDao();
			List<User> ret = userDao.getUserList(connection, LIMIT_NUM);

			commit(connection);
			return ret;
		} catch (RuntimeException e){
			rollback(connection);
			throw e;
		} catch (Error e){
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}

	}
}

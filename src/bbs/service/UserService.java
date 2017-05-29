package bbs.service;

import static bbs.utils.CloseableUtil.*;
import static bbs.utils.DBUtil.*;

import java.sql.Connection;

import bbs.beans.User;
import bbs.dao.UserDao;
import bbs.utils.CipherUtil;

public class UserService {

	public void register(User user) {

		Connection connection = null;
		try {
			connection = getConnection();

			String encPassword = CipherUtil.encrypt(user.getPassword());
			user.setPassword(encPassword);

			UserDao userDao = new UserDao();

			//Daoのユーザ登録メソッド呼び出し
			userDao.insert(connection, user);

			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	public User getUser(int id) {

		//コネクションの取得(DBUtilのコネクションメソッド呼び出し）
		Connection connection = null;
		try {
			connection = getConnection();

			UserDao userDao = new UserDao();
			//Daoのユーザ情報取得メソッドの呼び出し
			User user = userDao.getUsers(connection, id);

			commit(connection);

			return user;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	public void upDate(User user){

		Connection connection = null;
		try{
			connection = getConnection();
			UserDao userDao = new UserDao();
			userDao.upDate(connection, user);

			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}



	public void  isStopped(User user){

		Connection connection = null;
		try{
			connection = getConnection();
			UserDao userDao = new UserDao();
			userDao.isStopped(connection, user);

			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}
}
package bbs.service;

import static bbs.utils.CloseableUtil.*;
import static bbs.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import bbs.beans.Message;
import bbs.beans.UserMessages;
import bbs.dao.MessageDao;
import bbs.dao.UserMessagesDao;

public class MessageService {

	public void register(Message message) {

		Connection connection = null;
		try{
			connection = getConnection();
			MessageDao messageDao = new MessageDao();

			messageDao.insert(connection, message);

			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e){
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}
	private static final int LIMIT_NUM = 1000;


	//投稿情報取得メソッド
	public List<UserMessages> getMessage(){

		Connection connection = null;
		try{
			connection =getConnection();

			UserMessagesDao messageDao = new UserMessagesDao();
			List<UserMessages> ret = messageDao.getUserMessages(connection, LIMIT_NUM);

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



	public List<UserMessages> getCategories(){

		Connection connection = null;
		try{
			connection =getConnection();

			UserMessagesDao UserMessagesDao = new UserMessagesDao();
			List<UserMessages> ret = UserMessagesDao.getCategories(connection);

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
//投稿削除メソッド

	public void delete(Message message) {

		Connection connection = null;
		try{
			connection = getConnection();
			MessageDao messageDao = new MessageDao();

			messageDao.deleteMessage(connection, message);

			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e){
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}


//日付絞込み取得メソッド


	public List<UserMessages> getNarrowedMessages(String start, String end){

		Connection connection = null;
		try{
			connection =getConnection();

			UserMessagesDao messageDao = new UserMessagesDao();

			List<UserMessages> ret = messageDao.getNarrowedMessages(connection, LIMIT_NUM, start, end);

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
	//日付カテゴリ絞込み取得メソッド

		public List<UserMessages> getNarrowedMessagesCategory(String start, String end,String category){

			Connection connection = null;
			try{
				connection =getConnection();

				UserMessagesDao messageDao = new UserMessagesDao();

				List<UserMessages> ret = messageDao.getNarrowedMessagesCategory(connection, LIMIT_NUM, start, end, category);

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

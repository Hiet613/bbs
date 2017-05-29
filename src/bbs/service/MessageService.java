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
}


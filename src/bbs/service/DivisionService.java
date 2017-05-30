package bbs.service;

import static bbs.utils.CloseableUtil.*;
import static bbs.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import bbs.beans.Division;
import bbs.dao.BranchDivisionDao;

public class DivisionService {


	private static final int LIMIT_NUM = 1000;

	public List<Division> getDivisions(){

		Connection connection = null;
		try{
			connection =getConnection();

			BranchDivisionDao branchDivision = new BranchDivisionDao();
			List<Division> ret = branchDivision.getDivisions(connection, LIMIT_NUM);

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


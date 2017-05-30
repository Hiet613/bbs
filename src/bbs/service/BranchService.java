package bbs.service;

import static bbs.utils.CloseableUtil.*;
import static bbs.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import bbs.beans.Branch;
import bbs.dao.BranchDivisionDao;

public class BranchService {


	private static final int LIMIT_NUM = 1000;

	public List<Branch> getBranches(){

		Connection connection = null;
		try{
			connection =getConnection();

			BranchDivisionDao branchDivision = new BranchDivisionDao();
			List<Branch> ret = branchDivision.getBranches(connection, LIMIT_NUM);

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


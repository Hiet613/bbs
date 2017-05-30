package bbs.dao;

import static bbs.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bbs.beans.Branch;
import bbs.beans.Division;
import bbs.exception.SQLRuntimeException;

public class BranchDivisionDao {
//DBのbranchesテーブルから値を取得するＤＡＯ
	public List<Branch> getBranches(Connection connection, int num){

		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM branches ");
			sql.append("ORDER BY created_at ASC limit " + num);

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<Branch> ret = toBranches(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException (e) ;
		} finally {
			close(ps);
		}
	}

	private List<Branch> toBranches(ResultSet rs) throws SQLException {

		List<Branch> ret = new ArrayList<Branch>();
		try{
			while (rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				Timestamp insertDate = rs.getTimestamp("created_at");

				Branch branch = new Branch();
				branch.setId(id);
				branch.setName(name);
				branch.setInsertDate(insertDate);


				ret.add(branch);
			}
			return ret;
		} finally {
			close(rs);
		}
	}
	public List<Division> getDivisions(Connection connection, int num){

		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM divisions ");
			sql.append("ORDER BY created_at ASC limit " + num);

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<Division> ret = toDivisions(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException (e) ;
		} finally {
			close(ps);
		}
	}

	private List<Division> toDivisions(ResultSet rs) throws SQLException {

		List<Division> ret = new ArrayList<Division>();
		try{
			while (rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				Timestamp insertDate = rs.getTimestamp("created_at");

				Division division = new Division();
				division.setId(id);
				division.setName(name);
				division.setInsertDate(insertDate);


				ret.add(division);
			}
			return ret;
		} finally {
			close(rs);
		}
	}
}
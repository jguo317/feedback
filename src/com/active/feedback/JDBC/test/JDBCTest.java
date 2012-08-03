package com.active.feedback.JDBC.test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import com.active.feedback.JDBC.JDBCConstant;

public class JDBCTest {
	public static void main(String[] arg) {
		String sql = "select * from question_types with(nolock)";
		System.out.println(Calendar.getInstance().getTime());
		//testSql(sql);
	}

	private static void testSql(String sql) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName(JDBCConstant.dbDriverName).newInstance();
			System.out.println(JDBCConstant.dbUrl);
			conn = DriverManager.getConnection(JDBCConstant.dbUrl, JDBCConstant.dbUserName, JDBCConstant.dbPassword);
			System.out.println(conn);
			conn.setAutoCommit(true);
			 ps = conn.prepareStatement(sql);
			 rs = ps.executeQuery();

			while (rs.next()) {
			 int qdID = rs.getInt(1);
			 System.out.println("qdID = " + qdID);			 
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in testSql. error message = "
					+ e.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
}

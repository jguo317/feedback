package com.active.feedback.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.active.feedback.JDBC.JDBCConstant;
import com.active.feedback.dao.UserDao;
import com.active.feedback.entities.Answer;
import com.active.feedback.entities.User;
import com.active.feedback.util.HibernateUtil;

public class UserDaoImpl implements UserDao {

	private Session session = null;
	private Transaction ts = null;

	public void add(User user) {
		String sql = "insert users (user_fname, user_mname, user_lname, user_email) values (?, ?, ?, ?) select @@identity as 'new_id'";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
				
		try{
			Class.forName(JDBCConstant.dbDriverName).newInstance();
			conn = DriverManager.getConnection(JDBCConstant.dbUrl, JDBCConstant.dbUserName, JDBCConstant.dbPassword);
			conn.setAutoCommit(true);
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getFname());
			ps.setString(2, user.getMname());
			ps.setString(3, user.getLname());
			ps.setString(4, user.getEmail());
			rs = ps.executeQuery();
			while (rs.next()) {
				user.setId(rs.getInt("new_id"));
			}
	      }catch(Exception e){
	         e.printStackTrace();
	      }finally{
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

	public void delete(User user) {
		try {
			session = HibernateUtil.getSession();
			ts = session.beginTransaction();
			session.delete(user);
			ts.commit();
		} catch (Exception e) {
			System.out.println("Exception occurs in UserDaoImpl.delete()");
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
		}

	}

	public void Update(User user) {
		try {
			session = HibernateUtil.getSession();
			ts = session.beginTransaction();
			session.update(user);
			ts.commit();
		} catch (Exception e) {
			System.out.println("Exception occurs in UserDaoImpl.update()");
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
		}

	}

	public User getUserById(int id) {

		User user = null;

		try {
			System.out.println("11111111111111111111111");
			System.out.println(HibernateUtil.test);
			session = HibernateUtil.getSession();
			System.out.println("2222222222222222222");
			ts = session.beginTransaction();
			Query query = session.createQuery("from User u where u.id = ?");
			query.setInteger(0, id);

			List<User> list = query.list();

			if (null != list && list.size() > 0) {
				user = list.get(0);
			}

			ts.commit();
		} catch (Exception e) {
			System.out.println("Exception occurs in UserDaoImpl.getUserById()");
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
		}

		return user;
	}

	/**
	 * 
	 * @param name
	 * @param password
	 * @return 1 means success, 0 means fail.
	 */
	public int checklogin(String name, String password) {
		int result = 0;
		String sql = "select 1 from user_access with(nolock) where ua_username = ? and ua_password = ?";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
				
		try{
			Class.forName(JDBCConstant.dbDriverName).newInstance();
			conn = DriverManager.getConnection(JDBCConstant.dbUrl, JDBCConstant.dbUserName, JDBCConstant.dbPassword);
			conn.setAutoCommit(true);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				result = 1;
			}
	      }catch(Exception e){
	         System.out.println("Exception occurs in SurveyDaoImpl.add()");
	         e.printStackTrace();
	      }finally{
	    	  if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	      }
	

		return result;

	}

	public User getUserByUserName(String username, String password) {

		User user=new User();
		String sql = "select users.* from user_access with(nolock) join users with(nolock) on user_id = ua_frn_user_id where ua_username = ? and ua_password = ?";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
				
		try{
			Class.forName(JDBCConstant.dbDriverName).newInstance();
			conn = DriverManager.getConnection(JDBCConstant.dbUrl, JDBCConstant.dbUserName, JDBCConstant.dbPassword);
			conn.setAutoCommit(true);
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if (rs.next()) {
				user.setId(rs.getInt("user_id"));
				user.setFname(rs.getString("user_fname"));
				user.setLname(rs.getString("user_lname"));
			}
	      }catch(Exception e){
	         System.out.println("Exception occurs in SurveyDaoImpl.add()");
	         e.printStackTrace();
	      }finally{
	    	  if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	      }

		return user;
	}

	public List<User> getAllUsers() {

		List<User> users = null;

		try {
			session = HibernateUtil.getSession();
			ts = session.beginTransaction();
			Query query = session.createQuery("from User as user");

			users = query.list();

			ts.commit();
		} catch (Exception e) {
			System.out.println("Exception occurs in UserDaoImpl.getAllUsers()");
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
		}

		return users;

	}

	public List<User> getUserByTeamId(int teamId) {
		List<User> userList = new ArrayList();
		try {
			session = HibernateUtil.getSession();
			ts = session.beginTransaction();
			Query query = session
					.createSQLQuery("select u.user_fname from teams t join teams_to_members ttm on ttm.ttm_frn_team_id=t.team_id"
							+ " join users u on  u.user_id=ttm.ttm_frn_user_id"
							+ " where t.team_id ="+teamId);
			Iterator it = query.list().iterator();
			User user=null;
			while (it.hasNext()) {
				user = new User();
				user.setFname(it.next().toString());
				userList.add(user);
			}
			ts.commit();
		} catch (Exception e) {
			System.out.println("Exception occurs in UserDaoImpl.getUserByTeamId()");
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
		}

		return userList;
	}


}

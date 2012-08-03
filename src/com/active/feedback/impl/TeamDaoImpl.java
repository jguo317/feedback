package com.active.feedback.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.active.feedback.JDBC.JDBCConstant;
import com.active.feedback.dao.TeamDao;
import com.active.feedback.entities.Question;
import com.active.feedback.entities.Team;
import com.active.feedback.entities.Team2Admin;
import com.active.feedback.entities.Team2Member;
import com.active.feedback.entities.User;
import com.active.feedback.util.HibernateUtil;

public class TeamDaoImpl implements TeamDao{
	
	private Session session = null;
	private Transaction ts = null;
	
	public void add(Team team){
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "insert into teams (team_name) values (?) select @@identity as 'new_id'";
				
		try{
			Class.forName(JDBCConstant.dbDriverName).newInstance();
			conn = DriverManager.getConnection(JDBCConstant.dbUrl, JDBCConstant.dbUserName, JDBCConstant.dbPassword);
			conn.setAutoCommit(true);
			ps = conn.prepareStatement(sql);
			ps.setString(1, team.getName());
			rs = ps.executeQuery();
			while (rs.next()) {
				team.setId(rs.getInt("new_id"));
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
	
	public void delete(Team team){
		try{
			session = HibernateUtil.getSession();
			ts = session.beginTransaction();
			session.delete(team);
			ts.commit();
          }catch(Exception e){
             System.out.println("Exception occurs in TeamDaoImpl.delete()");
             e.printStackTrace();
          }finally{
        	  HibernateUtil.closeSession(session);
        }
		
	}
	
	public void update(Team team){
		
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "update teams set team_name = ? where team_id = ?";
				
		try{
			Class.forName(JDBCConstant.dbDriverName).newInstance();
			conn = DriverManager.getConnection(JDBCConstant.dbUrl, JDBCConstant.dbUserName, JDBCConstant.dbPassword);
			conn.setAutoCommit(true);
			ps = conn.prepareStatement(sql);
			ps.setString(1, team.getName());
			ps.setInt(2, team.getId());
			ps.execute();
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
	
	public Team getTeamById(int id){
		Team team=new Team();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from teams where team_id = ?";
				
		try{
			Class.forName(JDBCConstant.dbDriverName).newInstance();
			conn = DriverManager.getConnection(JDBCConstant.dbUrl, JDBCConstant.dbUserName, JDBCConstant.dbPassword);
			conn.setAutoCommit(true);
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				team.setId(rs.getInt("team_id"));
				team.setName(rs.getString("team_name"));
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
		return team;
	}

	
	public List<Team> getAllTeamByUserId(int userId) {
		List<Team> tList=new ArrayList<Team>();
		Team t = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select team_id, team_name from teams with(nolock) join teams_to_admins with(nolock) on team_id = tta_frn_team_id where tta_frn_user_id = ?";
				
		try{
			Class.forName(JDBCConstant.dbDriverName).newInstance();
			conn = DriverManager.getConnection(JDBCConstant.dbUrl, JDBCConstant.dbUserName, JDBCConstant.dbPassword);
			conn.setAutoCommit(true);
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				t = new Team();
				t.setName(rs.getString("team_name"));
				t.setId(rs.getInt("team_id"));
				tList.add(t);				
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
		
		return tList;
	}
	
	public List<User> getMembersByTeamId(int id) {
		List<User> uList = new ArrayList<User>();
		User u = null;
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select u.* from users u with(nolock) join teams_to_members with(nolock) on user_id = ttm_frn_user_id and ttm_frn_team_id = ?";				
		try{
			Class.forName(JDBCConstant.dbDriverName).newInstance();
			conn = DriverManager.getConnection(JDBCConstant.dbUrl, JDBCConstant.dbUserName, JDBCConstant.dbPassword);
			conn.setAutoCommit(true);
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				u = new User();
				u.setId(rs.getInt("user_id"));
				u.setFname(rs.getString("user_fname"));
				u.setLname(rs.getString("user_lname"));
				u.setEmail(rs.getString("user_email"));
				uList.add(u);				
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
		
		return uList;
	}

	public void addTeam2Member(Team2Member t2m) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "insert into teams_to_members (ttm_frn_team_id, ttm_frn_user_id) values (?, ?)";
				
		try{
			Class.forName(JDBCConstant.dbDriverName).newInstance();
			conn = DriverManager.getConnection(JDBCConstant.dbUrl, JDBCConstant.dbUserName, JDBCConstant.dbPassword);
			conn.setAutoCommit(true);
			ps = conn.prepareStatement(sql);
			ps.setInt(1, t2m.getTeam().getId());
			ps.setInt(2, t2m.getUser().getId());
			ps.execute();
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

	public void addTeam2Admin(Team2Admin t2a) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "insert into teams_to_admins (tta_frn_team_id, tta_frn_user_id) values (?, ?)";
				
		try{
			Class.forName(JDBCConstant.dbDriverName).newInstance();
			conn = DriverManager.getConnection(JDBCConstant.dbUrl, JDBCConstant.dbUserName, JDBCConstant.dbPassword);
			conn.setAutoCommit(true);
			ps = conn.prepareStatement(sql);
			ps.setInt(1, t2a.getTeam().getId());
			ps.setInt(2, t2a.getUser().getId());
			ps.execute();
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
	
}

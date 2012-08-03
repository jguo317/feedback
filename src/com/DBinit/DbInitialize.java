package com.DBinit;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.hibernate.hql.ast.tree.Statement;


public class DbInitialize {
	
    public static void initData(){
    	
	    Connection connection = null;
	    PreparedStatement ps = null;
	
	    Statement stmt = null;
	    try {
	            Class.forName("org.hsqldb.jdbcDriver");
	            
	            connection = DriverManager.getConnection("jdbc:hsqldb:mem:test", "sa", "");
	            ps = connection.prepareStatement("create table User(id int,fname varchar(50),mname varchar(50),lname varchar(50),email varchar(100))");
	            ps.execute();
	            System.out.println("Table User is created successfully");
	            
	            ps = connection.prepareStatement("create table Team(id int,teamName varchar(50))");
	            ps.execute();
	            System.out.println("Table Team is created successfully");
	            
	            ps = connection.prepareStatement("create table User2Team(id int,teamId int, userId int)");
	            ps.execute();
	            System.out.println("Table User2Team is created successfully");
	                        
	    } catch (Exception e) {
	            e.printStackTrace(System.err);
	    } finally {
	            try {
	                    if (stmt != null) {
	                            ps.close();
	                    }
	            } catch (SQLException e) {}
	    }               
    }
   
    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
            
    	    //start database server         
            HsqldbServer hsqldbServer = HsqldbServer.getInstance();
            hsqldbServer.startServer(); 
            
            //Create data objects
            DbInitialize.initData();
           
    }
}

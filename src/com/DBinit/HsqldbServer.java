package com.DBinit;


import org.hsqldb.Server;


public class HsqldbServer {
    public static HsqldbServer hsqldbServer = new HsqldbServer();
    private HsqldbServer(){
           
    }
public void startServer() {
    Server  server = new Server();
    server.setDatabaseName(0, "test");
    server.setDatabasePath(0, "mem:test;sql.enforce_strict_size=true");
    server.setLogWriter(null);
    server.setErrWriter(null);
    server.start();            
}

public static HsqldbServer getInstance(){
        return hsqldbServer;
}
}
package com.example.demo.credentials;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


@RepositoryRestResource
interface CredentialsRepository extends JpaRepository<Credentials, Long> {
    public class Database {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String url = "jdbc:sqlite:../../../projt/sqlite-tools/sqldb2";
                     
        public List<User> getAllUsers() {    
            List<User> usrs = new ArrayList<>();
            String sql = "SELECT user as user, password as password FROM EVANESCENT;";
            try {  
                conn = DriverManager.getConnection(url); 
                stmt  = conn.createStatement();  
                rs    = stmt.executeQuery(sql);
                usrs.add(new User(rs.getString("user"), rs.getString("password")));
            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
            } finally {
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException e) { /* Ignored */}
                }
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (SQLException e) { /* Ignored */}
                }
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) { /* Ignored */}
                }
            }
            return usrs;
        }

        public List<Credentials> getAllCredentials(String unam) {
            List<Credentials> creds = new ArrayList<>();
            String sql = "SELECT id as id, user as user, username as username, password as password, cpwd as cpwd, website as website, deleted as deleted FROM EVANESCENT where user = '" + unam + "';";
            try {
                conn = DriverManager.getConnection(url); 
                stmt  = conn.createStatement();  
                rs    = stmt.executeQuery(sql);
                while(rs.next()) {     
                    creds.add(new Credentials(rs.getInt("id"), rs.getString("user"), rs.getString("username"), rs.getString("password"), rs.getString("cpwd"), rs.getString("website"), rs.getString("deleted"), ""));
                }
            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
            } if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) { /* Ignored */}
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) { /* Ignored */}
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) { /* Ignored */}
            }
            return creds;
        }

        public List<Credentials> addCredentials(Credentials cred) {
            String sql = "INSERT INTO EVANESCENT VALUES (NULL, \"" + cred.getUser() +"\", \"" + cred.getUserName() 
            + "\", \"" + cred.getPassWord() + "\", \"" + cred.getCpwd() + "\", \"" + cred.getWebSite() + "\", \"" + cred.getActiveStatus() + "\");";
            System.out.println(sql);
            try {
                conn = DriverManager.getConnection(url); 
                stmt  = conn.createStatement();  
                stmt.executeQuery(sql);
            // CREATE TABLE EVANESCENT (id INTEGER PRIMARY KEY AUTOINCREMENT, user varchar(32) default NULL, username varchar(32) default NULL, password varchar(32) default NULL,  cpwd varchar(32) default NULL, website varchar(32) default NULL, deleted varchar(32) default "False");  
            // INSERT INTO EVANESCENT VALUES (NULL, "lv", "lv1", "p1", "lvcp", "w1", "False");
            } catch (SQLException e) {  
                System.out.println(e.getMessage());
            } if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) { /* Ignored */}
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) { /* Ignored */}
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) { /* Ignored */}
            }
            return getAllCredentials(cred.getUser());
        }

        public List<Credentials> deleteCredentials(Credentials cred) {
            String sql = "UPDATE EVANESCENT SET deleted = \"True\"";
            System.out.println(sql);
            try {
                conn = DriverManager.getConnection(url); 
                stmt  = conn.createStatement();  
                stmt.executeQuery(sql);
            // CREATE TABLE EVANESCENT (id INTEGER PRIMARY KEY AUTOINCREMENT, user varchar(32) default NULL, username varchar(32) default NULL, password varchar(32) default NULL,  cpwd varchar(32) default NULL, website varchar(32) default NULL, deleted varchar(32) default "False");  
            // INSERT INTO EVANESCENT VALUES (NULL, "lv", "lv1", "p1", "lvcp", "w1", "False");
            } catch (SQLException e) {  
                System.out.println(e.getMessage());
            } if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) { /* Ignored */}
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) { /* Ignored */}
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) { /* Ignored */}
            }
            return getAllCredentials(cred.getUser());
        }

        public int getCount(String unm) {
            int count = 0;
            String sql = "SELECT count(username) as total FROM EVANESCENT where user = '" + unm + "';";
            try { 
                conn = DriverManager.getConnection(url); 
                stmt  = conn.createStatement();  
                rs    = stmt.executeQuery(sql);
                
                count = rs.getInt("total");
            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
            }  if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) { /* Ignored */}
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) { /* Ignored */}
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) { /* Ignored */}
            }
            return count;
        }        
    }
}
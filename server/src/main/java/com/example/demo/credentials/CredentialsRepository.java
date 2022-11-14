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
        static Connection conn = null;
        
        public void retrieveData(String unm) {
            List<List<String>> accounts = new ArrayList<List<String>>();
            
            Database database = new Database();
            database.connect();
            unm = "User1";
            int accountNum = database.getCount(unm);
            System.out.println(accountNum);
            for (int i = 0; i < accountNum; i++) {
                List<String> account = new ArrayList<String>();
                account.add(database.getUsername(i));
                account.add(database.getPassword(i));
                account.add(database.getWebsite(i));
                account.add(database.getDeleted(i));
                accounts.add(account);
            }
        }
        
        public void connect() {  
            try {  
                // path of the database  
                String url = "jdbc:sqlite:/Users/yuhihakozaki/Desktop/workspace/SeleniumDBMS/evanescent.db";  
                // create a connection to the database  
                conn = DriverManager.getConnection(url);  
                  
                System.out.println("Connection to SQLite has been established.");  
            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
            } 
        }  
        
        public void close() {
             try {  
                 if (conn != null) {  
                     conn.close();  
                 }  
             } catch (SQLException ex) {  
                 System.out.println(ex.getMessage());  
             }  
        }
        
        public int getCount(String unm) {
            int count = 0;
            String sql = "SELECT count(username) as total FROM EVANESCENT where user = '" + unm + "';";
            try {
                Statement stmt  = conn.createStatement();  
                ResultSet rs    = stmt.executeQuery(sql);  
                
                count = rs.getInt("total");
            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
            }  
            return count;
        }
        
        public String getUsername(int skip) {
            String sql = "SELECT username FROM EVANESCENT";
            String username ="";
            try {
                Statement stmt  = conn.createStatement();  
                ResultSet rs    = stmt.executeQuery(sql);
                rs.next();
                for (int i = 0; i < skip; i++) {
                    rs.next();
                }
                username = rs.getString("username");
            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
            }  
            return username;
        }
        
        public String getPassword(int skip) {
            String sql = "SELECT password FROM EVANESCENT";
            String password ="";
            try {
                Statement stmt  = conn.createStatement();  
                ResultSet rs    = stmt.executeQuery(sql);  
                
                rs.next();
                for (int i = 0; i < skip; i++) {
                    rs.next();
                }
                password = rs.getString("password");
            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
            }  
            return password;
        }
        
        public String getWebsite(int skip) {
            String sql = "SELECT website FROM EVANESCENT";
            String website ="";
            try {
                Statement stmt  = conn.createStatement();  
                ResultSet rs    = stmt.executeQuery(sql);  
                
                rs.next();
                for (int i = 0; i < skip; i++) {
                    rs.next();
                }
                website = rs.getString("website");
            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
            }  
            return website;
        }
        
        public String getDeleted(int skip) {
            String sql = "SELECT deleted FROM EVANESCENT";
            String deleted ="";
            try {
                Statement stmt  = conn.createStatement();  
                ResultSet rs    = stmt.executeQuery(sql);  
                
                rs.next();
                for (int i = 0; i < skip; i++) {
                    rs.next();
                }
                deleted = rs.getString("deleted");
            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
            }  
            return deleted;
        }
        
        public void setDeleted() {
            String sql = "UPDATE EVANESCENT SET deleted = \"True\"";
            try {
                Statement stmt  = conn.createStatement();  
                stmt.executeQuery(sql);  
                
            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
            }  
        }
    }
}
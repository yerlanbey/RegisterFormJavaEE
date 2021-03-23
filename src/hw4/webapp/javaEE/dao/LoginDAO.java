package hw4.webapp.javaEE.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hw4.webapp.javaEE.datamean.User;

public class LoginDAO {
	//Getting from Servlet Login.java ServletException
    private List<Connection> connectionPool = new ArrayList<Connection>();

    private String jdbcDriver;
    private String jdbcURL;
    private String tableName;

    public LoginDAO(String jdbcDriver, String jdbcURL, String tableName)
            throws MyDAOException {
        this.jdbcDriver = jdbcDriver;
        this.jdbcURL = jdbcURL;
        this.tableName = tableName;

    }
    // end ServletException
    
    
    private synchronized Connection getConnection() throws MyDAOException {
        if (connectionPool.size() > 0) {
            return connectionPool.remove(connectionPool.size() - 1);
        }

        try {
            Class.forName(jdbcDriver);
        } catch (ClassNotFoundException e) {
            throw new MyDAOException(e);
        }

        try {
            return DriverManager.getConnection(jdbcURL);
        } catch (SQLException e) {
            throw new MyDAOException(e);
        }
    }
    
    private synchronized void releaseConnection(Connection con) {
        connectionPool.add(con);
    }
    public User read(String email) throws MyDAOException {
        Connection con = null;
        try {
            con = getConnection();

            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM "
                    + tableName + " WHERE email=?");
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            User user;
            if (!rs.next()) {
                user = null;
            } else {
                user = new User();
                user.setFirst_name(rs.getString("first_name"));
                user.setLast_name(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
            }

            rs.close();
            pstmt.close();
            releaseConnection(con);
            return user;

        } catch (Exception e) {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e2) { /* ignore */
            }
            throw new MyDAOException(e);
        }
    }
    public User[] getUsers() throws MyDAOException {
        Connection con = null;
        try {
            con = getConnection();

            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM "
                    + tableName);
          
            ResultSet rs = pstmt.executeQuery();

            List<User> list = new ArrayList<User>();
            while (rs.next()) {
                User bean = new User();
                bean.setFirst_name(rs.getString("first_name"));
                bean.setLast_name(rs.getString("last_name"));
                bean.setEmail(rs.getString("email"));
                bean.setPassword(rs.getString("password"));
              
                
               
          
                list.add(bean);
            }
            pstmt.close();
            releaseConnection(con);

            return list.toArray(new User[list.size()]);
        } catch (SQLException e) {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e2) { /* ignore */
            }
            throw new MyDAOException(e);
        }
    }
}
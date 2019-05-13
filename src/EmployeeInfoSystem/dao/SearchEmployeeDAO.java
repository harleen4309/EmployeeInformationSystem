/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmployeeInfoSystem.dao;

import com.prog32758.db.DBConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Harleen Kaur--search from given employees based on empId or firstName
 * uses try catch for exception handling
 */
public class SearchEmployeeDAO {
    /**
     * search based on empId passed by user
     * @param empNo -- employee Id passed by user
     */

    public static void searchEmployeeByEmpId(int empNo) {

        Connection conn = DBConnector.getConnection();

        String sqlQuery = "SELECT * FROM employeeinfo"
                + " WHERE Emp_no = ? ";

        PreparedStatement selectStmt = null;
        ResultSet rs = null;

        try {
            selectStmt = conn.prepareStatement(sqlQuery);
            selectStmt.setInt(1, empNo);

            rs = selectStmt.executeQuery();
            printResultSet(conn, selectStmt, rs);

        } catch (SQLException ex) {
            System.err.println("SQL message:" + ex.getMessage());
            System.err.println("SQL state :" + ex.getSQLState());
            System.err.println("SQL error code :" + ex.getErrorCode());

        }

    }
    /**
     * search based on firstName passed by user
     * @param firstName -- firstName passed by user
     */

    public static void searchEmployeeByFirstName(String firstName) {
        Connection conn = DBConnector.getConnection();
        String sqlQuery = "SELECT * FROM employeeinfo"
                + " WHERE First_Name = ? ";

        PreparedStatement selectStmt = null;
        ResultSet rs = null;
        try {
            selectStmt = conn.prepareStatement(sqlQuery);

            selectStmt.setString(1, firstName);

            rs = selectStmt.executeQuery();
            printResultSet(conn, selectStmt, rs);

        } catch (SQLException ex) {
            System.err.println("SQL message:" + ex.getMessage());
            System.err.println("SQL state :" + ex.getSQLState());
            System.err.println("SQL error code :" + ex.getErrorCode());

        }

    }

    /**
     *prints the result set created by search query and closes all connections
     * @param conn-- connection object 
     * @param selectStmt-- prepared sqlQuery statement
     * @param rs-- result set got from executing select query
     */

    public static void printResultSet(Connection conn, PreparedStatement selectStmt, ResultSet rs) {

        try {

            while (rs.next()) {
                int empNum = rs.getInt(1);
                String fName = rs.getString(2);
                String lastName = rs.getString(3);
                String title = rs.getString(4);
                String dept = rs.getString(5);
                Double salary = rs.getDouble(6);

                System.out.println("Employee ID: " + empNum);
                System.out.println("First Name : " + fName);
                System.out.println("Last Name : " + lastName);
                System.out.println("Title : " + title);
                System.out.println("Department : " + dept);
                System.out.println("Salary : " + salary);
                System.out.println("-----------");
            }
        } catch (SQLException ex) {
            System.err.println("SQL message:" + ex.getMessage());
            System.err.println("SQL state :" + ex.getSQLState());
            System.err.println("SQL error code :" + ex.getErrorCode());

        } finally {
            DBConnector.closeJDBCObjects(conn, selectStmt, null);
        }
    }

}

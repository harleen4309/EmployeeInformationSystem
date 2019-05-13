/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmployeeInfoSystem.dao;

import com.prog32758.db.DBConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Harleen Kaur 
 * Inserts new employees using data passed by user
 * 
 */
public class InsertEmployeeDAO {
 /**
  * creates and execute sqlQuery based on user input and insert new employee
  * @param empNo -- employeeID passed by user
  * @param firstName -- firstName passed by user
  * @param lastName-- lastName passed by user
  * @param title -- title passed by user
  * @param dept-- department passed by user
  * @param salary -- salary passed by user
  */
    public static void insertEmployee(int empNo, String firstName, String lastName,
            String title, String dept, double salary) {
        Connection conn = DBConnector.getConnection();

        String sqlQuery = "INSERT INTO employeeinfo(Emp_no,First_Name, Last_Name,Title,Department,Salary )"
                + " VALUES (?,?,?,?,?,?);";

        PreparedStatement stmt = null;
        int count = 0;
        try {
            stmt = conn.prepareStatement(sqlQuery);

            stmt.setInt(1, empNo);
            stmt.setString(2, firstName);
            stmt.setString(3, lastName);
            stmt.setString(4, title);
            stmt.setString(5, dept);
            stmt.setDouble(6, salary);

            count = stmt.executeUpdate();

            System.out.println(count + " row(s) affected");
            System.out.println("Employee added successfully");

        } catch (SQLException ex) {

            System.err.println("SQL message:" + ex.getMessage());
            System.err.println("SQL state :" + ex.getSQLState());
            System.err.println("SQL error code :" + ex.getErrorCode());

        } finally {
            DBConnector.closeJDBCObjects(conn, stmt, null);
        }

    }

}

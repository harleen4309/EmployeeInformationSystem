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
 * @author Harleen Kaur--removes employees who resigned based on their employeeId and First Name
 * uses try catch for exception handling
 */
public class RemoveEmployeeDAO {
    /**
     * creates and executes sqlQuery based on empID and firstName of resigning employee
     * @param empNo -- employeeID passed by user
     * @param firstName -- firstName passed by user
     */

    public static void removeEmployee(int empNo, String firstName) {

        Connection conn = DBConnector.getConnection();

        String sqlQuery = "DELETE FROM employeeinfo"
                + " WHERE Emp_no = ? AND First_Name = ? ";

        PreparedStatement stmt = null;
        int count = 0;

        try {

            stmt = conn.prepareStatement(sqlQuery);

            stmt.setInt(1, empNo);
            stmt.setString(2, firstName);

            count = stmt.executeUpdate();

            System.out.println(count + " row(s) affected");
            if(count == 1)
            System.out.println("Employee removed successfully");
            else if(count == 0)
                System.out.println("Employee could not be removed.Check your input ");

        } catch (SQLException ex) {

            System.err.println("SQL message:" + ex.getMessage());
            System.err.println("SQL state :" + ex.getSQLState());
            System.err.println("SQL error code :" + ex.getErrorCode());

        } finally {
            DBConnector.closeJDBCObjects(conn, stmt, null);
        }

    }

}

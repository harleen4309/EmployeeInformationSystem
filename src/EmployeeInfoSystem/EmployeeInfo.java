package EmployeeInfoSystem;

import EmployeeInfoSystem.dao.InsertEmployeeDAO;
import EmployeeInfoSystem.dao.RemoveEmployeeDAO;
import EmployeeInfoSystem.dao.SearchEmployeeDAO;
import com.prog32758.db.DBConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Harleen Kaur class EmployeeInfo--contains user information
 * 
 */
public class EmployeeInfo {

    /**
     * @param args the command line arguments
     * used to access other class methods suitably to add , delete and search employees
     * 
     */
    public static void main(String[] args) {
        Scanner sc1 = new Scanner(System.in);
        int empNo = 0;
        String firstName = "";
        String lastName = "";
        String title = "";
        String dept = "";
        double salary = 0.00;

        String driver = "com.mysql.cj.jdbc.Driver";
        String connURL = "jdbc:mysql://localhost/";
        String databaseName = "employeedb";
        String user = "root";
        String pass = "Aqualite12345!@#";

        Connection conn = null;
        Scanner scanner = null;
        Scanner scanner1 = null;
        PreparedStatement stmt = null;

        try {
            
            DBConnector.createConnection(driver, connURL, databaseName, user, pass);
            conn = DBConnector.getConnection();
            System.out.println("Press 1 to add new employee\n"
                    + "2 to remove an employee \n"
                    + "3 to search an employee");
            int num = sc1.nextInt();

            switch (num) {
                case 1:
                    scanner = new Scanner(System.in);
                    try {
                        System.out.println("Enter the Emp No.: ");
                        empNo = scanner.nextInt();
                        System.out.println("Enter the First Name :");
                        firstName = scanner.next();
                        System.out.println("Enter the Last Name: ");
                        lastName = scanner.next();
                        System.out.println("Enter the title: ");
                        title = scanner.next();
                        System.out.println("Enter the department: ");
                        dept = scanner.next();
                        System.out.println("Enter the salary earned: ");
                        salary = scanner.nextDouble();
                    } catch (InputMismatchException e) {
                        System.out.println("Make sure to enter an INTEGER for employee ID\n"
                                + " DECIMAL for salary \n"
                                + "STRINGS for rest of fields");
                        return;
                    }   /**
                     * calls insertEmployee method form InsertEmployee class using parameters passed by user
                     */
                    InsertEmployeeDAO.insertEmployee(empNo, firstName, lastName, title, dept, salary);
                    break;
                case 2:
                    scanner = new Scanner(System.in);
                    scanner1 = new Scanner(System.in);
                    try {
                        System.out.println("Enter the Emp No.:");
                        empNo = scanner.nextInt();
                        
                        System.out.println("Enter the First Name:");
                        firstName = scanner1.next();
                    } catch (InputMismatchException e) {
                        System.out.println("Make sure to enter an INTEGER for Employee ID\n"
                                + "STRING for first name");
                        return;
                    }   /**
                     * calls removeEmployee method form RemoveEmployee class using parameters passed by user
                     */
                    RemoveEmployeeDAO.removeEmployee(empNo, firstName);
                    break;
                case 3:
                    scanner = new Scanner(System.in);
                    scanner1 = new Scanner(System.in);
                    System.out.println("press 1 for emp no \n "
                            + " 2 for first name");
                    int number = scanner.nextInt();
                    if (number == 1) {
                        try {
                            System.out.println("Enter the Emp No.:");
                            empNo = scanner.nextInt();
                            
                        } catch (InputMismatchException e) {
                            System.out.println("Make sure to enter an INTEGER");
                        }
                        /**
                         * calls searchEmployeeByEmpId method form SearchEmployee class using employee ID passed by user
                         */
                        SearchEmployeeDAO.searchEmployeeByEmpId(empNo);
                        
                    } else if (number == 2) {
                        
                        try {
                            System.out.println("Enter the First Name: ");
                            firstName = scanner1.next();
                        } catch (InputMismatchException e) {
                            System.out.println("Make sure to enter a STRING");
                        }
                        /**
                         * calls searchEmployeeByFirstName method form SearchEmployee class using first name passed by user
                         */
                        SearchEmployeeDAO.searchEmployeeByFirstName(firstName);
                    }   break;
                default:
                    break;
            }
        } catch (Exception e) {
            System.out.println("Please verify your input again");
        } finally {
            /**
             * destroys the connection
             */
            DBConnector.closeJDBCObjects(conn, stmt, null);
        }

    }
}

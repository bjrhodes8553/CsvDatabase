package edu.fgcu.dataengineering;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.sqlite.util.StringUtils;

public class DatabaseAccessor {

  public static void insertAuthor(String name, String email, String a_url) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    String sql;

    try {
      String url = "jdbc:sqlite:C:\\Users\\feesh\\OneDrive\\intelliJCOP\\CsvToDatabase2\\src\\Data\\BookStore.db";
      conn = DriverManager.getConnection(url);
      System.out.println("Connection established");
      sql = "INSERT INTO author(author_name, author_email, author_url) VALUES(?,?,?);";
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, name);
      pstmt.setString(2, email);
      pstmt.setString(3, a_url);
      pstmt.executeUpdate();


    } catch (SQLException e) {
      System.out.println(e.getMessage());
    } finally {
      try {
        if (conn != null) {
          conn.close();
        }
      } catch (SQLException ex) {
        System.out.println(ex.getMessage());
      }
    }
  }

  public static void insertBook(List arr) {

    System.out.println("Testingggg");

      /*
       So fileRows will be an Object type (which is fine we are just incrementing though it)
            So after getting each row, we will need to "cast" row to a String array (String[])
       */


        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql;

        try {
          String url = "jdbc:sqlite:C:\\Users\\feesh\\OneDrive\\intelliJCOP\\CsvToDatabase2\\src\\Data\\BookStore.db";
          conn = DriverManager.getConnection(url);
          sql = "INSERT INTO book(isbn, book_title, author_name, publisher_name) VALUES(?,?,?,?);";
          pstmt = conn.prepareStatement(sql);
          int i = 1;
          for (Object row : arr) {
            for (String fields : (String[]) row) {
              pstmt.setString(1, fields);
              i++;
            }
          }
          pstmt.executeUpdate();

            } catch(SQLException e){
              System.out.println(e.getMessage());
            } finally{
              try {
                if (conn != null) {
                  conn.close();
                }
              } catch (SQLException ex) {
                System.out.println(ex.getMessage());
              }
            }
          }
        }



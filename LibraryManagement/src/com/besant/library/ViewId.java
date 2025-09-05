package com.besant.library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.besant.library.exception.InvalidIDInputException;

public class ViewId {
    public static boolean viewIDMethod(String url, String userName, String password , int bookid) throws InvalidIDInputException{
        boolean flag = false;

        String query = "select * from book where bookid=?";
        Connection con = DBConnection.getConnection(url, userName, password);

        try (PreparedStatement pr = con.prepareStatement(query)) {

            if (bookid < 1) {
                throw new InvalidIDInputException("Enter valid bookid");
            }

            pr.setInt(1, bookid);
            ResultSet rs = pr.executeQuery();

            while (rs.next()) {
                flag = true;
                int bookid1 = rs.getInt("bookid");
                String bookname = rs.getString("bookname");
                int quantity = rs.getInt("quantity");
                int price = rs.getInt("price");
                boolean isAvailable = rs.getBoolean("isavailable");

                System.out.println("ID:" + bookid1 +
                        " Name:" + bookname +
                        " Quantity:" + quantity +
                        " Price:" + price +
                        " Availability:" + isAvailable);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return flag;
    }
}

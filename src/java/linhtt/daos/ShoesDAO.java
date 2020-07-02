///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package linhtt.daos;
//
//import generated.bank.Bank;
//import java.io.Serializable;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import utils.DBUtil;
//
///**
// *
// * @author dungntmse63284
// */
//public class ShoesDAO implements Serializable {
//     private Connection connection = null;
//    private PreparedStatement statement = null;
//    private ResultSet resultSet = null;
//
//    private void closeConnection() throws SQLException {
//        if (resultSet != null) {
//            resultSet.close();
//        }
//        if (statement != null) {
//            statement.close();
//        }
//        if (connection != null) {
//            connection.close();
//        }
//    }
//    public boolean insertBank(Bank bank)  throws SQLException, ClassNotFoundException {
//        boolean result = false;
//        try {
//            String sql="Insert into Bank(name, one_month, three_month, six_month, twelve_month"
//                    + ", twentyfour_month) values(?,?,?,?,?,?)";
//            connection = DBUtil.createConnection();
//            statement = connection.prepareStatement(sql);
//            statement.setString(1, bank.getName());
//            statement.setDouble(2, bank.getOneMonth());
//            statement.setDouble(3, bank.getThreeMonth());
//            statement.setDouble(4, bank.getSixMonth());
//            statement.setDouble(5, bank.getTwelveMonth());
//            statement.setDouble(6, bank.getTwentyFourMonth());
//            result = statement.executeUpdate() > 0;
//        } finally{
//            closeConnection();
//        }
//        return result;
//    }
//}

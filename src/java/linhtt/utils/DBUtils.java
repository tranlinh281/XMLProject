/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhtt.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.xml.bind.JAXBException;
import javax.xml.transform.dom.DOMResult;

/**
 *
 * @author macos
 */
public class DBUtils implements Serializable {
    public static Connection createConnection() throws SQLException, ClassNotFoundException{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;databaseName=TouristDB";
        Connection connection = DriverManager.getConnection(url, "sa", "Nh@tquang98");
        return connection;
    }
    
//    public static void saveDomResultToDatabase(DOMResult domResult) throws JAXBException, SQLException, ClassNotFoundException {
//        Tours tours = new Tours();
//        tours = (Tours) JAXBUtil.unmarshal(tours.getClass(), domResult.getNode());
//
//        TourDAO tourDAO = new TourDAO();
//        
//        for (Tour tour: tours.getTour()) {
//            tourDAO.insert(tour);
//        }
//    }
}

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package linhtt.resolvers;
//
//import daos.BankDAO;
//import daos.StockDAO;
//import generated.bank.Bank;
//import generated.bank.Banks;
//import generated.stock.Stocks;
//import generated.stock.Stock;
//
//import java.io.Serializable;
//import java.sql.SQLException;
//import javax.xml.bind.JAXBException;
//import javax.xml.transform.dom.DOMResult;
//import utils.JAXBUtil;
//
///**
// *
// * @author dungntmse63284 
// */
//public class DataResolver implements Serializable {
//    public void saveDomResultToDatabase(DOMResult domResult)throws JAXBException, SQLException, ClassNotFoundException{
//        Stocks stocks = new Stocks();
//        stocks = (Stocks) JAXBUtil.unmarshal(stocks.getClass(), domResult.getNode());
//        StockDAO stockDAO = new StockDAO();
//        for (Stock stock : stocks.getStock()) {
//   //        System.out.println(stock.getSymbol()+"-"+stock.getPe()+"-"+stock.getEps()+"-"+stock.getRoa()+"-"+stock.getRoe()
//  //          +"-"+stock.getBeta());
//  //        System.out.println(stock.getMarketCap());
//            
////            System.out.println(stock.getExchange());
//              stockDAO.insertStock(stock);
//              
//            //insertStock(stockDAO, stock);
//        }
//            
//        
//    }
//    public void saveDomResultToDatabaseName(DOMResult domResult)throws JAXBException, SQLException, ClassNotFoundException{
//        Stocks stocks = new Stocks();
//        stocks = (Stocks) JAXBUtil.unmarshal(stocks.getClass(), domResult.getNode());
//        StockDAO stockDAO = new StockDAO();
//        for (Stock stock : stocks.getStock()) {
//   //        System.out.println(stock.getSymbol()+"-"+stock.getPe()+"-"+stock.getEps()+"-"+stock.getRoa()+"-"+stock.getRoe()
//  //          +"-"+stock.getBeta());
//  //        System.out.println(stock.getMarketCap());
//            
//           
//             
//              
//            stockDAO.insertName(stock);
//        }
//            
//        
//    }
//    public void saveDomResultToDatabaseBank(DOMResult domResult)throws JAXBException, SQLException, ClassNotFoundException{
//        Banks banks = new Banks();
//        banks = (Banks)JAXBUtil.unmarshal(banks.getClass(), domResult.getNode());
//        BankDAO dao = new BankDAO();
//        for (Bank bank : banks.getBank()) {
//            System.out.println(bank.getName()+ " - " +bank.getOneMonth()+ " - " +bank.getThreeMonth()+ " - " +bank.getSixMonth()
//            + " - " +bank.getTwelveMonth()+ " - " +bank.getTwentyFourMonth());
//            dao.insertBank(bank);
//            
//            
//        }    
//        
//    }
//    
//   
//}

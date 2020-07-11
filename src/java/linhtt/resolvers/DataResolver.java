/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhtt.resolvers;


import java.io.Serializable;
import linhtt.generated.shoes.ListShoes;
import linhtt.generated.shoes.Shoes;
import java.sql.SQLException;
import javax.xml.bind.JAXBException;
import javax.xml.transform.dom.DOMResult;
import linhtt.utils.JAXBUtil;

/**
 *
 * @author dungntmse63284 
 */
public class DataResolver implements Serializable {
    public void saveDomResultToDatabase(DOMResult domResult)throws JAXBException, SQLException, ClassNotFoundException{
        ListShoes lShoes = new ListShoes();
        lShoes = (ListShoes) JAXBUtil.unmarshal(lShoes.getClass(), domResult.getNode());
//        StockDAO shoesDAO = new StockDAO();
        for (Shoes shoes : lShoes.getShoes()) {
           System.out.println(shoes.getBrand()+"-"+shoes.getGender()+"-"+shoes.getDescription()+"-"+shoes.getImgSrc()+"-"+shoes.getLinkProduct()
            +"-"+shoes.getName());
  //        System.out.println(shoes.getMarketCap());
            
//            System.out.println(shoes.getExchange());
//              shoesDAO.insertStock(shoes);
              
            //insertStock(shoesDAO, shoes);
        }
            
        
    }
//    public void saveDomResultToDatabaseName(DOMResult domResult)throws JAXBException, SQLException, ClassNotFoundException{
//        Stocks shoess = new Stocks();
//        shoess = (Stocks) JAXBUtil.unmarshal(shoess.getClass(), domResult.getNode());
//        StockDAO shoesDAO = new StockDAO();
//        for (Stock shoes : shoess.getStock()) {
//   //        System.out.println(shoes.getSymbol()+"-"+shoes.getPe()+"-"+shoes.getEps()+"-"+shoes.getRoa()+"-"+shoes.getRoe()
//  //          +"-"+shoes.getBeta());
//  //        System.out.println(shoes.getMarketCap());
//            
//           
//             
//              
//            shoesDAO.insertName(shoes);
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
    
   
}

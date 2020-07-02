/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhtt.constants;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author macos
 */
public class Sources {
    public static final String TOUR = "https://clevertech.vn";
    public static final String PACKED_GENERATED_NAME = "java.linhtt.generated";
    private static final String TRONGNUOC_XSL_PATH = "WEB-INF\\xsl\\phoneClevertech.xsl";

    
    public static final List<String> SOURCES = Arrays.asList(
        TOUR
    );
    
    public static final List<String> SCHEMAS = Arrays.asList(
        "web\\WEB-INF\\xsd\\mobilephone.xsd",
        "web\\WEB-INF\\xsd\\mobilephones.xsd"
            
    );
    
    public static final List<String> STYLE_SHEETS = Arrays.asList(
        TRONGNUOC_XSL_PATH
    );
    
    public static final String XML_PATH="WEB-INF\\clevertech.xml";
}

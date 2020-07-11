/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhtt.constants;

import java.util.Arrays;
import java.util.List;

public class Sources {
    
    public static final String XML_PATH = "WEB-INF\\assets\\configs\\xml\\shoes.xml";
    public static final String PACKED_GENERATED_NAME = "java.linhtt.generated";
    private static final String GIAYTOT_XSL_PATH = "WEB-INF\\xsl\\hudo.xsl";
    private static final String TIMAN_XSL_PATH = "WEB-INF\\xsl\\timan.xsl";

    public static final List<String> SCHEMAS = Arrays.asList(
            "web\\WEB-INF\\xsd\\shoes.xsd",
            "web\\WEB-INF\\xsd\\listShoes.xsd"
    );
    
    public static final List<String> SCHEMAS_GIAYTOT = Arrays.asList(
            "web\\WEB-INF\\xsd\\giaytot.xsd",
            "web\\WEB-INF\\xsd\\giaytots.xsd"
    );

    public static final List<String> STYLE_SHEETS_GIAYTOT = Arrays.asList(
            GIAYTOT_XSL_PATH
    );
    
    public static final List<String> STYLE_SHEETS_TIMAN = Arrays.asList(
            TIMAN_XSL_PATH
    );

}

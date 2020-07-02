/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhtt.utils;

import com.sun.codemodel.JCodeModel;
import com.sun.tools.xjc.api.S2JJAXBModel;
import com.sun.tools.xjc.api.SchemaCompiler;
import com.sun.tools.xjc.api.XJC;
import java.io.File;
import java.io.IOException;
import org.w3c.dom.Node;
import java.io.Serializable;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import linhtt.constants.Sources;
import org.w3c.dom.Node;
import linhtt.handlers.JAXBValidationHandler;
import org.xml.sax.InputSource;

/**
 *
 * @author TranLinh
 */
public class JAXBUtil implements Serializable {
       public static void generateClassFromSchema(String filePath, String location) throws IOException {
        SchemaCompiler schemaCompiler = XJC.createSchemaCompiler();
        schemaCompiler.forcePackageName(Sources.PACKED_GENERATED_NAME + location);

        File schema = new File(filePath);
        InputSource inputSource = new InputSource(schema.toURI().toString());
        schemaCompiler.parseSchema(inputSource);
        S2JJAXBModel mode = schemaCompiler.bind();
        JCodeModel code = mode.generateCode(null, null);

        String sourcePackage = "src";
        code.build(new File(sourcePackage));
    }
    
    public static Object unmarshal(Class<?> objClass, Node node) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(objClass);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        unmarshaller.setEventHandler(new JAXBValidationHandler());
        Object result = unmarshaller.unmarshal(node);

        return result;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhtt.generator;

import linhtt.constants.Sources;
import java.io.IOException;
import linhtt.utils.JAXBUtil;

/**
 *
 * @author TranLinh
 */
public class XMLGiaytotGenerator {
    public static void main(String[] args) {
        System.out.println("------------------------------------------");
        System.out.println("START GENERATE PROCESS");
        for (String schema : Sources.SCHEMAS_GIAYTOT) {
            System.out.println("Starting to generate file: " + schema);
            try {
                JAXBUtil.generateClassFromSchema(schema, "");
                System.out.println("Object generated");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("XMLGiaytotGenerator: " + e);
            }
            System.out.println();
        }
        System.out.println("END GENERATE PROCESS");
        System.out.println("------------------------------------------");
    }
}

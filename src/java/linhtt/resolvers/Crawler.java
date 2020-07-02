/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhtt.resolvers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import linhtt.utils.XmlUtils;

/**
 *
 * @author TranLinh
 */
public class Crawler implements Serializable {

    public static List<DOMResult> crawlPaginatedSite(
            String xmlConfigPath,
            String xslPath,
            String hrefName,
            int totalPage
    ) throws IOException, TransformerException, XPathExpressionException {

        InputStream xmlInputStream = new FileInputStream(xmlConfigPath);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        DOMResult domResult = new DOMResult();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.transform(new StreamSource(xmlInputStream), domResult);

        Document document = (Document) domResult.getNode();
        Node networkOperatorNode = document.getChildNodes().item(0);
        String href = networkOperatorNode.getAttributes().getNamedItem(hrefName).getNodeValue();

        StreamSource xslStreamSource = new StreamSource(xslPath);
        UltimateURIResolver ultimateURIResolver = new UltimateURIResolver();
        transformerFactory.setURIResolver(ultimateURIResolver);
        transformer = transformerFactory.newTransformer(xslStreamSource);

        ArrayList<DOMResult> domResults = new ArrayList<>(totalPage);
        for (int i = 1; i <= totalPage; i++) {
            domResult = new DOMResult();
            /* Update href attribute */
            String newHref = href + i;
            networkOperatorNode.getAttributes().getNamedItem(hrefName).setNodeValue(newHref);

            InputStream inputStream = XmlUtils.parseDocumentToInputStream(document);
            StreamSource streamSource = new StreamSource(inputStream);
            transformer.transform(streamSource, domResult);

            domResults.add(domResult);
        }

        return domResults;
    }

    public static DOMResult crawl(String xmlPath, String xslPath)
            throws FileNotFoundException, TransformerException {
        StreamSource xslCate = new StreamSource(xslPath);
        InputStream inputStream = new FileInputStream(xmlPath);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        DOMResult domResult = new DOMResult();
        UltimateURIResolver resolver = new UltimateURIResolver();
        transformerFactory.setURIResolver(resolver);
        Transformer transformer = transformerFactory.newTransformer(xslCate);
        StreamSource streamSource = new StreamSource(inputStream);
        transformer.transform(streamSource, domResult);
        return domResult;
    }
}

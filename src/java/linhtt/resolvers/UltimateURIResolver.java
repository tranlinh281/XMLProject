/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhtt.resolvers;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.URIResolver;
import javax.xml.transform.stream.StreamSource;
import java.net.URLConnection;
import linhtt.utils.UTF8StringHelper;

/**
 *
 * @author TranLinh
 */
public class UltimateURIResolver implements URIResolver, Serializable {
//
//    static {
//        java.lang.System.setProperty("https.protocols", "TLSv1, TLSv1.1, TLSv1.2");
//    }

    @Override
    public Source resolve(String href, String base) throws TransformerException {
        System.out.println("reso;");
        if (href != null) {
            try {
                //     URLConnection urlConnection = (URLConnection) new URL(href).openConnection();
                URLConnection urlConnection = (URLConnection) new URL(href).openConnection();
                StreamSource streamSource;

//                SSLContext sc = SSLContext.getInstance("SSL");
//                sc.init(null, new TrustManager[]{new AnyTrustManager()}, new java.security.SecureRandom());
//                urlConnection.setSSLSocketFactory(sc.getSocketFactory());
                try (InputStream http = urlConnection.getInputStream()) {

                    streamSource = preProcessInputStream(http);
                    return streamSource;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private StreamSource preProcessInputStream(InputStream http) throws IOException {
        String httpString = "<html>" + parseInputStreamToString(http) + "</html>";

        httpString = httpString.replaceAll("\n", "");
        httpString = httpString.replaceAll("&nbsp;", "");
        httpString = getTag(httpString, "body");
        httpString = removeTag(httpString, "script");
        httpString = removeTag(httpString, "g:plusone");
        httpString = removeTag(httpString, "style");
        httpString = removeComment(httpString);

        XmlSyntaxChecker xmlRefiner = new XmlSyntaxChecker();
        httpString = xmlRefiner.check(httpString);
        http = parseStringToInputStream(httpString);
        System.out.println(httpString);
        
//          try {
//            FileWriter test = new FileWriter("D:\\testname.txt");
//        test.write(httpString);
//        test.close();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            
//        }
         String utf8HttpString = UTF8StringHelper.convertFromUTF8(httpString);
        //
        http = parseStringToInputStream(utf8HttpString);
        return new StreamSource(http);
    }

    private String removeComment(String source) {
        String expression = "<!--.*?-->";
        return source.replaceAll(expression, "");
    }

    private String removeTag(String source, String tagName) {
        String expression = "<" + tagName + ".*?</" + tagName + ">";
        return source.replaceAll(expression, "");
    }

    private String getTag(String source, String tagName) {
        String result = source;
        String expression = "<" + tagName + ".*?</" + tagName + ">";
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(source);

        if (matcher.find()) {
            result = matcher.group(0);
        }

        return result;
    }

    private static String parseInputStreamToString(InputStream inputStream) throws IOException {
        String line;
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }
        }

        return stringBuilder.toString();
    }

    private static InputStream parseStringToInputStream(String string) {
        return new ByteArrayInputStream(string.getBytes());
    }

    private static boolean isStringStartWithListString(List<String> stringList, String parentString) {
        for (String string : stringList) {
            if (parentString.startsWith(string)) {
                return true;
            }
        }

        return false;
    }
}

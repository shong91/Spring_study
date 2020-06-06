package etc.mashalling;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class XMLParsing_test {
    public static void main(String[] args) {
        String url ="D:/Github_repo/Spring_study_/src/main/java/etc/mashalling/practice03.xml";

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(url);

            //root tag
            doc.getDocumentElement().normalize();

            //파싱할 tag
            NodeList nList = doc.getElementsByTagName("item");
            System.out.println(nList.getLength());
            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    System.out.println("#######################");
                    System.out.println("item:"  + getTagValue("a", element));
                }

            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }

    }

    private static String getTagValue(String tag, Element element) {
        NodeList nList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = nList.item(0);
        if(nValue == null) {
            return null;
        }
        return nValue.getNodeValue();
    }
}

// https://blog.naver.com/PostView.nhn?blogId=nonamed0000&logNo=220988048654&categoryNo=12&parentCategoryNo=0&viewDate=&currentPage=1&postListTopCurrentPage=1&from=postView
// https://kingbbode.tistory.com/6?category=737338
// https://www.inflearn.com/questions/16184
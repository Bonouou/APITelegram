/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api_telegram;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author bonuglia_gabriele
 */
public class ParseXml {
    Document document;
    Place p = new Place();

    public ParseXml() {
    }
    
    
    public Place parseDocument(String filename) throws ParserConfigurationException, SAXException, IOException {
        
        DocumentBuilderFactory factory;
        DocumentBuilder builder;
        Element root, element;
        NodeList nodeList;
        
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();

        document = builder.parse(filename);
  
        root = document.getDocumentElement();
        nodeList = root.getElementsByTagName("place");
        for (int temp = 0; temp < nodeList.getLength(); temp++) {
            Node node = nodeList.item(temp);
            System.out.println("");  
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                
                  System.out.println("lat: "    + eElement.getAttribute("lat"));
                  System.out.println("lon : "    + eElement.getAttribute("lon"));
                  p = new Place(Float.parseFloat(eElement.getAttribute("lat")),Float.parseFloat(eElement.getAttribute("lon")));
                  break;
            }
        }
        return p;
    }
    
    public Document getDocument() {
        return document;
    }
}

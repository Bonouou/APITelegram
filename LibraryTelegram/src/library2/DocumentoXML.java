/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library2;

import java.io.IOException;
import org.w3c.dom.Element;
//import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 *
 * @author gabri
 */
public class DocumentoXML {
     Document document;
    DocumentBuilderFactory factory;
    DocumentBuilder builder;
    Element root, element;
    NodeList nodelist;
    
    public Coordinate GetCoordinate(String filename) throws ParserConfigurationException, SAXException, IOException
    {
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        
        document = builder.parse(filename);
        root = document.getDocumentElement();
        Element element = (Element) root.getElementsByTagName("place").item(0); 
        Coordinate place = new Coordinate(Float.parseFloat(element.getAttribute("lat")), Float.parseFloat(element.getAttribute("lon")));
        return place;
    }
}

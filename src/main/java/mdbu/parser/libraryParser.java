package mdbu.parser;
import org.w3c.dom.*;
import org.xml.sax.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class libraryParser {


    public static void main(String[] args){

        Document xmlDoc = getDocument("library1.xml");
        //plist
        System.out.println("Root :" + xmlDoc.getDocumentElement().getNodeName());

        NodeList listOfPlaylists = xmlDoc.getElementsByTagName("dict");

        //System.out.println("Number of playlists: " + listOfPlaylists.getLength());

        String elementName = "key";

        getElement(listOfPlaylists, elementName);
    }

    private static void getElement(NodeList listOfPlaylists, String elementName) {
        try {

            for(int i=0; i<listOfPlaylists.getLength(); i++) {
                Node dictNode = listOfPlaylists.item(i);
                System.out.println("\nCurrent Element: " + dictNode.getNodeName());
                Element dictElement = (Element) dictNode;
                NodeList keyList = dictElement.getElementsByTagName(elementName);
                    Element keyElement = (Element) keyList.item(0);

                    NodeList elementList = keyElement.getChildNodes();

                    System.out.println(elementName + " : " +
                            ((Node) elementList.item(0)).getNodeValue().trim());

                }


        } catch (Exception ex){

            System.out.println(ex.getMessage());
    }
    }

    private static Document getDocument(String docString) {

        //trying to access a file that might not exist
        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringComments(true);
            factory.setIgnoringElementContentWhitespace(true);
            factory.setValidating(true);

            DocumentBuilder builder = factory.newDocumentBuilder();

            return builder.parse(new InputSource(docString));


        } catch (Exception ex){

            System.out.println(ex.getMessage());

        }

        return null;
    }
}
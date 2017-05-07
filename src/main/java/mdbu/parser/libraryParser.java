package mdbu.parser;

import mdbu.entities.Track;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.Collection;

public class libraryParser {

    public static Collection<Track> parseTrack(String docString) {

        Document xmlDoc = getDocument(docString);

        //ROOT - plist
        Node root = xmlDoc.getDocumentElement();
        //System.out.println("Root :" + root.getNodeName());

        //First child of root is the outermost dict - librarydict
        Node libraryDict = root.getFirstChild();
        //System.out.println("Library Dict: " + libraryDict.getNodeName());
        //System.out.println("Number of Children: " + libraryDict.getChildNodes().getLength());


        //libraryDict child Noders are key Nodes - Major version, Tracks, Playlist, Array etc
        NodeList libraryDictKeys = libraryDict.getChildNodes();

        Collection<Track> tracks = new ArrayList<Track>();

        //First dict children
        //+2 for just key nodes


        //Following loop contains, tracks, Playlist & Library Persistent ID !!! START HERE

        String library_id = null;

        for (int i = 0; i < libraryDictKeys.getLength(); i += 2) {


            //print all keys in first dict
            Node libraryDataNode = libraryDictKeys.item(i).getFirstChild();
            //print value nodes - string, date, integer etc
            Node libraryDataValueNode = libraryDictKeys.item(i + 1).getFirstChild();
            //    System.out.println("Library Data key: " + libraryDataNode.getNodeValue());


            if (libraryDataNode.getNodeValue().equals("Library Persistent ID")) {
                //     System.out.println(libraryDataNode.getNodeValue() + " : " + libraryDataValueNode.getNodeValue());

                library_id = libraryDataValueNode.getNodeValue();


            }

            if (libraryDataNode.getNodeValue().equals("Tracks")) {

                Integer track_id = null;
                String name = null;
                String artist = null;
                String album = null;
                String genre = null;

                NodeList trackDicts = libraryDictKeys.item(i + 1).getChildNodes();


                //loop for inner dict
                // returns Track key (Track ID)
                for (int j = 0; j < trackDicts.getLength(); j += 2) {


                    Node trackIDKey = trackDicts.item(j).getFirstChild();
                    track_id = Integer.valueOf(trackIDKey.getNodeValue());


                    NodeList trackDataKeys = trackDicts.item(j + 1).getChildNodes();


                    for (int k = 0; k < trackDataKeys.getLength(); k += 2) {
                        Node trackKey = trackDataKeys.item(k).getFirstChild();
                        Node trackValue = trackDataKeys.item(k + 1).getFirstChild();


                        if (trackKey.getNodeValue().equals("Name")) {
                            name = trackValue.getNodeValue();
                        }
                        if (trackKey.getNodeValue().equals("Artist")) {
                            artist = trackValue.getNodeValue();
                        }
                        if (trackKey.getNodeValue().equals("Album")) {
                            album = trackValue.getNodeValue();
                        }
                        if (trackKey.getNodeValue().equals("Genre")) {
                            genre = trackValue.getNodeValue();
                        }


                    }

                    Track newTrack = new Track(track_id, name, artist, album, genre, library_id);

                    tracks.add(newTrack);

                }

            }

        }

       // for (Track t : tracks) {
       //     System.out.print(t);
       //     System.out.println("");
      //  }

        return tracks;
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


        } catch (Exception ex) {

            System.out.println(ex.getMessage());

        }

        return null;
    }

}
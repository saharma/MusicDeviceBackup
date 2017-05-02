package mdbu.parser;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class libraryParser {

    public static void main(String[] args) {

        Document xmlDoc = getDocument("library1.xml");

        //ROOT - plist
        Node root = xmlDoc.getDocumentElement();
        System.out.println("Root :" + root.getNodeName());

        //First child of root is the outermost dict - librarydict
        Node libraryDict = root.getFirstChild();
        System.out.println("Library Dict: " + libraryDict.getNodeName());
        System.out.println("Number of Children: " + libraryDict.getChildNodes().getLength());


        //libraryDict child Noders are key Nodes - Major version, Tracks, Playlist, Array etc
        NodeList libraryDictKeys = libraryDict.getChildNodes();

        //First dict children
        //+2 for just key nodes


        //Following loop contains, tracks, playlist & library Persistent ID !!! START HERE
        for (int i = 0; i < libraryDictKeys.getLength(); i += 2) {

            //print all keys in first dict
            Node libraryDataNode = libraryDictKeys.item(i).getFirstChild();
            //print value nodes - string, date, integer etc
            Node libraryDataValueNode = libraryDictKeys.item(i + 1).getFirstChild();
            System.out.println("Library Data key: " + libraryDataNode.getNodeValue());


            if (libraryDataNode.getNodeValue().matches("Show Content Ratings")) {

                System.out.println("Value: " + libraryDictKeys.item(i + 1).getNodeName());
            } else
                System.out.println("Value: " + libraryDataValueNode.getNodeValue());

            //XML file contains Tracks and Playlists so must create if's for each one

            //create tracks list - Tracks - dict - keys w/ details


            if (libraryDataNode.getNodeValue().equals("Tracks")) {
                NodeList trackDicts = libraryDictKeys.item(i + 1).getChildNodes();


                //loop for inner dict
                // returns track key (track ID)
                for (int j = 0; j < trackDicts.getLength(); j += 2) {
                    Node trackIDKey = trackDicts.item(j).getFirstChild();
                    System.out.println("Track ID: " + trackIDKey.getNodeValue());

                    NodeList trackDataKeys = trackDicts.item(j + 1).getChildNodes();
                    //track data dict
                    //       System.out.println("Value: " + trackDicts.item(j+1).getNodeName());

                    //3rd dict - track details
                    for (int k = 0; k < trackDataKeys.getLength(); k += 2) {
                        Node trackKey = trackDataKeys.item(k).getFirstChild();
                        Node trackValue = trackDataKeys.item(k + 1).getFirstChild();
                        System.out.println("Track Key: " + trackKey.getNodeValue());


                        //according to error messages for boolean values


                        //Values
                        if (trackKey.getNodeValue().matches("Compilation|Purchased|Album Rating Computed|Has Video|HD|Disabled|Explicit")) {

                            System.out.println("Value: " + trackDataKeys.item(k + 1).getNodeName());
                        } else
                            System.out.println("Value: " + trackValue.getNodeValue());

                    }
                    System.out.println(" ");
                }

            }

            if (libraryDataNode.getNodeValue().equals("Playlists")) {
                NodeList playListDict = libraryDictKeys.item(i + 1).getChildNodes();
                //  Node first = libraryDictKeys.item(i+1).getFirstChild();
                //  System.out.println("Node Name: " + first.getNodeName()); returns dict
                //  System.out.println("Number of dicts: " + first.getChildNodes().getLength());


                //plus one, no values nodes to follow
                for (int l = 0; l < playListDict.getLength(); l += 1) {
                    NodeList playListKeys = playListDict.item(l).getChildNodes();


                    for (int m = 0; m < playListKeys.getLength(); m += 2) {
                        NodeList playlistTracksDict = playListKeys.item(m+1).getChildNodes();
                        Node playListKey = playListKeys.item(m).getFirstChild();
                        Node playlistValue = playListKeys.item(m + 1).getFirstChild();
                        System.out.println("Playlist Key: " + playListKey.getNodeValue());



                           if (playListKey.getNodeValue().matches("Music|All Items|Movies|TV Shows|Purchased Music|Party Shuffle|Master|Visible")) {
                                System.out.println("Not regular Playlist Key Value: " + playListKeys.item(m + 1).getNodeName());

                            }

                            else if (playListKey.getNodeValue().equals("Playlist Items")) {
                               //in playlistTrack Dict
                               for(int n=0; n < playlistTracksDict.getLength(); n+=1){
                                   NodeList playlistTrackKeys = playlistTracksDict.item(n).getChildNodes();


                               //playlistTrack keys
                                   for(int p=0; p< playlistTrackKeys.getLength(); p +=2) {
                                       //    Node playlistTrackKey = playlistTrackKeys.item(p).getFirstChild();
                                           Node playlistTrackValue = playlistTrackKeys.item(p+1).getFirstChild();
                                           System.out.println("Playlist Track: " + playlistTrackValue.getNodeValue());


                                   }

                               }

                           } else

                                System.out.println("Regular Playlist Key Value: " + playlistValue.getNodeValue());

                        }

                    System.out.println("");



                    }
                }
            }
            System.out.println("End of file");
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
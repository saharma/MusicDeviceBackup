package mdbu.parser;

import mdbu.entities.track;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.Collection;

public class libraryParser {

    public static void main(String[] args) {

        Document xmlDoc = getDocument("library1.xml");

        //ROOT - plist
        Node root = xmlDoc.getDocumentElement();
        //System.out.println("Root :" + root.getNodeName());

        //First child of root is the outermost dict - librarydict
        Node libraryDict = root.getFirstChild();
        //System.out.println("Library Dict: " + libraryDict.getNodeName());
        //System.out.println("Number of Children: " + libraryDict.getChildNodes().getLength());


        //libraryDict child Noders are key Nodes - Major version, Tracks, Playlist, Array etc
        NodeList libraryDictKeys = libraryDict.getChildNodes();

        Collection<track> tracks = new ArrayList<track>();

        track track = new track();
        //First dict children
        //+2 for just key nodes


        //Following loop contains, tracks, playlist & library Persistent ID !!! START HERE

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
                track.setLibrary_ID(library_id);
            }

            if (libraryDataNode.getNodeValue().equals("Tracks")) {
                Integer track_id = null;
                NodeList trackDicts = libraryDictKeys.item(i + 1).getChildNodes();


                //loop for inner dict
                // returns track key (track ID)
                for (int j = 0; j < trackDicts.getLength(); j += 2) {
                    tracks.add(track);
                    Node trackIDKey = trackDicts.item(j).getFirstChild();
                    track_id = Integer.valueOf(trackIDKey.getNodeValue());
                    track.setTrack_id(track_id);


                    NodeList trackDataKeys = trackDicts.item(j + 1).getChildNodes();


                    for (int k = 0; k < trackDataKeys.getLength(); k += 2) {
                        Node trackKey = trackDataKeys.item(k).getFirstChild();
                        Node trackValue = trackDataKeys.item(k + 1).getFirstChild();

                        String name = null;
                        String artist = null;
                        String album = null;
                        String genre = null;
                        Integer size = null;
                        Integer year = null;

                        if (trackKey.getNodeValue().equals("Name")) {
                            name = trackValue.getNodeValue();
                            track.setName(name);
                        }
                        if (trackKey.getNodeValue().equals("Artist")) {
                            artist = trackValue.getNodeValue();
                            track.setArtist(artist);
                        }
                        if (trackKey.getNodeValue().equals("Album")) {
                            album = trackValue.getNodeValue();
                            track.setAlbum(album);
                        }
                        if (trackKey.getNodeValue().equals("Genre")) {
                            genre = trackValue.getNodeValue();
                            track.setGenre(genre);
                        }
                        if (trackKey.getNodeValue().equals("Size")) {
                            size = Integer.valueOf(trackValue.getNodeValue());
                            track.setSize(size);
                        }
                        if (trackKey.getNodeValue().equals("Year")) {
                            year = Integer.valueOf(trackValue.getNodeValue());
                            track.setYear(year);
                        }











                    }


                 //   System.out.println("");
                }


            }


            if (libraryDataNode.getNodeValue().equals("Playlists")) {
                NodeList playListDict = libraryDictKeys.item(i + 1).getChildNodes();


                //plus one, no values nodes to follow
                for (int l = 0; l < playListDict.getLength(); l += 1) {
                    NodeList playListKeys = playListDict.item(l).getChildNodes();


                    for (int m = 0; m < playListKeys.getLength(); m += 2) {
                        NodeList playlistTracksDict = playListKeys.item(m + 1).getChildNodes();
                        Node playListKey = playListKeys.item(m).getFirstChild();
                        Node playlistValue = playListKeys.item(m + 1).getFirstChild();
                        if (playListKey.getNodeValue().matches("Name|Playlist Persistent ID")) {

                    //        System.out.println(playListKey.getNodeValue() + ":" + playlistValue.getNodeValue());


                        } else if (playListKey.getNodeValue().equals("Playlist Items")) {
                            //in playlistTrack Dict
                            for (int n = 0; n < playlistTracksDict.getLength(); n += 1) {
                                NodeList playlistTrackKeys = playlistTracksDict.item(n).getChildNodes();


                                //playlistTrack keys
                                for (int p = 0; p < playlistTrackKeys.getLength(); p += 2) {
                                    //    Node playlistTrackKey = playlistTrackKeys.item(p).getFirstChild();
                                    Node playlistTrackValue = playlistTrackKeys.item(p + 1).getFirstChild();
                            //        System.out.println("Playlist Track: " + playlistTrackValue.getNodeValue());


                                }

                            }
                        }
                    }


               //     System.out.println("");


                }
            }

        }



        for(track t: tracks){
            System.out.print(t);
            System.out.println("");}



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
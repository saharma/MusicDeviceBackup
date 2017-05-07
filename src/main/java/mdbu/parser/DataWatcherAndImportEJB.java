package mdbu.parser;

import mdbu.entities.Track;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.RollbackException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import javax.ws.rs.NotSupportedException;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Collection;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

/**
 * Created by saharmohamedali on 06/05/2017.
 */

@Startup
@Singleton
public class DataWatcherAndImportEJB {
    @PersistenceContext EntityManager em;
    @Resource
    SessionContext sessionContext;
    boolean watch = true;

    @PostConstruct
    public void initialise(){
        Runnable run = () -> {
            try {
                WatchService watcher = FileSystems.getDefault().newWatchService();
                Path dir = Paths.get("/res/incoming_data");
                dir = Paths.get("/Users/saharmohamedali/Git_Repository/MusicDeviceBackUp/res/incoming_data");
                dir.register(watcher, ENTRY_CREATE);
                System.out.println("Watch Service registered for dir: " + dir.getFileName());

                while(watch){
                    WatchKey key;
                    try{
                        key = watcher.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return;
                    }
                    for (WatchEvent<?> event : key.pollEvents()) {
                        // get event type
                        WatchEvent.Kind<?> kind = event.kind();

                        // get file name
                        @SuppressWarnings("unchecked")
                        WatchEvent<Path> ev = (WatchEvent<Path>) event;
                        Path fileName = ev.context();
                        System.out.println(kind.name() + ": " + fileName);

                        if (kind == OVERFLOW) {
                            continue;
                        }
                        else if (kind == ENTRY_CREATE) {
                            String relPathToFilename = dir.toString() + "/" + fileName.toString();
                            File f = new File(relPathToFilename);
                            f.setWritable(true);

                            if(fileName.toString().endsWith(".xml")){
                                System.out.println("Found XML File");
                                long startTime = System.currentTimeMillis();
                                Collection<Track> tobePersisted = libraryParser.parseTrack(relPathToFilename);
                                System.out.println("Persisting Entities");
                                for(Track track: tobePersisted){

                                    try {
                                        UserTransaction uTx = sessionContext.getUserTransaction();
                                        try {
                                            uTx.begin();
                                        } catch (javax.transaction.NotSupportedException e) {
                                            e.printStackTrace();
                                        }
                                        em.persist(track);
                                        try {
                                            uTx.commit();
                                        } catch (javax.transaction.RollbackException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    catch(RollbackException e ){
                                        System.out.println("Transaction Rollback. Probably Foreign Key Constraint error");
                                    }

                                }

                                System.out.println("Persistence completed in: " +
                                        ((System.currentTimeMillis() - startTime) / 1000) + "s");
                            }
                        }
                    }
                    // IMPORTANT: The key must be reset after processed
                    boolean valid = key.reset();
                    if (!valid) {
                        break;
                    }
                    Thread.sleep(1000);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (NotSupportedException e) {
                e.printStackTrace();
            } catch (SystemException e) {
                e.printStackTrace();

            } catch (HeuristicMixedException e) {
                e.printStackTrace();
            } catch (HeuristicRollbackException e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(run);
        thread.setDaemon(true);
        thread.start();
    }

    @PreDestroy
    public void destroy(){
        watch = false;
    }
}
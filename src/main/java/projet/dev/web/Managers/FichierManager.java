package projet.dev.web.Managers;

import projet.dev.web.Dao.SonDao;
import projet.dev.web.Entities.Son;

import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Properties;

public class FichierManager {
    private static class FichierManagerHolder{
        private static FichierManager instance = new FichierManager();
    }
    private SonDao sonDao;
    public static FichierManager getInstance()

    {
        return FichierManagerHolder.instance;
    }
    private FichierManager(){
        sonDao = new SonDao();
        Properties configProperties = new Properties();
        try (InputStream configFileStream = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            configProperties.load(configFileStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        repertoirePrincipal=configProperties.getProperty("repertoirePrincipal");
    }
    private String repertoirePrincipal;

    public Son saveNewSon(Son son, Part sonPart) throws IOException, SQLException {
        Path dir = Paths.get(repertoirePrincipal, "src/main/webapp", "Sons", son.getStreamer().getNom());
        if (Files.notExists(dir) ){
                Files.createDirectories(dir);
        }
        String nomFichier;
        if(son.getId()!=0){
            nomFichier=son.getDescription()+String.valueOf(son.getId())+".mp3";
        }else {
            nomFichier = son.getDescription() + String.valueOf(sonDao.getNbSons()) + ".mp3";
        }
        son.setChemin("Sons/"+son.getStreamer().getNom()+"/"+nomFichier);
        sonPart.write(Paths.get(dir.toString(),nomFichier).toString());
        return son;
        }
    public void modifyExistingSon(Son son , Part sonPart)throws IOException, SQLException{
        Path dir = Paths.get(repertoirePrincipal, "src/main/webapp",son.getCheminSon());
        Files.deleteIfExists(dir);
        son=saveNewSon(son,sonPart);
        sonDao.modifierSon(son);
    }


}

package projet.dev.web.Dao;


import projet.dev.web.Entities.Son;
import projet.dev.web.Entities.Streamer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import projet.dev.web.Managers.SonManager;

import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

public class SonDaoTest {
    @Before
    public void init() throws Exception {
        try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DELETE FROM sons");
            stmt.executeUpdate("DELETE FROM streamers");
            stmt.executeUpdate("INSERT INTO streamers(IdStreamer,Description,CheminImage,Nom) VALUES (1,'le streamer de feu','Images/Zerator.jpg','Zerator')");
            stmt.executeUpdate("INSERT INTO streamers(IdStreamer,Description,CheminImage,Nom) VALUES (2,'le vietnamo-moldave','Images/MisterMV.jpg','MisterMV')");
            stmt.executeUpdate(
                    "INSERT INTO sons(IdSon,Chemin, Description, Date, IdStreamer, supprime) "
                            + "VALUES (1, 'Sons/Zerator/Zerator1.mp3', 'son 1', '2017-10-06', 1, 0)");
            stmt.executeUpdate(
                    "INSERT INTO sons(IdSon,Chemin, Description, Date, IdStreamer, supprime) "
                            + "VALUES (2, 'Sons/MisterMV/MisterMV1.mp3','son 2','2014-10-26', 2, 0)");
            connection.close();
        }
    }

    @Test
    public void shouldCountSons(){
        try {
            init();
            assertThat(new SonDao().getNbSons()==2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void shouldListSons() {
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Son> sons = SonManager.getInstance().getSons();
        assertThat(sons).hasSize(2);
        assertThat(sons).extracting("id", "description", "date", "cheminSon", "streamer.nom", "streamer.cheminImage", "streamer.id", "streamer.description").containsOnly(
                tuple(1, "son 1", LocalDate.of(2017, 10, 6), "Sons/Zerator/Zerator1.mp3", "Zerator", "Images/Zerator.jpg", 1, "le streamer de feu"),
                tuple(2, "son 2", LocalDate.of(2014, 10, 26), "Sons/MisterMV/MisterMV1.mp3", "MisterMV", "Images/MisterMV.jpg", 2, "le vietnamo-moldave")
        );

    }

    @Test
    public void shouldAddSon() {
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String chemin ="Test" ;
        Son son = SonManager.getInstance().addSon(new Son("son 3", 0,
                LocalDate.of(2017, 10, 24),
                chemin,
                new Streamer(1, "le streamer de feu", "Images/Zerator.jpg", "Zerator"),false));
        assertThat(son.getId() != 0);
        List<Son> sons = SonManager.getInstance().getSons();
        assertThat(sons).hasSize(3);
        assertThat(sons).extracting("description", "date", "cheminSon", "streamer.nom", "streamer.cheminImage", "streamer.id", "streamer.description").containsOnly(
                tuple("son 1", LocalDate.of(2017, 10, 6), "Sons/Zerator/Zerator1.mp3", "Zerator", "Images/Zerator.jpg", 1, "le streamer de feu"),
                tuple("son 2", LocalDate.of(2014, 10, 26), "Sons/MisterMV/MisterMV1.mp3", "MisterMV", "Images/MisterMV.jpg", 2, "le vietnamo-moldave"),
                tuple("son 3", LocalDate.of(2017, 10, 24), "Test", "Zerator", "Images/Zerator.jpg", 1, "le streamer de feu")
        );
    }

@Test
    public void shouldGetSon(){
        Son son = SonManager.getInstance().getSon(1);
        assertThat(son.getId()==1);
        assertThat(son.getCheminSon().equals("Sons/Zerator/Zerator1.mp3"));
        assertThat(son.getDate().equals(LocalDate.of(2017,10, 6)));
        assertThat(son.getDescription().equals("son 1"));
        assertThat(son.getStreamer().getId()==1);
    }

    @Test
    public void shouldDeleteSon(){
        SonManager.getInstance().deleteSon(1);
        assertThat(SonManager.getInstance().getSon(1).isSupprime());
    }

    @Test
    public void shouldNotListDeletedSons(){
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
        SonManager.getInstance().deleteSon(2);
        List<Son> sons =SonManager.getInstance().getSons();
        assertThat(sons).hasSize(1);
        assertThat(sons).extracting("description", "date", "cheminSon", "streamer.nom", "streamer.cheminImage", "streamer.id", "streamer.description").containsOnly(
                tuple("son 1", LocalDate.of(2017, 10, 6), "Sons/Zerator/Zerator1.mp3", "Zerator", "Images/Zerator.jpg", 1, "le streamer de feu")
        );
    }

    @Test
    public void shouldModifySon(){
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String chemin = "Test";
        Son son = new Son("son 3", 2,
                LocalDate.of(2017, 10, 24),
                chemin,
                new Streamer(1, "le streamer de feu", "Images/Zerator.jpg", "Zerator"),false);
        SonManager.getInstance().modifierSon(son);
        List<Son> sons = SonManager.getInstance().getSons();
        assertThat(sons).hasSize(2);
        assertThat(sons).extracting("id", "description", "date", "cheminSon", "streamer.nom", "streamer.cheminImage", "streamer.id", "streamer.description").containsOnly(
                tuple(1, "son 1", LocalDate.of(2017, 10, 6), "Sons/Zerator/Zerator1.mp3", "Zerator", "Images/Zerator.jpg", 1, "le streamer de feu"),
                tuple(2,"son 3", LocalDate.of(2017, 10, 24), "Test", "Zerator", "Images/Zerator.jpg", 1, "le streamer de feu")
        );
    }

    @After
    public void goBackToInit(){
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

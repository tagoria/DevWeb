package projet.dev.web.Managers;

import projet.dev.web.Dao.SonDao;
import projet.dev.web.Entities.Son;

import java.util.List;

public class SonManager {


    private static class SonManagerHolder {
        private static SonManager instance = new SonManager();
    }

    private static SonDao sonDao;

    public static SonManager getInstance()

    {
        return SonManagerHolder.instance;
    }

    private SonManager() {
        sonDao = new SonDao();

    }
    public  Son getSon(int idSon){
        return sonDao.getSon(idSon);
    }
    public  List<Son> getSons(){
        return  sonDao.getListeSon();
    }
    public  Son addSon(Son son){
        return sonDao.addSon(son);
    }
    public  boolean deleteSon(int id){
        return sonDao.deleteSon(id);
    }
    public void modifierSon(Son son){
        sonDao.modifierSon(son);
    }
}

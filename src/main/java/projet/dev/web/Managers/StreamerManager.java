package projet.dev.web.Managers;

import projet.dev.web.Dao.StreamerDao;
import projet.dev.web.Entities.Streamer;

import java.util.List;

public class StreamerManager {


    private static class StreamerManagerHolder {
        private static StreamerManager instance = new StreamerManager();
    }

    private static StreamerDao streamerDao;

    public static StreamerManager getInstance()

    {
        return StreamerManager.StreamerManagerHolder.instance;
    }

    private StreamerManager() {
        streamerDao = new StreamerDao();

    }
    public List<Streamer> getStreamers(){
        return streamerDao.listeStreamer();
    }
    public Streamer getStreamer(int idStreamer){
        return  streamerDao.getStreamer(idStreamer);
    }
    public Streamer addStreamer(Streamer streamer){
        return streamerDao.addStreamer(streamer);
    }
    public Streamer getStreamerByName(String nomStreamer){
        return streamerDao.getStreamerByName(nomStreamer);
    }
}

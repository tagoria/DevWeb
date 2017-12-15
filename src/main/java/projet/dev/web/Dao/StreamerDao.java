package projet.dev.web.Dao;

import projet.dev.web.Entities.Streamer;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StreamerDao {
    public Streamer getStreamer(int id){
        DataSource dataSource = DataSourceProvider.getInstance().getDataSource();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM streamers WHERE IdStreamer=?");
            statement.setInt(1,id);
            ResultSet resultSet =statement.executeQuery();
            if(resultSet.next()){
                Streamer streamer = new Streamer(id,resultSet.getString("Description"),
                        resultSet.getString("CheminImage"),
                        resultSet.getString("Nom"));
                connection.close();
                return  streamer;
            }else{
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<Streamer> listeStreamer(){
        DataSource dataSource = DataSourceProvider.getInstance().getDataSource();
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet =statement.executeQuery("SELECT * FROM streamers ORDER BY IdStreamer asc");
            ArrayList<Streamer> streamList = new ArrayList<>();
            while(resultSet.next()){
                streamList.add(new Streamer(resultSet.getInt("IdStreamer"),
                        resultSet.getString("Description"),
                        resultSet.getString("CheminImage"),
                        resultSet.getString("Nom")));
            }
            connection.close();
            return streamList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Streamer addStreamer(Streamer streamer){
        DataSource dataSource = DataSourceProvider.getInstance().getDataSource();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement=connection.prepareStatement("INSERT INTO streamers(CheminImage,Description,Nom)"+
                    "VALUES (?,?,?)",Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,streamer.getCheminImage());
            statement.setString(2,streamer.getDescription());
            statement.setString(3,streamer.getNom());
            statement.executeUpdate();
            ResultSet resultSet =statement.getGeneratedKeys();
            if(resultSet.next()){
                streamer.setId(resultSet.getInt(1));
                connection.close();
                return streamer;
            }else{
                connection.close();
                return  null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Streamer getStreamerByName(String nomStreamer) {
        DataSource dataSource = DataSourceProvider.getInstance().getDataSource();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM streamers WHERE Nom=?");
            statement.setString(1,nomStreamer);
            ResultSet resultSet =statement.executeQuery();
            if(resultSet.next()){
                Streamer streamer = new Streamer(resultSet.getInt("IdStreamer"),resultSet.getString("Description"),
                        resultSet.getString("CheminImage"),
                        nomStreamer);
                connection.close();
                return streamer;
            }else{
                connection.close();
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

package projet.dev.web.Dao;

import projet.dev.web.Entities.Son;
import projet.dev.web.Managers.StreamerManager;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public  class SonDao {
    public List<Son> getListeSon(){
        DataSource dataSource = DataSourceProvider.getInstance().getDataSource();
        List<Son> listeSon = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            String  sqlQuery = "SELECT * FROM sons WHERE not supprime ORDER BY IdSon";
            ResultSet resultSet =statement.executeQuery(sqlQuery);

            while (resultSet.next()){
                String description = resultSet.getString("Description");
                String chemin = resultSet.getString("Chemin");
                int idStreamer = resultSet.getInt("IdStreamer");
                java.sql.Date date = resultSet.getDate("Date");
                int idSon = resultSet.getInt("IdSon");
                Son son = new Son(description,idSon,date.toLocalDate(),chemin, StreamerManager.getInstance().getStreamer(idStreamer),false);
                listeSon.add(son);
            }
            connection.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listeSon;

    }
    public Son getSon(int id){
        DataSource dataSource = DataSourceProvider.getInstance().getDataSource();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM sons WHERE IdSon=?");
            statement.setInt(1,id);
            ResultSet resultSet =statement.executeQuery();

            if (resultSet.next()){
                Son son = new Son(resultSet.getString("Description"),
                id,resultSet.getDate("Date").toLocalDate(),
                resultSet.getString("Chemin"),
                StreamerManager.getInstance().getStreamer(resultSet.getInt("IdStreamer")),
                        resultSet.getBoolean("supprime"));
                connection.close();
                return son;
            }else{
                connection.close();
                return null;
            }


        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public  Son addSon(Son son){
        DataSource dataSource = DataSourceProvider.getInstance().getDataSource();
        try {

            Connection connection = dataSource.getConnection();
            PreparedStatement statement=connection.prepareStatement("INSERT INTO sons(Chemin,Description,Date,IdStreamer,supprime)"+
                    "VALUES (?,?,?,?,0)",Statement.RETURN_GENERATED_KEYS);
            statement.setInt(4,son.getStreamer().getId());
            statement.setString(1,son.getCheminSon());
            statement.setString(2,son.getDescription());
            statement.setDate(3,java.sql.Date.valueOf(son.getDate()));
            statement.executeUpdate();
            ResultSet resultSet =statement.getGeneratedKeys();
            if(resultSet.next()){
                son.setId(resultSet.getInt(1));
                connection.close();
                return son;
            }else{
                connection.close();
                return  null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public  boolean deleteSon(int id){
        DataSource dataSource = DataSourceProvider.getInstance().getDataSource();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE sons SET supprime=1 WHERE IdSon=?");
            statement.setInt(1,id);
            boolean suppressionReussie = statement.executeUpdate()==1;
            connection.close();
            return suppressionReussie;


        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public void modifierSon(Son son){
        DataSource dataSource = DataSourceProvider.getInstance().getDataSource();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement=connection.prepareStatement("UPDATE sons SET Chemin=?, Description=? ,Date=? ,IdStreamer=? WHERE IdSon=?");
            statement.setInt(4,son.getStreamer().getId());
            statement.setString(1,son.getCheminSon());
            statement.setString(2,son.getDescription());
            statement.setDate(3,java.sql.Date.valueOf(son.getDate()));
            statement.setInt(5,son.getId());
            statement.executeUpdate();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public int getNbSons() throws SQLException {
        DataSource dataSource = DataSourceProvider.getInstance().getDataSource();
            Connection connection = dataSource.getConnection();
            PreparedStatement statement=connection.prepareStatement("SELECT max(IdSon) as max FROM sons ");
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                Integer a = resultSet.getInt("max")+1;
                connection.close();
                return a;
            }else{
                throw new SQLException("Failed to count sons");
            }

    }
}

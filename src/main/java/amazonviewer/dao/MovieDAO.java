package amazonviewer.dao;

import amazonviewer.db.IDBConnection;
import model.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static amazonviewer.db.DataBase.*;

public interface MovieDAO extends IDBConnection {
  default Movie setMovieViewed(Movie movie) {
    return movie;
  }

  default ArrayList<Movie> read() {
    ArrayList<Movie> movies = new ArrayList<>();
    try (Connection connection = connectToDB()) {
      String query = "SELECT * FROM " + TMOVIE;
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      ResultSet rs = preparedStatement.executeQuery();
      while (rs.next()) {
        Movie movie = new Movie(rs.getString(TMOVIE_TITLE),
          rs.getString(TMOVIE_GENRE),
          rs.getString(TMOVIE_CREATOR),
          Integer.valueOf(rs.getInt(TMOVIE_DURATION)),
          Short.valueOf(rs.getShort(TMOVIE_YEAR)));

        movie.setId(Integer.valueOf(rs.getInt(TMOVIE_ID)));
        movie.setViewed(getMovieViewed(preparedStatement, connection, Integer.valueOf(rs.getInt(TMOVIE_ID))));
        movies.add(movie);
      }
    } catch (SQLException e) {
      System.out.println("Error " + e);
    }
    return movies;
  }

  private boolean getMovieViewed(PreparedStatement preparedStatement, Connection connection, int id_movie) {
    boolean viewed = false;
    String query = "SELECT * FROM " + TVIEWED +
      " WHERE " + TVIEWED_ID_MATERIAL + " = ? " +
      " AND " + TVIEWED_ID_ELEMENT + " = ? " +
      " AND " + TVIEWED_ID_USER + " = ? ;";
    ResultSet rs = null;

    try {
      preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, TMATERIAL_ID[0]);
      preparedStatement.setInt(2, id_movie);
      preparedStatement.setInt(3, TUSER_IDUSUARIO);

      rs = preparedStatement.executeQuery();
      viewed = rs.next();
    } catch (Exception r) {
      System.out.println(rs);
      r.printStackTrace();
    }
    return viewed;
  }
}

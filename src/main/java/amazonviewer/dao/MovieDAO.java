package amazonviewer.dao;

import amazonviewer.db.IDBConnection;
import model.Movie;

import java.sql.*;
import java.util.ArrayList;

import static amazonviewer.db.DataBase.*;

public interface MovieDAO extends IDBConnection {
  default Movie setMovieViewed(Movie movie) {
    try (Connection connection = connectToDB()) {
      Statement statement = connection.createStatement();

      String query = "INSERT INTO " + TVIEWED +
        " (" + TVIEWED_ID_MATERIAL + ", " + TVIEWED_ID_ELEMENT +
        ", " + TVIEWED_ID_USER + "," + TMOVIE_FECHA_VISTO + ") " +
        " VALUES (" + TMATERIAL_ID[0] + ", " + movie.getId() + ", " +
        TUSER_IDUSUARIO + ", " + movie.getDateViewed() + ")";
      System.out.println(query);
      if (statement.executeUpdate(query) > 0) {
        System.out.println("Se marco en Visto");
      } else {
        System.out.println("Error interno");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
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

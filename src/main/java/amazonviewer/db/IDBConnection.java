package amazonviewer.db;


import java.sql.Connection;
import java.sql.DriverManager;

import static amazonviewer.db.DataBase.URL;
import static amazonviewer.db.DataBase.DB;
import static amazonviewer.db.DataBase.USER;
import static amazonviewer.db.DataBase.PASSWORD;

public interface IDBConnection {
  default Connection connectToDB() {
    Connection connection = null;
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      connection = DriverManager.getConnection(URL + DB, USER, PASSWORD);
      if (connection != null) {
        System.out.println("Se establecio la coneccion 😋😋");
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      return connection;
    }
  }
}

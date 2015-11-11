package entidades;
import java.sql.*;
import java.io.*;

public class MAE {
   Connection conn;
   Statement stmt;

   public MAE() {
      try {
        String userName = "root";
        String password = "";
        String url = "jdbc:mysql://localhost/maes_system";
        Class.forName ("com.mysql.jdbc.Driver").newInstance();
        conn = DriverManager.getConnection (url, userName, password);
        stmt = conn.createStatement();
      } catch (Exception e) { System.out.println ("Cannot connect to database server"); }
   }

   public boolean verificarCrendenciales(int iMatricula, String strContrasena) {
        try {
           stmt.executeQuery ("SELECT matricula FROM Usuario WHERE matricula = " + iMatricula);
           ResultSet rs = stmt.getResultSet();
           if (rs.next()) { //Va al primer registro si lo hay
              int iNMatricula = rs.getInt ("matricula");
              rs.close();
              if (iMatricula == iNMatricula) {
                return (strContrasena == obtenerContrasena(iMatricula));
              }
           } else {
             return false;
           }
        } catch (SQLException e) {}

        return false;
    }

    public String obtenerNombre(int iMatricula) {
      try {
         stmt.executeQuery ("SELECT nombre FROM Usuario WHERE matricula = " + iMatricula);
         ResultSet rs = stmt.getResultSet();
         if (rs.next()) { //Va al primer registro si lo hay
            String strNombre = rs.getString ("nombre");
            rs.close();
            return strNombre;
         } else {
           return "Nombre no disponible.";
         }
      } catch (SQLException e) {}

      return "Nombre no disponible.";
    }

    private String obtenerContrasena(int iMatricula) {
      try {
         stmt.executeQuery ("SELECT contrasena FROM Usuario WHERE matricula = " + iMatricula);
         ResultSet rs = stmt.getResultSet();
         if (rs.next()) { //Va al primer registro si lo hay
            String strContrasena = rs.getString ("contrasena");
            rs.close();
            return strContrasena;
         } else {
           return "";
         }
      } catch (SQLException e) {}

      return "";
    }
}

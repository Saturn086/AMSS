package entidades;
import java.sql.*;
import java.io.*;
import java.lang.String;

public class Asesoria {
   Connection conn;
   Statement stmt;

   public Asesoria() {
      try {
        String userName = "root";
        String password = "";
        String url = "jdbc:mysql://localhost/maes_system";
        Class.forName ("com.mysql.jdbc.Driver").newInstance();
        conn = DriverManager.getConnection (url, userName, password);
        stmt = conn.createStatement();
      } catch (Exception e) { System.out.println ("Cannot connect to database" +
          "server"); }
   }

   public RowAsesoria obtenerAsesoria(String strMatricula) {
        try {
          //Crea la instancia que será regresada por el método
          RowAsesoria rowAsesoria;

           stmt.executeQuery ("SELECT *, COUNT(*) AS cantAlumnos FROM Asesoria"
              + " WHERE matricula_mae = " + "\'" + strMatricula + "\'" +
              " GROUP BY matricula_mae, materia, lugar");
           ResultSet rs = stmt.getResultSet();
           if (rs.next()) { //Va al primer registro si lo hay
             //Crea a la instancia que será regresada como resultado del query
            rowAsesoria = new RowAsesoria();
            rowAsesoria.setStrMatriculaMAE(rs.getString("matricula_mae"));
            rowAsesoria.setILugar(rs.getInt("lugar"));
            rowAsesoria.setIMateria(rs.getInt("materia"));
            rowAsesoria.setICantAlumnos(rs.getInt("cantAlumnos"));
            rowAsesoria.setCDisponibilidad(rs.getString("disponibilidad").charAt(0));

            rs.close();
            return rowAsesoria;
        }
        } catch (SQLException e) {System.out.println("Error en obtenerAsesoria"+
            " dentro de Asesoria."); }
        return null;
    }

    // ####################eliminarAsesoria no está completamente implementada #######################################
    /*public void eliminarAsesoria(String strMatricula, int iMateria) {
      try {
        //Crea la instancia que será regresada por el método
        RowAsesoria rowAsesoria = obtenerAsesoria(strMatricula);

         stmt.executeQuery ("SELECT * FROM Asesoria WHERE matricula = " +
            "\'" + strMatricula + "\'");
         ResultSet rs = stmt.getResultSet();
         if (rs.next()) { //Va al primer registro si lo hay
           //Crea a la instancia que será regresada como resultado del query
            rowAsesoria = new RowAsesoria(rs.getString("matricula_mae"),
                              rs.getInt("materia"),
                              rs.getInt("lugar"),
                              (char)rs.getString("disponibilidad"));
            rs.close();
            if (strMatricula.equals(strNMatricula)) {
              return (strContrasena.equals(obtenerContrasena(strMatricula)));
          }
          else {
            return null;
          }
      } catch (SQLException e) {System.out.println("Error en obtenerAsesoria"+
          " dentro de Asesoria."); }

      return false;
  }*/
}

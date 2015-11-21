package entidades;
import java.sql.*;
import java.io.*;
import java.lang.String;
import java.util.*;

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
		}
		catch (Exception e) {
			System.out.println("Cannot connect to database server");
		}
	}

	public ArrayList<RowAsesoria> obtenerAsesoria(String strMatricula) {
		try {
			//Crea la instancia que será regresada por el método
			RowAsesoria rowAsesoria;
			ArrayList<RowAsesoria> rowAsesoriaList = new ArrayList<RowAsesoria>();

			stmt.executeQuery ("SELECT *, COUNT(*) AS cantAlumnos FROM Asesoria" +
				" WHERE matricula_mae = " + "\'" + strMatricula + "\'" +
				" GROUP BY matricula_mae, materia, lugar"
				);
			ResultSet rs = stmt.getResultSet();

			while (rs.next()) { //Va al primer registro si lo hay
				//Crea a la instancia que será regresada como resultado del query
				rowAsesoria = new RowAsesoria();
				rowAsesoria.setStrMatriculaMAE(rs.getString("matricula_mae"));
				rowAsesoria.setILugar(rs.getInt("lugar"));
				rowAsesoria.setIMateria(rs.getInt("materia"));
				rowAsesoria.setICantAlumnos(rs.getInt("cantAlumnos"));
				rowAsesoria.setCDisponibilidad(rs.getString("disponibilidad").charAt(0));
				rowAsesoriaList.add(rowAsesoria);
			}

			rs.close();
			return rowAsesoriaList;
		}
		catch (SQLException e) {
			System.out.println("Error en obtenerAsesoria" + " dentro de Asesoria.");
		}

		return null;
	}


	public void asignarUbicacion(String strMatricula, int intUbicacion) {
		try {
			stmt.executeUpdate(
				"UPDATE Asesoria" +
				" SET lugar = " + "\'" + intUbicacion + "\'" +
				" WHERE matricula_mae = " + "\'" + strMatricula + "\'"
				);
		}
		catch (SQLException e) {
			System.out.println("Error en asignarUbicacion" + " dentro de Asesoria.");
		}
	}


	public boolean obtenerDisponibilidad(String strMatricula) {
		try {
			stmt.executeQuery(
				"SELECT disponibilidad" +
				" FROM asesoria" +
				" WHERE matricula_mae = " + "\'" + strMatricula + "\'"
				);
			ResultSet rs = stmt.getResultSet();

			if (rs.next()) { //Va al primer registro si lo hay
				String strDisp = rs.getString("disponibilidad");

				rs.close();

				return strDisp.equals("1");
			}
			else {
				rs.close();
				return false;
			}
		}
		catch (SQLException e) {
			System.out.println("Error en obtenerDisponibilidad" + " dentro de Asesoria.");
		}

		return false;
	}


	public boolean modificarDisponibilidad(String strMatricula) {
		try {
			String strDisp;

			if (obtenerDisponibilidad(strMatricula)) {
				strDisp = "0";
			}
			else {
				strDisp = "1";
			}

			stmt.executeUpdate(
				"UPDATE asesoria" +
				" SET disponibilidad = " + "\'" + strDisp + "\'" +
				" WHERE matricula_mae = " + "\'" + strMatricula + "\'"
				);
		}
		catch (SQLException e) {
			System.out.println("Error en obtenerDisponibilidad" + " dentro de Asesoria.");
		}

		return false;
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

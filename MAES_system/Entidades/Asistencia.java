//obt matrucilas activas
package entidades;
import java.sql.*;
import java.io.*;
import java.util.*;


public class Asistencia {
	Connection conn;
	Statement stmt;

	public Asistencia() {
       try {
         String userName = "root";
         String password = "";
         String url = "jdbc:mysql://localhost/maes_system";
         Class.forName ("com.mysql.jdbc.Driver").newInstance();
         conn = DriverManager.getConnection (url, userName, password);
         stmt = conn.createStatement();
       } catch (Exception e) { System.out.println ("Cannot connect to database server"); }
    }

	public ArrayList<String> obtenerMatriculasActivas() {
		ArrayList<String> listaMatriculas = new ArrayList<String>();
		try {
			stmt.executeQuery ("SELECT matricula_mae FROM Asistencia WHERE hora_salida IS NULL and fecha = CURDATE()");
			ResultSet rs = stmt.getResultSet();
			while(rs.next()) {
				listaMatriculas.add(rs.getString("matricula_mae"));
			}
			rs.close();
			return listaMatriculas;
		} catch (SQLException e) {}
		return listaMatriculas;
	}

	public ArrayList<String> obtenerMatriculas() {
		ArrayList<String> listaMatriculas = new ArrayList<String>();

		try {
			stmt.executeQuery ("SELECT matricula FROM Usuario WHERE matricula IS NOT \'A00000000\'");
			ResultSet rs = stmt.getResultSet();
			while(rs.next()) {
				listaMatriculas.add(rs.getString("matricula"));
			}
			rs.close();
			return listaMatriculas;
		} catch (SQLException e) {}
		return listaMatriculas;
	}

	public Time obtenerHoras(String strMatricula) {
		try {
			stmt.executeQuery (
			"SELECT SEC_TO_TIME( SUM( TIME_TO_SEC( TIMEDIFF(hora_salida,hora_entrada) ) ) ) AS hours"
			+ " FROM Asistencia"
			+ " WHERE matricula_mae = \'" + strMatricula + "\'"
			+ " GROUP BY matricula_mae");

			ResultSet rs = stmt.getResultSet();
			if(rs.next()) {
				Time time = rs.getTime("hours");
				rs.close();
				return time;
			}
		} catch (SQLException e) {System.out.println("error in obtener horas");}

		return null;
	}
}

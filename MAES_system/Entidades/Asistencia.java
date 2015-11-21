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
		}
		catch (Exception e) {
			System.out.println ("Cannot connect to database server");
		}
	}

	public ArrayList<String> obtenerMatriculasActivas() {
		ArrayList<String> listaMatriculas = new ArrayList<String>();
		try {
			stmt.executeQuery ("SELECT matricula_mae FROM Asistencia WHERE hora_salida IS NULL");
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
			stmt.executeQuery ("SELECT matricula FROM Usuario WHERE matricula != \'A00000000\'");
			ResultSet rs = stmt.getResultSet();
			while(rs.next()) {
				//System.out.println(rs.getString("matricula"));
				listaMatriculas.add(rs.getString("matricula"));
			}
			rs.close();
			return listaMatriculas;
		} 
		catch (SQLException e) {
			System.out.println("error in obtener matriculas");
		}
		
		return null;
	}

	public String obtenerHoras(String strMatricula) {
		try {
			stmt.executeQuery (
				"SELECT SEC_TO_TIME( SUM( TIME_TO_SEC( TIMEDIFF(hora_salida,hora_entrada) ) ) ) AS hours" +
				" FROM Asistencia" +
				" WHERE matricula_mae = \'" + strMatricula + "\' AND hora_salida IS NOT NULL" +
				" GROUP BY matricula_mae"
				);

			ResultSet rs = stmt.getResultSet();
			if(rs.next()) {
				String strTime = rs.getTime("hours").toString();
				rs.close();
				return strTime;
			}
		} catch (SQLException e) {System.out.println("error in obtener horas");}

		return null;
	}
	
	
	public boolean esEntrada(String strMatricula) {
		try {
			stmt.executeQuery (
				"SELECT matricula_mae" +
				" FROM Asistencia" + 
				" WHERE matricula_mae = " +  "\'" + strMatricula + "\'" + " AND hora_salida = ISNOTNULL"
				);
			
			ResultSet rs = stmt.getResultSet();
			
			if(rs.next()) {
				rs.close();
				return true;
			}
			else {
				rs.close();
				return false;
			}
		} 
		catch (SQLException e) {
			System.out.println("Error en guardarAsistenciaInicio" + " dentro de Asistencia.");
		}
		
		return false;
	}
	
	
	public void guardarAsistenciaInicio(String strMatricula, String strFecha, String strHora) {
		try {
			stmt.executeUpdate (
				"INSERT INTO Asistencia (matricula_mae,hora_entrada,fecha)"
				+ strMatricula + "," + strHora + "," + strFecha
				);
		} 
		catch (SQLException e) {
			System.out.println("Error en guardarAsistenciaInicio" + " dentro de Asistencia.");
		}
	}
	
	public void guardarAsistenciaFin(String strMatricula, String strHora) {
		try {
			stmt.executeUpdate (
				"UPDATE Asistencia" +
				" SET hora_salida=" + "\'" + strHora + "\'" +
				" WHERE matricula_mae=" + "\'" + strMatricula + "\'"
				);
		} 
		catch (SQLException e) {
			System.out.println("Error en guardarAsistenciaInicio" + " dentro de Asistencia.");
		}
	}
}

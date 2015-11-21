package entidades;
import java.sql.*;
import java.io.*;

public class Usuario {
	Connection conn;
	Statement stmt;

	public Usuario() {
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

	public boolean verificarCrendenciales(String strMatricula, String strContrasena) {
		try {
			stmt.executeQuery ("SELECT matricula FROM Usuario WHERE matricula = " + "\'" + strMatricula + "\'");
			ResultSet rs = stmt.getResultSet();
			if (rs.next()) { //Va al primer registro si lo hay
				String strNMatricula = rs.getString ("matricula");
				rs.close();
				if (strMatricula.equals(strNMatricula)) {
					return (strContrasena.equals(obtenerContrasena(strMatricula)));
				}
			}
			else {
				rs.close();
				return false;
			}
		}
		catch (SQLException e) {
			System.out.println("Error en verificarCrendenciales" + " dentro de Usuario.");
		}

		return false;
	}

	public String obtenerNombre(String strMatricula) {
		try {
			stmt.executeQuery ("SELECT nombre FROM Usuario WHERE matricula = " + "\'" + strMatricula + "\'");
			ResultSet rs = stmt.getResultSet();
			if (rs.next()) { //Va al primer registro si lo hay
				String strNombre = rs.getString ("nombre");
				rs.close();
				return strNombre;
			}
			else {
				rs.close();
				return "Nombre no disponible.";
			}
		}
		catch (SQLException e) {
			System.out.println("Error en obtenerNombre" + " dentro de Usuario.");
		}

		return "Nombre no disponible.";
	}

	public int obtenerTipo(String strMatricula) {
		try {
			stmt.executeQuery ("SELECT tipo FROM Usuario WHERE matricula = " + "\'" + strMatricula + "\'");
			ResultSet rs = stmt.getResultSet();
			if (rs.next()) { //Va al primer registro si lo hay
				int iTipo = rs.getInt ("tipo");
				rs.close();
				return iTipo;
			} 
			else {
				rs.close();
				return 2; //2 no es un tipo valido, simboliza error
			}
		} catch (SQLException e) {
			System.out.println("Error en obtenerTipo" + " dentro de Usuario.");
		}

		return 2;
	}
	
	public boolean verificarEsMae(String strMatricula) {
		try {
			stmt.executeQuery (
				"SELECT matricula" +
				"FROM Usuario" +
				"WHERE matricula = " + "\'" + strMatricula + "\'");
			ResultSet rs = stmt.getResultSet();
			
			if (rs.next()) {	// Si hay 1 registro, si es mae.
				rs.close();
				return true;
			} 
			else {				// Si no, no.
				rs.close();
				return false; 
			}
			
		} catch (SQLException e) {
			System.out.println("Error en verificarEsMae" + " dentro de Usuario.");
		}

		return false;
	}

	private String obtenerContrasena(String strMatricula) {
		try {
			stmt.executeQuery ("SELECT contrasena FROM Usuario WHERE matricula = " + "\'" + strMatricula + "\'");
			ResultSet rs = stmt.getResultSet();
			if (rs.next()) { //Va al primer registro si lo hay
				String strContrasena = rs.getString ("contrasena");
				rs.close();
				return strContrasena;
			} 
			else {
				rs.close();
				return "";
			}
		}
		catch (SQLException e) {
			System.out.println("Error en obtenerContrasena" + " dentro de Usuario.");
		}

		return "";
	}
}

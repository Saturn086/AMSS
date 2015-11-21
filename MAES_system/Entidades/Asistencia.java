//obt matrucilas activas
package entidades;
import java.sql.*;
import java.io.*;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import entidades.RowHistorial;

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
		}
		catch (SQLException e) {
			System.out.println("Error en obtenerMatriculasActivas" + " dentro de Asistencia.");
		}
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
		}
		catch (SQLException e) {
			System.out.println("error in obtener horas");
		}

		return null;
	}

	public ArrayList<RowHistorial> obtenerHistorial(String strMatricula) {
		ArrayList<RowHistorial> resultList = new ArrayList<RowHistorial>();

		try {
			stmt.executeQuery (
				"SELECT hora_entrada, hora_salida, fecha," +
				"SEC_TO_TIME( SUM( TIME_TO_SEC( TIMEDIFF(hora_salida,hora_entrada) ) ) ) AS hours" +
				" FROM Asistencia" +
				" WHERE matricula_mae = \'" + strMatricula + "\' AND hora_salida IS NOT NULL" +
				" GROUP BY matricula_mae"
				);

			ResultSet rs = stmt.getResultSet();



			while(rs.next()) {
				RowHistorial rowHistorial = new RowHistorial(rs.getDate("fecha").toString(),
						rs.getTime("hora_entrada").toString(),
				 		rs.getTime("hora_salida").toString(),
						rs.getTime("hours").toString());

				resultList.add(rowHistorial);
			}
			rs.close();
		} catch (SQLException e) {System.out.println("error en obtener historial ");}

		return resultList;
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
				/*"INSERT INTO Asistencia (matricula_mae,hora_entrada,hora_salida,fecha)"
				+ strMatricula + "," + strHora + "," + strFecha */
				"INSERT INTO Asistencia VALUES (\'"+ strMatricula + "\',\'" + strHora + "\',NULL,\'" + strFecha + "\')"
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

	public ArrayList<String> obtenerMateriasYAlumnos(String strMatricula) {
		try {
			stmt.executeQuery (
				"SELECT nombre, COUNT(*) as numero" +
				" FROM Asesoria, Materia" +
				" WHERE matricula_mae =" + "\'" + strMatricula + "\'" + " AND id = materia" +
				" GROUP BY nombre"
				);

			ResultSet rs = stmt.getResultSet();

			ArrayList<String> lststrMatsYAlumnos = new ArrayList<String>();

			while(rs.next()) {
				lststrMatsYAlumnos.add(rs.getString("nombre"));
				lststrMatsYAlumnos.add(rs.getString("numero"));
			}

			rs.close();
			return lststrMatsYAlumnos;
		}
		catch (SQLException e) {
			System.out.println("Error en obtenerMateriasYAlumnos" + " dentro de Asistencia.");
		}
		return null;
	}


	public void borrarAsistencia(String strMatricula, String strFecha, String strHora) {
		try {
			stmt.executeUpdate (
				"DELETE" +
				" FROM Asistencia" +
				" WHERE matricula_mae =" + "\'" + strMatricula + "\'" + " AND" +
						" fecha =" + "\'" + strFecha + "\'" + " AND" +
						" hora_entrada =" + "\'" + strHora + "\'"
				);
		}
		catch (SQLException e) {
			System.out.println("Error en obtenerMateriasYAlumnos" + " dentro de Asistencia.");
		}
	}
}

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

				/*"SELECT TIME_TO_SEC(hora_entrada) AS hora_entrada, TIME_TO_SEC(hora_salida) AS hora_salida, " +
				"day(fecha) AS day, month(fecha) AS month, year(fecha), SEC_TO_TIME( SUM( TIME_TO_SEC( TIMEDIFF" +
				"(hora_salida,hora_entrada) ) ) ) AS hours" +
				" FROM Asistencia" +
				" WHERE matricula_mae = \'" + strMatricula + "\' AND hora_salida IS NOT NULL" +
				" GROUP BY matricula_mae"*/

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
	}

	/*
	public void guardarAsistenciaInicio(String strMatricula) {
		try {
			GregorianCalendar gregCal = (GregorianCalendar) Calendar.getInstance();

			int intDay = gregCal.get(Calendar.DATE);
			int intMonth = gregCal.get(Calendar.MONTH);
			int intYeat = gregCal.get(Calendar.YEAR);

			String strDay = intDay + "";
			String strMonth = intMonth + "";
			String strYear = intYeat + "";

			if (intDay < 10) {
				strDay = "0" + strDay;
			}
			if (intMonth < 10) {
				strMonth = "0" + strMonth;
			}

			String strFecha = strYear + "-" + strMonth + "-" + strDay;

			int intMinute = gregCal.get(Calendar.MINUTE);
			int intHour = gregCal.get(Calendar.HOUR_OF_DAY);

			String strMinute = intMinute + "";
			String strHour =  + "";
			String strYear =  + "";

			if (intMinute < 10) {
				strDay = "0" + strDay;
			}
			if (intMonth < 10) {
				strMonth = "0" + strMonth;
			}

			stmt.executeQuery (
				"INSERT INTO Asistencia (matricula_mae,hora_entrada,fecha)"
				+ strMatricula + "," + + "," + strFecha
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
	*/

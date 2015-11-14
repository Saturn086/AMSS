//obt matrucilas activas
package entidades;
import java.sql.*;
import java.io.*;
import java.util.*;


class Asistencia {
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
}

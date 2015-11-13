//look at my code, my code is amazing
package entidades;
import java.sql.*;
import java.io.*;

public class Materia {
	Connection conn;
	Statement stmt;

	public Materia() {
       try {
         String userName = "root";
         String password = "";
         String url = "jdbc:mysql://localhost/maes_system";
         Class.forName ("com.mysql.jdbc.Driver").newInstance();
         conn = DriverManager.getConnection (url, userName, password);
         stmt = conn.createStatement();
       } catch (Exception e) { System.out.println ("Cannot connect to database server"); }
    }

	public String strObtenerNombreMateria(string strId) {
		try {
			stmt.executeQuery("SELECT nombre FROM Materia WHERE id = " + "\'"
					+ strId + "\'");)
			ResultSet rs = stmt.getResultSet();
			if (rs.next()) {
				String strNombre = rs.getString("nombre");
				rs.close();
				return strNombre;
			}
			else {
				return "Nombre no disponible."
			}
		} catch (SQLException e) {}

		return "Nombre no disponible."
	}

	public boolean bolValidarMateria(string strId) {
		try {
			stmt.executeQuery("SELECT nombre FROM Materia WHERE id = " + "\'"
					+ strId + "\'");)
			ResultSet rs = stmt.getResultSet();
			if (rs.next()) {
				rs.close();
				return true;
			}
			else {
				return false;
			}
		} catch (SQLException e) {}

		return false;
	}

}

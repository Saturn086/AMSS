package entidades;
import java.sql.*;
import java.io.*;

public class Ubicacion {
	Connection conn;
	Statement stmt;

	public Ubicacion() {
		try {
			String userName = "root";
	        String password = "";
	        String url = "jdbc:mysql://localhost/maes_system";
	        Class.forName ("com.mysql.jdbc.Driver").newInstance();
	        conn = DriverManager.getConnection (url, userName, password);
	        stmt = conn.createStatement();
		} catch (Exception e) { System.out.println ("Cannot connect to database server"); }
	}

	public int intObtenerCantidadUbicaciones() {
		try {
           stmt.executeQuery ("SELECT COUNT(*) AS numero FROM Ubicacion");
           ResultSet rs = stmt.getResultSet();
           if (rs.next()) { //Va al primer registro si lo hay
              int iCont = rs.getInt("numero");
              rs.close();
              return iCont;
           } else {
             return -1;
           }
        } catch (SQLException e) {}
        return -1;
	}

	public String strObtenerNombre(int iId) {
		try {
           stmt.executeQuery ("SELECT lugar FROM Ubicacion WHERE id = " + "\'" + iId + "\'");
           ResultSet rs = stmt.getResultSet();
           if (rs.next()) {
              String strNombre = rs.getString("lugar");
              rs.close();
              return strNombre;
           } else {
             return "Nombre no disponible.";
           }
        } catch (SQLException e) {}
        return "Nombre no disponible.";

	}
}

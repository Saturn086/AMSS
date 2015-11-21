package controles;
import entidades.Usuario;
import entidades.Asistencia;
import entidades.RowHistorial;
import java.util.*;
import java.sql.*;


public class ControlAsistencias {
	Asistencia asistencia;
	Usuario usuario;
	ArrayList<String> matriculasList;
	String strResultado;

	public ControlAsistencias() {
		asistencia = new Asistencia();
		usuario = new Usuario();
		matriculasList = new ArrayList<String>();
		strResultado = "";
	}

	public ArrayList<String> mostrarAsistencia() {
		matriculasList = asistencia.obtenerMatriculas();
		ArrayList<String> resultList = new ArrayList<String>();
		for(String strMatricula : matriculasList) {
			String strTiempo = asistencia.obtenerHoras(strMatricula);
			String strNombre = usuario.obtenerNombre(strMatricula);
			resultList.add(strNombre);
			if(strTiempo != null)
				resultList.add(strTiempo);
			else
				resultList.add("No lleva horas");
		}
		return resultList;
	}

	public ArrayList<RowHistorial> obtenerHistorial(String strMatricula) {
		ArrayList<RowHistorial> resultList = new ArrayList<RowHistorial>();

		resultList = asistencia.obtenerHistorial(strMatricula);

		return resultList;
}
}

package controles;
import entidades.Usuario;
import entidades.Asistencia;
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

	public String mostrarAsistencia() {
		matriculasList = asistencia.obtenerMatriculas();
		for(String strMatricula : matriculasList) {
			String strTiempo = asistencia.obtenerHoras(strMatricula);
			String strNombre = usuario.obtenerNombre(strMatricula);
			strResultado += strNombre + " ";
			if(strTiempo != null)
				strResultado += strTiempo + "<br>";
			else
				strResultado += "No lleva horas<br>";
		}
		return strResultado;
	}

}

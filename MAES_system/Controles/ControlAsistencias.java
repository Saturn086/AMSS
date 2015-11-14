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
		matriculasList = new ArrayList<String>();
		strResultado = "";
	}

	public String mostrarAsistencia() {
		matriculasList = asistencia.obtenerMatriculasActivas();
		for(String strMatricula : matriculasList) {
			Time tTiempo = asistencia.obtenerHoras(strMatricula);
			String strNombre = usuario.obtenerNombre(strMatricula);
			strResultado += strNombre + " " + tTiempo.toString() + "<br>";
		}
	}

}

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

	public boolean verificarEsMae(String strMatricula) {
		if (usuario.verificarEsMae(strMatricula)) {
			if (asistencia.esEntrada(strMatricula)) {
				guardarAsistenciaInicio(strMatricula);
			}
			else {
				guardarAsistenciaFin(strMatricula);
			}

			return true;
		}
		return false;
	}

	public String generaFecha() {
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

		return strFecha;
	}

	public String generaHora() {
		GregorianCalendar gregCal = (GregorianCalendar) Calendar.getInstance();

		int intMinute = gregCal.get(Calendar.MINUTE);
		int intHour = gregCal.get(Calendar.HOUR_OF_DAY);

		String strMinute = intMinute + "";
		String strHour = intHour + "";

		if (intMinute < 10) {
			strMinute = "0" + strMinute;
		}
		if (intHour < 10) {
			strHour = "0" + strHour;
		}

		String strHora = strHour + ":" + strMinute;

		return strHora;
	}

	public void guardarAsistenciaInicio(String strMatricula) {
		String strFecha = generaFecha();
		String strHora = generaHora();

		asistencia.guardarAsistenciaInicio(strMatricula, strFecha, strHora);
	}

	public void guardarAsistenciaFin(String strMatricula) {
		String strHora = generaHora();

		asistencia.guardarAsistenciaFin(strMatricula, strHora);
	}
}

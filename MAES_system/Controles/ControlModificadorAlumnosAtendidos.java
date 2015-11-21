package controles;
import entidades.Usuario;
import entidades.Materia;
import entidades.Asesoria;
import entidades.Asistencia;
import entidades.Ubicacion;
import entidades.RowAsesoria;
import java.util.*;

public class ControlModificadorAlumnosAtendidos {
	Usuario usuario;
	Materia materia;
	Asesoria asesoria;
	Asistencia asistencia;
	Ubicacion ubicacion;
	ArrayList<RowAsesoria> rowAsesoriaList;
	ArrayList<String> matriculasList;
	ArrayList<String> resultadoList;

	public ControlModificadorAlumnosAtendidos() {
		//Asume que la instancia persiste durante la sesion
		usuario = new Usuario();
		materia = new Materia();
		asesoria = new Asesoria();
		asistencia = new Asistencia();
		ubicacion = new Ubicacion();
		rowAsesoriaList = new ArrayList<RowAsesoria>();
		matriculasList = new ArrayList<String>();
		resultadoList = new ArrayList<String>();
	}
	
	public ArrayList<String> obtenerMateriasYAlumnos(String strMatricula){
		return asistencia.obtenerMateriasYAlumnos(strMatricula);
	}
}

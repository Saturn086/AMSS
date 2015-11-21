package controles;
import entidades.Usuario;
import entidades.Materia;
import entidades.Asesoria;
import entidades.Asistencia;
import entidades.Ubicacion;
import entidades.RowAsesoria;
import java.util.*;

public class ControlActualizarMAESDisponibles {
	Usuario usuario;
	Materia materia;
	Asesoria asesoria;
	Asistencia asistencia;
	Ubicacion ubicacion;
	ArrayList<RowAsesoria> rowAsesoriaList;
	ArrayList<String> matriculasList;
	ArrayList<String> resultadoList;

	public ControlActualizarMAESDisponibles() {
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

	//Obtener todas las asesorÃ­as dadas en el momento de la consulta
	public ArrayList<String> mostrarAsesorias() {
		matriculasList = asistencia.obtenerMatriculasActivas();
		for(String strMatricula : matriculasList) {
			rowAsesoriaList = asesoria.obtenerAsesoria(strMatricula);
			
			for(RowAsesoria rowAsesoria : rowAsesoriaList) {
				String strNombre = usuario.obtenerNombre(rowAsesoria.getStrMatriculaMAE());
				String strCantAlumnos = Integer.toString(rowAsesoria.getICantAlumnos());
				String strNombreMateria = materia.strObtenerNombreMateria(rowAsesoria.getIMateria());
				String strDisponibilidad = Character.toString(rowAsesoria.getCDisponibilidad());
				String strUbicacion = ubicacion.strObtenerNombre(rowAsesoria.getILugar());
				resultadoList.add(strNombre);
				resultadoList.add(strCantAlumnos);
				resultadoList.add(strNombreMateria);
				
				if (strDisponibilidad.equals("0")) {
					resultadoList.add("No"); 
				}
				else {
					resultadoList.add("Si");
				}
				
				resultadoList.add(strUbicacion);
			}
		}
		return resultadoList;
	}

	// Mostrar los MAEs que se encuentran disponibles.
	public ArrayList<String> desplegarUbicaciones() {
		ArrayList<String> lstStrNombres = new ArrayList<String>();
		int intCantUbicacion = ubicacion.intObtenerCantidadUbicaciones();

		for(int i = 1; i <= intCantUbicacion; i++) {
			String strUbicacion = ubicacion.strObtenerNombre(i);

			lstStrNombres.add(i + "");
			lstStrNombres.add(strUbicacion);
		}

		return lstStrNombres;
	}


	// Asignar un mae a una ubicacion.
	public void asginarUbicacion(String strMatricula, int intUbicacion) {
		asesoria.asignarUbicacion(strMatricula, intUbicacion);
	}
}

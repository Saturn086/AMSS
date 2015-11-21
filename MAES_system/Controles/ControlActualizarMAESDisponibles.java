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
	String strResultado;

	public ControlActualizarMAESDisponibles() {
		//Asume que la instancia persiste durante la sesion
		usuario = new Usuario();
		materia = new Materia();
		asesoria = new Asesoria();
		asistencia = new Asistencia();
		ubicacion = new Ubicacion();
		rowAsesoriaList = new ArrayList<RowAsesoria>();
		matriculasList = new ArrayList<String>();
		strResultado = "";
	}

	//Obtener todas las asesorias dadas en el momento de la consulta
	public String mostrarAsesorias() {
		matriculasList = asistencia.obtenerMatriculasActivas();

		for(String strMatricula : matriculasList) {
			rowAsesoriaList = asesoria.obtenerAsesoria(strMatricula);

				for(RowAsesoria rowAsesoria : rowAsesoriaList) {
					String strNombre = usuario.obtenerNombre(rowAsesoria.getStrMatriculaMAE());
					String strCantAlumnos = Integer.toString(rowAsesoria.getICantAlumnos());
					String strNombreMateria = materia.strObtenerNombreMateria(rowAsesoria.getIMateria());
					String strDisponibilidad = Character.toString(rowAsesoria.getCDisponibilidad());
					String strUbicacion = ubicacion.strObtenerNombre(rowAsesoria.getILugar());

					strResultado += strNombre + " - " + strCantAlumnos + " - " + strNombreMateria + " - ";
					if(strDisponibilidad.equals("0")) {
						strResultado += "No";
					}
					else {
						strResultado += "Si";
					}

					strResultado += " - " + strUbicacion + "<br>";
				}
		}
		return strResultado;
	}
	
	// Mostrar los MAEs que se encuentran disponibles.
	public ArrayList<String> desplegarMaesDisp() {
		ArrayList<String> lstStrNombres = new ArrayList<String>();
		int intCantUbicacion = ubicacion.intObtenerCantidadUbicaciones();

		for(int i = 1; i <= intCantUbicacion; i++) {
			String strUbicacion = ubicacion.strObtenerNombre(i);
			
			lstStrNombres.add(i + "");
			lstStrNombres.add(strUbicacion);
		}
		
		return lstStrNombres;
	}
	
	
	// Mostrar los MAEs que se encuentran disponibles.
	public void asginarUbicacion(String strMatricula) {
		asesoria.asignarUbicacion(strMatricula);
	}
}

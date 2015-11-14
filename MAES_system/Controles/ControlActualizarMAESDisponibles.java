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
      rowAsesoria = new ArrayList<rowAsesoria>();
      matriculasList = new ArrayList<String>();
      strResultado = "";
	}

	//Obtener todas las asesorías dadas en el momento de la consulta
	public String mostrarAsesorias() {
    matriculasList = asistencia.obtenerMatriculasActivas();

    for(String strMatricula : matriculasList) {
      RowAsesoria rowAsesoria = asesoria.obtenerAsesoria(strMatricula);

      String strNombre = usuario.obtenerNombre(rowAsesoria.getStrMatriculaMAE());
      String strCantAlumnos = Integer.toString(rowAsesoria.getICantAlumnos());
      String strNombreMateria = materia.strObtenerNombreMateria(rowAsesoria.getIMateria());
      String strDisponibilidad = Character.toString(rowAsesoria.getCDisponibilidad());
      String strUbicacion = ubicacion.strObtenerNombre(rowAsesoria.getILugar());

      strResultado += strNombre + " - " + strCantAlumnos + " - " + strNombreMateria + " - "
          + strDisponibilidad + " - " + strUbicacion + "\n";

    }
	}
}

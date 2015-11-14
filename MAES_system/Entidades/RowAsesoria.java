//Clase utilizada para guardar los datos de un reglón de la base de datos de
//Asesoría

public class RowAsesoria
{
  String strMatriculaMAE;
  int iMateria;
  int iLugar;
  char cDisponibilidad;
  int iCantAlumnos;

  public RowAsesoria(String strMatriculaMAE, int iMateria, int iLugar,
      char cDisponibilidad, int iCantAlumnos) {
    this.strMatriculaMAE = strMatriculaMAE;
    this.iMateria = iMateria;
    this.iLugar = iLugar;
    this.cDisponibilidad = cDisponibilidad;
    this.iCantAlumnos = iCantAlumnos;
  }

  public String getStrMatriculaMAE() {
    return strMatriculaMAE;
  }

  public int getIMateria() {
    return iMateria;
  }

  public int getILugar() {
    return iLugar;
  }

  public char getCDisponibilidad() {
    return cDisponibilidad;
  }

  public int getICantAlumnos() {
    return iCantAlumnos;
  }
}

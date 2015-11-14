//Clase utilizada para guardar los datos de un reglón de la base de datos de
//Asesoría
package entidades;
public class RowAsesoria
{
  String strMatriculaMAE;
  int iMateria;
  int iLugar;
  char cDisponibilidad;
  int iCantAlumnos;

  public RowAsesoria() {
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


  public void setStrMatriculaMAE(String strMatriculaMAE) {
    this.strMatriculaMAE = strMatriculaMAE;
  }

  public void setIMateria(int iMateria) {
    this.iMateria = iMateria;
  }

  public void setILugar(int iLugar) {
    this.iLugar = iLugar;
  }

  public void setCDisponibilidad(char cDisponibilidad) {
    this.cDisponibilidad = cDisponibilidad;
  }

  public void setICantAlumnos(int iCantAlumnos) {
    this.iCantAlumnos = iCantAlumnos;
  }
}

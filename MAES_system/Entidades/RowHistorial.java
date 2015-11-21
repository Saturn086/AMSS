//Clase utilizada para guardar los datos de un reglón de la base de datos de
//Asistencia
package entidades;
public class RowHistorial
{
  String strFecha;
  String strHoraE;
  String strHoraS;
  String strHours;

  public RowHistorial(String strFecha, String strHoraE, String strHoraS,
      String strHours) {

      this.strFecha = strFecha;
      this.strHoraE = strHoraE;
      this.strHoraS = strHoraS;
      this.strHours = strHours;
  }

  public String getStrFecha() {
    return strFecha;
  }
  public String getStrHoraE() {
    return strHoraE;
  }
  public String getStrHoraS() {
    return strHoraS;
  }
  public String getStrHours() {
    return strHours;
  }
}

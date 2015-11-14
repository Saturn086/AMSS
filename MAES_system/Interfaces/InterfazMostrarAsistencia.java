package interfaces;
import controles.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import controles.ControlAsistencias;

public class InterfazMostrarAsistencia extends HttpServlet {
	HttpServletResponse thisResponse;
	HttpServletRequest thisRequest;
	PrintWriter out;
	ControlAsistencias caAsistencia;

	//Es importante observar que todos los metodos definen la operacion GET para
	//	que el metodo doGet sea el que se ejecuta al presionar el boton "Enviar".
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException {
		thisResponse = response;
		thisRequest = request;
		thisResponse.setContentType("text/html");

		out = thisResponse.getWriter();
		//Preparar el encabezado de la pagina Web de respuesta
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">");
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<META http-equiv=Content-Type content=\"text/html\">");
		out.println("</HEAD>");
		out.println("<BODY>");
		out.println("<TITLE>SISTEMA MAES</TITLE>");
		out.println("<h2>Sistema MAES</h2>");
		out.println("<h3>Listado de MAES (Asistencia)</h3>");

		String strOperacion = request.getParameter("operacion");
		// El menu nos envia un parametro para indicar el inicio de sesion
		if (strOperacion == null) {
			mostrarAsistencia();
		}

		out.println("</BODY>");
		out.println("</HTML>");
	}


  public void mostrarAsistencia() {
    caAsistencia = new ControlAsistencias();
    out.println("<b>Nombre - Horas Completadas</b>");
    out.println("<br>");
    out.println(caAsistencia.mostrarAsistencia());
  }
}

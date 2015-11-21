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
		out.println("<meta charset=\"UTF-8\">");
		out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
		out.println("<link href=\"css/bootstrap.min.css\" rel=\"stylesheet\">");
		out.println("</HEAD>");
		out.println("<BODY>");
		out.println("<br> <br> <br>");
		out.println("<div class=\"container text-center\">");
		out.println("<div class=\"panel panel-primary\">");
		out.println("<div class=\"panel-heading\">");
		out.println("<H1 class=\"panel-title\">Mentores Académicos de Excelencia</H1>");
		out.println("</div>");
		out.println("<div class=\"panel-body\">");
		out.println("<h3>Asistencia de MAES</h3> <br>");

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
    ArrayList<String> lista = caAsistencia.mostrarAsistencia();

		out.println("<div class=\"center-block\" style=\"width:80%\">");
		out.println("<table class=\"table text-center\">");
		out.println("<tr>");
		out.println("<th class=\"text-center\">Nombre</th>");
		out.println("<th class=\"text-center\">Horas completadas</th>");
		out.println("</tr>");
		for(int i=0;i<lista.size();i+=2) {
			out.println("<tr>");
			out.println("<td><a href=\"horasRealizadas?nombre=" + lista.get(i) + "\">" + lista.get(i) + "</a></td>");
			out.println("<td>" + lista.get(i+1) + "</td>");
			out.println("</tr>");
		}
		out.println("</table>");
		out.println("<div>");

  }
}

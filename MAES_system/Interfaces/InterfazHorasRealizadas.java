package interfaces;
import controles.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import controles.ControlAsistencias;
import controles.ControlVerificador;
import entidades.RowHistorial;

public class InterfazHorasRealizadas extends HttpServlet {
	HttpServletResponse thisResponse;
	HttpServletRequest thisRequest;
	PrintWriter out;
	ControlAsistencias controlAsistencias;
	ControlVerificador controlVerificador;

	//Es importante observar que todos los metodos definen la operacion GET para
	//	que el metodo doGet sea el que se ejecuta al presionar el boton "Enviar".
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException {
		thisResponse = response;
		thisRequest = request;
		thisResponse.setContentType("text/html");

		out = thisResponse.getWriter();
		//Preparar el encabezado de la pagina Web de respuesta



		String strNombre = request.getParameter("nombre");
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
		out.println("<h3>Horas realizadas - " + strNombre + "</h3> <br>");
		controlVerificador = new ControlVerificador();
		controlAsistencias = new ControlAsistencias();

		String strOperacion = request.getParameter("operacion");
		// El menu nos envia un parametro para indicar el inicio de sesion
		if (strOperacion == null) {
			mostrarHistorial(strNombre);
		}
		else {
			String strMatricula =	controlVerificador.obtenerMatricula(strNombre);
			ArrayList<RowHistorial> historial = controlAsistencias.obtenerHistorial(strMatricula);
			RowHistorial row = historial.get(Integer.parseInt(request.getParameter("valor")));

			controlAsistencias.borrarAsistencia(strMatricula, row.getStrFecha(), row.getStrHoraE());
			mostrarHistorial(strNombre);
		}



		out.println("</div>");
		out.println("</div>");
		out.println("</div>");
		out.println("</BODY>");
		out.println("</HTML>");
	}

	private void mostrarHistorial(String strNombre) {
		String strMatricula =	controlVerificador.obtenerMatricula(strNombre);
		ArrayList<RowHistorial> historial = controlAsistencias.obtenerHistorial(strMatricula);
		int iI = 0;
		out.println("<div class=\"center-block\" style=\"width:80%\">");
		out.println("<table class=\"table text-center\">");
		out.println("<tr>");
		out.println("<th class=\"text-center\">Acción</th>");
		out.println("<th class=\"text-center\">Fecha</th>");
		out.println("<th class=\"text-center\">Hora de entrada</th>");
		out.println("<th class=\"text-center\">Hora de salida</th>");
		out.println("<th class=\"text-center\">Tiempo</th>");
		out.println("</tr>");
		for(RowHistorial r : historial) {
			out.println("<tr>");
			out.println("<td><a href=\"horasRealizadas?valor=" + iI + "&operacion=borrar&nombre=" + strNombre + "\">" + "Borrar" + "</a></td>");
			out.println("<td>" + r.getStrFecha() + "</td>");
			out.println("<td>" + r.getStrHoraE() + "</td>");
			out.println("<td>" + r.getStrHoraS() + "</td>");
			out.println("<td>" + r.getStrHours() + "</td>");
			iI++;
			out.println("</tr>");
		}
		out.println("</table>");
		out.println("<div>");
	}

}

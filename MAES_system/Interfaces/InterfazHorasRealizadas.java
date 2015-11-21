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
    out.println("<h3>Horas realizadas - " + strNombre + "</h3> <br>");
		out.println("</HEAD>");
		out.println("<BODY>");
		out.println("<br> <br> <br>");
		out.println("<div class=\"container text-center\">");
		out.println("<div class=\"panel panel-primary\">");
		out.println("<div class=\"panel-heading\">");
		out.println("<H1 class=\"panel-title\">Mentores Académicos de Excelencia</H1>");
		out.println("</div>");
		out.println("<div class=\"panel-body\">");

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
			RowHistorial row = historial[Integer.parseInt(request.getParameter("valor"))];

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
		for(RowHistorial r : historial) {
			out.println("<p><a href=\"horasRealizadas?valor=" + iI + "&operacion=borrar&nombre=" + strNombre + "\">" + iI + "</a> - " + r.getStrFecha() + " " + r.getStrHoraE() + " " + r.getStrHoraS() + " " + r.getStrHours() + "</p>");
			iI++;
		}
	}

}

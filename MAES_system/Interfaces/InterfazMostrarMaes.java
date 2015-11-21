package interfaces;
import controles.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import controles.ControlVerificador;

public class InterfazMostrarMaes extends HttpServlet {
	HttpServletResponse thisResponse;
	HttpServletRequest thisRequest;
	PrintWriter out;
	ControlActualizarMAESDisponibles cvActualizar;

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
		out.println("<h3>MAES Disponibiles</h3> <br>");

		String strOperacion = request.getParameter("operacion");
		// El menu nos envia un parametro para indicar el inicio de sesion
		if (strOperacion == null) {
			mostrarMaes();
		}

		out.println("</BODY>");
		out.println("</HTML>");
	}

	public void mostrarMaes() {
		cvActualizar = new ControlActualizarMAESDisponibles();
		ArrayList<String> lista = cvActualizar.mostrarAsesorias();
		out.println("<div class=\"center-block\" style=\"width:80%\">");
		out.println("<table class=\"table text-center\">");
		out.println("<tr>");
		out.println("<th class=\"text-center\">Nombre</th>");
		out.println("<th class=\"text-center\">Cantidad de alumnos</th>");
		out.println("<th class=\"text-center\">Materia</th>");
		out.println("<th class=\"text-center\">Disponibilidad</th>");
		out.println("<th class=\"text-center\">Ubicacion</th>");
		out.println("</tr>");
		for(int i=0;i<lista.size();i+=5) {
			out.println("<tr>");
			out.println("<td>" + lista.get(i) + "</td>");
			out.println("<td>" + lista.get(i+1) + "</td>");
			out.println("<td>" + lista.get(i+2) + "</td>");
			out.println("<td>" + lista.get(i+3) + "</td>");
			out.println("<td>" + lista.get(i+4) + "</td>");
			out.println("</tr>");
		}
		out.println("</table>");
		out.println("<div>");

	}

}

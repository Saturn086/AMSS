package interfaces;
import controles.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class InterfazControlAsesorias extends HttpServlet {
	HttpServletResponse thisResponse;
	HttpServletRequest thisRequest;
	PrintWriter out;
	//declarar controladores
	ControlVerificador cvVerificador;
	ControlModificadorAlumnosAtendidos cmAlumnos;
	ControlActualizarMAESDisponibles caMaes;


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
		out.println("<h3>Control de Asesorias</h3> <br>");

		String strOperacion = request.getParameter("operacion");
		// El menu nos envia un parametro para indicar el inicio de sesion
		if (strOperacion == null) {
			mostrarControl();
		}
		else {
			cambiarStatus();
		}

		out.println("</BODY>");
		out.println("</HTML>");
	}

	public void mostrarControl() {
		cvVerificador = new ControlVerificador();
		cmAlumnos = new ControlModificadorAlumnosAtendidos();
		caMaes = new ControlActualizarMAESDisponibles();

		String strMatricula = thisRequest.getParameter("matricula");
		String strNombre = cvVerificador.obtenerNombre(strMatricula);
		out.println("<p> Nombre de MAE: " + strNombre + "</p>");
		if(caMaes.obtenerDisponibilidad(strMatricula))
			out.println("<p> Status: Disponible" );
		else
			out.println("<p> Status: No Disponible" );
		out.println("<a href=\"controlAsesorias?operacion=cambiarStatus&matricula=" + strMatricula + "\"> Cambiar status</a>");
		out.println("</p>");
		out.println("<a href=\"#\"> Finalizar Sesión </a>");
		ArrayList<String> materiasList = cmAlumnos.obtenerMateriasYAlumnos(strMatricula);

		out.println("<div class=\"center-block\" style=\"width:80%\">");
		out.println("<table class=\"table text-center\">");
		out.println("<tr>");
		out.println("<th class=\"text-center\">Materia</th>");
		out.println("<th class=\"text-center\">Alumnos</th>");
		out.println("</tr>");
		for(int i=0;i<materiasList.size();i+=2) {
			out.println("<tr>");
			out.println("<td>" + materiasList.get(i) + "</td>");
			out.println("<td>" + materiasList.get(i+1) + "</td>");
			out.println("</tr>");
		}
		out.println("</table>");
		out.println("<div>");


	}

	public void cambiarStatus() {
		caMaes = new ControlActualizarMAESDisponibles();
		String strMatricula = thisRequest.getParameter("matricula");
		caMaes.modificarDisponibilidad(strMatricula);
		mostrarControl();
	}

}

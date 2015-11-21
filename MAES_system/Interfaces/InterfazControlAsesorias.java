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

		out.println("</BODY>");
		out.println("</HTML>");
	}

	public void mostrarControl() {
		cvVerificador = new ControlVerificador();
		String strMatricula = thisRequest.getParameter("matricula");
		String strNombre = cvVerificador.obtenerNombre(strMatricula);
		out.println("<p> Nombre de MAE: " + strNombre + "</p>");
		out.println("<p> Status: Disponible");
		out.println("<a href=\"#\"> Cambiar status </a>");
		out.println("</p>");
		out.println("<a href=\"#\"> Finalizar Sesión </a>");
		

	}

}

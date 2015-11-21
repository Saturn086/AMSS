package interfaces;
import controles.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import controles.ControlVerificador;

public class InterfazRegistrarUbicacion extends HttpServlet {
	HttpServletResponse thisResponse;
	HttpServletRequest thisRequest;
	PrintWriter out;
	//declarar controladores
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
		out.println("<div class=\"container\" align=\"center\">");
		out.println("<div class=\"panel panel-primary\">");
		out.println("<div class=\"panel-heading\">");
		out.println("<H1 class=\"panel-title\">Mentores Académicos de Excelencia</H1>");
		out.println("</div>");
		out.println("<div class=\"panel-body\">");
		out.println("<h3>Seleccion de Ubicacion</h3> <br>");

		String strOperacion = request.getParameter("operacion");
		// El menu nos envia un parametro para indicar el inicio de sesion
		if (strOperacion == null) {
			mostrarUbicacion();
		}
		else {
			guardarUbicacion();
		}

		out.println("</BODY>");
		out.println("</HTML>");
	}

	public void mostrarUbicacion() {
		String strMatricula = thisRequest.getParameter("matricula");
		caMaes = new ControlActualizarMAESDisponibles();
		ArrayList<String> ubicaciones = caMaes.desplegarUbicaciones();
		out.println("<form class=\"form-horizontal\" method=\"GET\" action=\"registrarUbicacion\">");
		//hidden inputs
		out.println("<input type=\"hidden\" name=\"operacion\" value=\"guardar\"/>");
		out.println("<input type=\"hidden\" name=\"matricula\" value=\""+ strMatricula +"\"/>");
		//select box
		out.println("<div class=\"form-group\" style=\"width:40%\">");
		out.println("<select class=\"form-control text-center\" name=\"ubicacion\">");
		out.println("<option value=\"0\"> </option>");
		for(int i=0;i<ubicaciones.size();i+=2) {
			out.println("<option value=\"" + ubicaciones.get(i)  + "\">" + ubicaciones.get(i+1) + "</option>");
		}
		out.println("</select>");
		out.println("</div>");

		//boton
		out.println("<p><input type=\"submit\" class=\"btn btn-primary\" value=\"Entrar\"name=\"B1\"></p>");
		out.println("</form>");
	}

	public void guardarUbicacion() {
		caMaes = new ControlActualizarMAESDisponibles();
		String strMatricula = thisRequest.getParameter("matricula");

		int iUbicacion = Integer.parseInt(thisRequest.getParameter("ubicacion"));
		caMaes.asginarUbicacion(strMatricula, iUbicacion);
		out.println("<p> Ubicacion asignada </p>");
		out.println("<a href=\"#\"> Ir a pantalla de sesión</a>");
	}

}

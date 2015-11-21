package interfaces;
import controles.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import controles.ControlVerificador;

public class InterfazInicioSesion extends HttpServlet {
	HttpServletResponse thisResponse;
	HttpServletRequest thisRequest;
	PrintWriter out;
	ControlVerificador cvVerifi;

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

		String strOperacion = request.getParameter("operacion");
		// El menu nos envia un parametro para indicar el inicio de sesion
		if (strOperacion == null || strOperacion.equals("inicio")) {
			introducirCredenciales();
		}else if (strOperacion.equals("autentificar")) {
			autentificacion();
		}

		out.println("</div>");
		out.println("</div>");
		out.println("</div>");
		out.println("</BODY>");
		out.println("</HTML>");
	}

	public void introducirCredenciales(){
		//header
		out.println("<h3>Iniciar Sesion</h3>");
		//form
		out.println("<form class=\"form-horizontal\" method=\"GET\" action=\"InicioSesion\">");
		//hidden inputs
		out.println("<input type=\"hidden\" name=\"operacion\" value=\"autentificar\"/>");
		//form groups
		out.println("<div class=\"form-group\">");
		out.println("<label class=\"control-label col-md-4\" for=\"text\">Matricula:</label>");
		out.println("<div class=\"col-md-4\"> <input type=\"text\" class=\"form-control\" name=\"matricula\" placeholder=\"Introducir matricula\"> </div>" );
		out.println("</div>");
		out.println("<div class=\"form-group\">");
		out.println("<label class=\"control-label col-md-4\" for=\"pwd\">Contraseña:</label>");
		out.println("<div class=\"col-md-4\"> <input type=\"password\" class=\"form-control\" name=\"contrasena\" placeholder=\"Introducir contraseña\"> </div>" );
		out.println("</div>");
		//submit button
		out.println("<p><input type=\"submit\" class=\"btn btn-primary\" value=\"Entrar\"name=\"B1\"></p>");
		out.println("</form>");
		out.println("<br><br>");
		out.println("<i>Administradores usar matricula A00000000</i>");
	}

	public void autentificacion() {
		cvVerifi = new ControlVerificador();

		String strMatricula = thisRequest.getParameter("matricula").trim();
		String strContrasena = thisRequest.getParameter("contrasena");

		boolean boolExistente = cvVerifi.verificarCrendenciales(strMatricula, strContrasena);
		if (boolExistente) {
			out.println("<p>¡¡¡Bienvenido " + strMatricula + "!!!</p>");
			out.println("<a href=\"mostrarMaes\" type=\"button\" class=\"btn btn-primary\"> Lista de MAES Disponibles </a> <br>");
			//ligas a funciones de administradores
			if(strMatricula.equals("A00000000")) {
				out.println("<br> <p> Funciones de administrador </p>");
				out.println("<a href=\"mostrarAsistencia\" type=\"button\" class=\"btn btn-primary\"> Lista de Asistencia de MAES </a> <br>");
			}
		} else {
			out.println("<form method=\"GET\" action=\"InicioSesion\">");
			out.println("<input type=\"hidden\" name=\"operacion\" value=\"inicio\"/>");
			out.println("<p>Matricula y/o contraseña incorrecta</p>");
			out.println("<p><input type=\"submit\" value=\"Regresar\"name=\"B1\"></p>");
			out.println("</form>");
		}
	}
}

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
		out.println("</HEAD>");
		out.println("<BODY>");
		out.println("<TITLE>SISTEMA MAES</TITLE>");
		out.println("<h2>Sistema MAES</h2>");

		String strOperacion = request.getParameter("operacion");
		// El menu nos envia un parametro para indicar el inicio de sesion
		if (strOperacion == null || strOperacion.equals("inicio")) {
			introducirCredenciales();
		}else if (strOperacion.equals("autentificar")) {
			autentificacion();
		}

		out.println("</BODY>");
		out.println("</HTML>");
	}

	public void introducirCredenciales(){
		out.println("<h3>Iniciar Sesion</h3>");
		out.println("<form method=\"GET\" action=\"InicioSesion\">");
		out.println("<input type=\"hidden\" name=\"operacion\" value=\"autentificar\"/>");
		out.println("<p> Matricula: \t <input type=\"text\" name=\"matricula\" size=\"15\"></p>");
		out.println("<p> Contraseña:\t <input type=\"password\" name=\"contrasena\" size=\"15\"></p>");
		out.println("<p><input type=\"submit\" value=\"Entrar\"name=\"B1\"></p>");
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
			out.println("<a href=\"mostrarMaes\"> Mostrar MAES Disponibles </a>");
			out.println("<br>");
			out.println("<a href=\"mostrarAsistencia\"> Mostrar Asistencia de MAES</a>");
		} else {
			out.println("<form method=\"GET\" action=\"InicioSesion\">");
			out.println("<input type=\"hidden\" name=\"operacion\" value=\"inicio\"/>");
			out.println("<p>Matricula y/o contraseña incorrecta</p>");
			out.println("<p><input type=\"submit\" value=\"Regresar\"name=\"B1\"></p>");
			out.println("</form>");
		}
	}
}

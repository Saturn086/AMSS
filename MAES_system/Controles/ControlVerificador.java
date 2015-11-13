package controles;
import entidades.Usuario;

public class ControlVerificador {
	Usuario usuario;
	
	public ControlVerificador(){
		//Asume que la instancia persiste durante la sesion
    	usuario = new Usuario();
	}

	//Valida si la matricula existe en la base de datos
	public boolean verificarCrendenciales(String strMartricula, String strContrasena) {
    	return(usuario.verificarCrendenciales(strMartricula, strContrasena));
	}

	//Regresa verdadero si el usuario es un administrador
	public boolean esAdministrador(String strMatricula) {
		return(usuario.obtenerTipo(strMatricula) == 1);
	}
}

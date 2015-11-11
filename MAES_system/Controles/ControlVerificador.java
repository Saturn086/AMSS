package controles;
import entidades.Usuario;

public class ControlVerificador {
   Usuario usuario;

   public ControlVerificador(){
     //Asume que la instancia persiste durante la sesion
     usuario = new Usuario();
   }

   //Valida si la matricula existe en la base de datos
   public boolean verificarCrendenciales(int iMartricula, String strContrasena) {
      return(usuario.verificarCrendenciales(iMartricula, strContrasena));
   }

   //Regresa verdadero si el usuario es un administrador
   public boolean esAdministrador(int iMatricula) {
     return(usuario.obtenerTipo(iMatricula) == 1);
   }
}

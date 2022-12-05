package marco.servicio.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import marco.servicio.interfaz.RepositorioUsuario;
import marco.servicio.modelo.Usuario;
import marco.servicio.modelo.Respuesta;

@RestController @RequestMapping("/acceso")
public class ServicioAcceso{
  @Autowired RepositorioUsuario usuarios;
  
  @GetMapping
  public Respuesta entrar(
    @RequestParam(value="alias") String alias,
    @RequestParam(value="contraseña") String contrasena
  ) {
    Usuario usuario = usuarios.findByAlias(alias);
    
    if(
      usuario != null && 
      usuario.getContrasena().equals(contrasena)){  
      usuario.setContrasena(null);
      return new Respuesta("Acceso correcto", usuario, true);
    }return new Respuesta("Falló usuario o contraseña", null, false);
  }//method
}//class
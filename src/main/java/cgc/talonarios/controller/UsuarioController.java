package cgc.talonarios.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cgc.talonarios.model.Usuario;
import cgc.talonarios.pojo.Password;
import cgc.talonarios.pojo.ResponseStatus;
import cgc.talonarios.services.UsuarioService;

//https://stackoverflow.com/questions/31047052/how-to-check-if-username-exists-in-angularjs

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Usuario> listarRegistros(){
		List<Usuario> usuarios = new ArrayList<Usuario>();
		try {
			usuarios = usuarioService.listAll();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return usuarios;
	}

	@RequestMapping(value = "/registro", method = RequestMethod.POST)
	public ResponseStatus registroUsuario(@RequestBody Usuario usuario){
		ResponseStatus status = new ResponseStatus(403,"Peticion Incorrecta");		
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Usuario temp = usuarioService.findByUsername(usuario.getNombre());
			if(temp == null){
				usuario.setNombre(usuario.getNombre().toUpperCase());
				usuario.setEstado("A");
				usuario.setUsuarioInsert(auth.getName().toUpperCase());
				usuario.setFechaInsert(new Date());
				usuarioService.save(usuario);
				status.setCode(200);
				status.setMessage("Usuario Creado Correctamente");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	@RequestMapping(value = "/modificar", method = RequestMethod.POST)
	public ResponseStatus modifUsuario(@RequestBody Usuario usuario){
		ResponseStatus status = new ResponseStatus(403,"Peticion Incorrecta");		
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if(usuario != null && usuario.getIdUsuario() != null){
				usuario.setUsuarioUpdate(auth.getName().toUpperCase());
				usuario.setFechaUpdate(new Date());
				usuarioService.update(usuario);
				status.setCode(200);
				status.setMessage("Usuario Modificado Correctamente");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	@RequestMapping(value = "/anular", method = RequestMethod.POST)
	public ResponseStatus deleteUsuario(@RequestBody Usuario usuario){
		ResponseStatus status = new ResponseStatus(403,"Peticion Incorrecta");		
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if(usuario != null && usuario.getIdUsuario() != null){
				if(usuario.getEstado().equalsIgnoreCase("A")){
					usuario.setEstado("I");
				}else{
					usuario.setEstado("A");
				}
				
				usuario.setUsuarioUpdate(auth.getName().toUpperCase());
				usuario.setFechaUpdate(new Date());
				usuarioService.update(usuario);
				status.setCode(200);
				status.setMessage(usuario.getEstado() == "A" ? "Se Activo el usuario: "+usuario.getNombre() : "Se inactivo el usuario: "+usuario.getNombre());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	
}

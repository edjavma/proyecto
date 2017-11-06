package cgc.talonarios.controller;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cgc.talonarios.model.Usuario;
import cgc.talonarios.pojo.Password;
import cgc.talonarios.pojo.ResponseStatus;
import cgc.talonarios.services.UsuarioService;

@RestController
@RequestMapping("/password")
public class PasswordController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@RequestMapping(value = "/cambio", method = RequestMethod.POST)
	public ResponseStatus passUsuario(@RequestBody Password password){
		ResponseStatus status = new ResponseStatus(403,"Peticion Incorrecta");		
		try {
			boolean isAdmin = false;
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Collection<? extends GrantedAuthority> authorities =  auth.getAuthorities();
			for(GrantedAuthority authority: authorities){
				if(authority.getAuthority().equalsIgnoreCase("ROLE_ADMIN")){
					isAdmin = true;
					break;
				}
			}
			
			if(password != null){
				if(password.getIdUsuario() != null){
					if(isAdmin){
						Usuario usuario = usuarioService.findById(password.getIdUsuario());
						usuario.setPassword(passwordEncoder.encode(password.getPassword()));
						usuario.setUsuarioUpdate(auth.getName().toUpperCase());
						usuario.setFechaUpdate(new Date());
						usuarioService.update(usuario);
						status.setCode(200);
						status.setMessage("Password Modificado Correctamente");
					}else{
						status.setCode(403);
						status.setMessage(auth.getName()+" su usuario no tiene permisos para realizar esta accion!");
					}
				}else{
					Usuario usuario = usuarioService.findByUsername(auth.getName());					
					if(usuario != null){
						//boolean compare = usuario.getPassword().equals(passwordEncoder.encode(password.getLastpass()));
						boolean compare = passwordEncoder.matches(password.getLastpass(), usuario.getPassword());
						if(compare){
							usuario.setPassword(passwordEncoder.encode(password.getPassword()));
							usuario.setUsuarioUpdate(auth.getName().toUpperCase());
							usuario.setFechaUpdate(new Date());
							usuarioService.update(usuario);
							status.setCode(200);
							status.setMessage("Password Modificado Correctamente");
						}else{
							status.setCode(403);
							status.setMessage("La contrase�a anterior no coincide con la que esta actualmente.");
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

}

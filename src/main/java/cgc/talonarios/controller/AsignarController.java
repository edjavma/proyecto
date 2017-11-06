package cgc.talonarios.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cgc.talonarios.model.UsuarioRole;
import cgc.talonarios.pojo.ResponseStatus;
import cgc.talonarios.services.RoleService;
import cgc.talonarios.services.UsuarioRoleService;
import cgc.talonarios.services.UsuarioService;

@RestController
@RequestMapping("/asignar")
public class AsignarController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UsuarioRoleService usuarioRoleService;
	
	/*@RequestMapping(value="/listar",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ObjectResult asignadosPorUsuario(@RequestParam(value = "usuario", required = false) BigDecimal usuario){
		ObjectResult result = new ObjectResult();
		try {
			if(usuario != null){
				List<Role> source = roleService.listNotTargetByIdUser(usuario);
				List<Role> target = usuarioService.findRolesByUsuario(usuario);
				
				result.setIdUsuario(usuario);
				result.setSource(source);
				result.setTarget(target);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}*/
	
	@RequestMapping(value="/guardar",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseStatus guardarAsignacion(@RequestParam BigDecimal usuario,
			@RequestParam BigDecimal role){
		ResponseStatus status = new ResponseStatus(403,"Peticion Incorrecta");
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if(usuario != null && role != null){
				UsuarioRole tmp = usuarioRoleService.findByUsuarioByRole(usuario, role);
					if(tmp == null){
						UsuarioRole usuarioRole = new UsuarioRole();		
						usuarioRole.setEstado("A");
						usuarioRole.setFechaInsert(new Date());
						usuarioRole.setUsuarioInsert(auth.getName().toUpperCase());
						usuarioRole.setIdRole(roleService.findById(role));
						usuarioRole.setIdUsuario(usuarioService.findById(usuario));
						usuarioRoleService.create(usuarioRole);					
					}else{
						tmp.setUsuarioUpdate(auth.getName().toUpperCase());
						tmp.setFechaUpdate(new Date());
						tmp.setEstado("A");
						usuarioRoleService.update(tmp);
					}
					status.setCode(200);
					status.setMessage("Asignacion realizada con exito");
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return status;
	}
	
	@RequestMapping(value="/estado",method = RequestMethod.POST)
	public ResponseStatus modificaAsignacion(@RequestParam(value = "usuarioRole", required = true) BigDecimal usuarioRole){
		ResponseStatus status = new ResponseStatus(403,"Peticion Incorrecta");
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if(usuarioRole != null){
					boolean asign = true;
					UsuarioRole usRole = usuarioRoleService.findUsuarioRole(usuarioRole);	
					if(usRole != null){
						
						if(usRole.getEstado().equalsIgnoreCase("A")){
							usRole.setEstado("I");
							asign = false;
						}else{
							usRole.setEstado("A");
							asign = true;
						}						
						
						usRole.setFechaUpdate(new Date());
						usRole.setUsuarioUpdate(auth.getName().toUpperCase());					
						usuarioRoleService.update(usRole);
						status.setCode(200);
						status.setMessage(asign ? "Se Ha activado el permiso para el usuario: "+usRole.getIdUsuario().getNombre() : "Se inactivo el permiso para el usuario: "+usRole.getIdUsuario().getNombre());
					}					
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return status;
	}
	
	
	@RequestMapping(value="/listar",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UsuarioRole> asignadosPorUsuario(@RequestParam(value = "usuario", required = false) BigDecimal usuario){
		List<UsuarioRole> result = new ArrayList<UsuarioRole>();
		try {
			if(usuario != null){
					result = usuarioRoleService.findUserRolesById(usuario);			
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

}

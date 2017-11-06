package cgc.talonarios.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cgc.talonarios.model.Role;
import cgc.talonarios.pojo.ResponseStatus;
import cgc.talonarios.services.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Role> listarRegistros(){
		List<Role> roles = new ArrayList<Role>();
		try {
			roles = roleService.listAll();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return roles;
	}
	
	@RequestMapping(value = "/usuario", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Role> listarRegistrosPorUsuario(@RequestParam("usuario") BigDecimal usuario){
		List<Role> roles = new ArrayList<Role>();
		try {
			roles = roleService.listNotTargetByIdUser(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return roles;
	}
	
	@RequestMapping(value = "/registro", method = RequestMethod.POST)
	public ResponseStatus createRegistro(@RequestBody Role role){
		ResponseStatus response = new ResponseStatus(403,"Peticion Incorrecta");
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Role roleTmp = roleService.findByNombre(role.getNombre());
			if(roleTmp == null){
				role.setNombre(role.getNombre().toUpperCase());
				role.setUsuarioInsert(auth.getName().toUpperCase());
				role.setFechaInsert(new Date());
				roleService.create(role);
				response.setCode(200);
				response.setMessage("Rol creado Correctamente");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	@RequestMapping(value = "/modificar", method = RequestMethod.POST)
	public ResponseStatus modificarRegistro(@RequestBody Role role){
		ResponseStatus response = new ResponseStatus(403,"Peticion Incorrecta");
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Role roleTmp = roleService.findById(role.getIdRole());
			if(roleTmp != null){
				roleTmp.setDescripcion(role.getDescripcion());
				roleTmp.setUsuarioUpdate(auth.getName().toUpperCase());
				roleTmp.setFechaUpdate(new Date());
				response.setCode(200);
				response.setMessage("Modificado Correctamente");
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
}

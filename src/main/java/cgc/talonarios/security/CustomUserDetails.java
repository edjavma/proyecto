package cgc.talonarios.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cgc.talonarios.model.Role;
import cgc.talonarios.model.Usuario;
import cgc.talonarios.services.UsuarioService;

@Service("customUserDetailsService")
public class CustomUserDetails implements UserDetailsService{

	@Autowired
	UsuarioService usuarioService; 
	
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		Usuario usuario = usuarioService.findByUsername(username);
		if(usuario==null){
			throw new UsernameNotFoundException("Username not found");
		}
		return new org.springframework.security.core.userdetails.User(usuario.getNombre(), usuario.getPassword(), 
				 true, true, true, true, getGrantedAuthorities(usuario));
	}
	
	private List<GrantedAuthority> getGrantedAuthorities(Usuario usuario){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		List<Role> roles = usuarioService.findRolesByUsuario(usuario.getIdUsuario());
		for(Role rol:roles){
			authorities.add(new SimpleGrantedAuthority(rol.getNombre()));
		}
		return authorities;
	}
}

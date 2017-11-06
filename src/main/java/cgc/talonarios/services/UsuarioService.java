package cgc.talonarios.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cgc.talonarios.model.Role;
import cgc.talonarios.model.Usuario;
import cgc.talonarios.repositories.RoleRepository;
import cgc.talonarios.repositories.UsuarioRepository;

public interface UsuarioService {
	public void save(Usuario usuario);
	public void update(Usuario usuario);
	public Usuario findByUsername(String username);
	public Usuario findById(BigDecimal idUsuario);
	public List<Role> findRolesByUsuario(BigDecimal idUsuario);
	public List<Usuario> listAll();
}

@Service
class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Usuario findByUsername(String username) {
		try {
			return usuarioRepository.findByUsername(username);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Role> findRolesByUsuario(BigDecimal idUsuario) {
		try {
			return roleRepository.findRolesByUsuario(idUsuario);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Role>();
		}
	}

	@Override
	public List<Usuario> listAll() {
		try {
			return usuarioRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Usuario>();
		}
	}

	@Override
	public void save(Usuario usuario) {
		try {
			usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
			usuarioRepository.save(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Usuario usuario) {
		try {
			usuarioRepository.save(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Usuario findById(BigDecimal idUsuario) {
		try {
			return usuarioRepository.findOne(idUsuario);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
}

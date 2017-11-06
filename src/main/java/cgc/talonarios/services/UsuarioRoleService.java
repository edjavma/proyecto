package cgc.talonarios.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cgc.talonarios.model.UsuarioRole;
import cgc.talonarios.repositories.UsuarioRoleRepository;

public interface UsuarioRoleService {
	public void create(UsuarioRole usuarioRole);
	public void update(UsuarioRole usuarioRole);
	public UsuarioRole findUsuarioRole(BigDecimal usuarioRole);
	public UsuarioRole findByUsuarioByRole(BigDecimal idUsuario, BigDecimal idRole);
	public List<UsuarioRole> findUserRolesById(BigDecimal idUsuario);
}

@Service
class UsuarioRoleServiceImpl implements UsuarioRoleService {

	@Autowired	
	UsuarioRoleRepository usuarioRoleRepository;

	@Override
	public UsuarioRole findUsuarioRole(BigDecimal usuarioRole) {
		try {
			return usuarioRoleRepository.findOne(usuarioRole);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public UsuarioRole findByUsuarioByRole(BigDecimal idUsuario,
			BigDecimal idRole) {
		try {
			return usuarioRoleRepository.findByUsuarioByRole(idUsuario, idRole);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<UsuarioRole> findUserRolesById(BigDecimal idUsuario) {
		try {
			return usuarioRoleRepository.findUserRolesById(idUsuario);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<UsuarioRole>();
		}
	}

	@Override
	public void create(UsuarioRole usuarioRole) {
		try {
			usuarioRoleRepository.save(usuarioRole);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(UsuarioRole usuarioRole) {
		try {
			usuarioRoleRepository.save(usuarioRole);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}

package cgc.talonarios.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cgc.talonarios.model.Role;
import cgc.talonarios.repositories.RoleRepository;

public interface RoleService{
	public void create(Role role);
	public void update(Role role);
	public Role findByNombre(String nombre);
	public Role findById(BigDecimal idRole);
	public List<Role> listNotTargetByIdUser(BigDecimal idUsuario);
	public List<Role> listAll();
}

@Service
class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<Role> listNotTargetByIdUser(BigDecimal idUsuario) {
		try {
			return roleRepository.listNotTargetByIdUser(idUsuario);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Role>();
		}
	}

	@Override
	public void create(Role role) {
		try {
			roleRepository.save(role);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Role role) {
		try {
			roleRepository.save(role);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Role> listAll() {
		try {
			return roleRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Role>();
		}
	}

	@Override
	public Role findByNombre(String nombre) {
		try {
			return roleRepository.findByNombre(nombre);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Role findById(BigDecimal idRole) {
		try {
			return roleRepository.findOne(idRole);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}

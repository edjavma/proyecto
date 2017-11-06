package cgc.talonarios.repositories;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cgc.talonarios.model.UsuarioRole;


@Repository
public abstract interface UsuarioRoleRepository extends JpaRepository<UsuarioRole, Serializable>{

	@Query("SELECT u FROM UsuarioRole u WHERE u.idUsuario.idUsuario = :idUsuario AND u.idRole.idRole = :idRole ")
	public abstract UsuarioRole findByUsuarioByRole(@Param("idUsuario") BigDecimal idUsuario,@Param("idRole") BigDecimal idRole);
	
	@Query("SELECT r FROM UsuarioRole r WHERE r.idUsuario.idUsuario = :idUsuario ")	
	public abstract List<UsuarioRole> findUserRolesById(@Param("idUsuario") BigDecimal idUsuario);
}

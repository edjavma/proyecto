package cgc.talonarios.repositories;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cgc.talonarios.model.Role;

public abstract interface RoleRepository extends JpaRepository<Role, Serializable>{

	@Query(value="SELECT R.* FROM RCC_USUARIO_ROLE U INNER JOIN RCC_ROLE R ON U.ID_rOLE = R.ID_ROLE WHERE U.ID_USUARIO = :usuario AND U.ESTADO = 'A'", nativeQuery=true)
	public abstract List<Role> findRolesByUsuario(@Param("usuario") BigDecimal idUsuario);
	
	@Query(value = "SELECT A.* FROM RCC_ROLE A WHERE NOT EXISTS ( SELECT 1 FROM RCC_USUARIO_ROLE WHERE ID_ROLE = A.ID_ROLE AND ID_USUARIO = :usuario ) ORDER BY A.NOMBRE ASC", nativeQuery = true)
	public abstract List<Role> listNotTargetByIdUser(@Param("usuario") BigDecimal idUsuario);
	
	@Query(value = "SELECT R.* FROM RCC_ROLE R WHERE UPPER(R.NOMBRE) = UPPER(:nombre)", nativeQuery = true)
	public abstract Role findByNombre(@Param("nombre") String nombre);
	
}

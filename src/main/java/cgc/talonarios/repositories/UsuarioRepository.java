package cgc.talonarios.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cgc.talonarios.model.Usuario;

@Repository
public abstract interface UsuarioRepository extends JpaRepository<Usuario, Serializable>{

	@Query(value="SELECT U.* FROM RCC_USUARIO U WHERE UPPER(U.NOMBRE) = UPPER(:nombre) AND U.ESTADO = 'A' ", nativeQuery=true)
	public abstract Usuario findByUsername(@Param("nombre") String username);
	
}

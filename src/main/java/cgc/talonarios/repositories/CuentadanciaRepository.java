package cgc.talonarios.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cgc.talonarios.model.Cuentadancia;


@Repository
public abstract interface CuentadanciaRepository extends JpaRepository<Cuentadancia, Serializable>{
	
	@Query(value = "SELECT C.* FROM RCC_CUENTADANTES C WHERE C.CODIGO_CUENTADANCIA = :codigo ", nativeQuery = true)
	public abstract Cuentadancia findByCodigo(@Param("codigo") String codigo);
	
	@Query(value = "SELECT * FROM RCC_CUENTADANTES WHERE ESTADO = 'A' AND TRANSLATE(UPPER(DESCRIPCION),'ÁÉÍÓÚÑ','AEIOUN') LIKE REPLACE('%'|| TRANSLATE(UPPER(:texto),'ÁÉÍÓÚÑ','AEIOUN')||'%',' ','%')  ORDER BY DESCRIPCION ASC ", nativeQuery = true)	
	public abstract List<Cuentadancia> findByTexto(@Param("texto") String texto);

}

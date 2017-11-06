package cgc.talonarios.repositories;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cgc.talonarios.model.Rendicion;


@Repository
public abstract interface RendicionRepository extends JpaRepository<Rendicion, Serializable>{

	@Query(value = "SELECT R.* FROM RCC_RENDICION_CONSUL R WHERE R.ANIO = NVL(:anio, R.ANIO) AND R.MES = NVL(:mes, R.MES) ORDER BY R.FECHA_INGRESA DESC", nativeQuery = true)
	public abstract List<Rendicion> listByAnioMes(@Param("anio") String anio,@Param("mes") String mes);
	
	@Query(value = "SELECT * FROM RCC_RENDICION_CONSUL r INNER JOIN RCC_CUENTADANTES c on r.ID_CUENTADANCIA = c.ID_CUENTADANCIA WHERE TRANSLATE(UPPER(DESCRIPCION),'ÁÉÍÓÚÑ','AEIOUN') LIKE REPLACE('%'|| TRANSLATE(UPPER(:texto),'ÁÉÍÓÚÑ','AEIOUN')||'%',' ','%') AND r.anio = NVL(:anio,r.anio) AND r.mes = NVL(:mes,r.mes) ORDER BY r.FECHA_INGRESA DESC", nativeQuery = true)
	public abstract List<Rendicion> listByAnioMesCuentadante(@Param("anio") String anio,@Param("mes") String mes,@Param("texto") String texto);
	
	@Query("SELECT r FROM Rendicion r WHERE UPPER(r.usuarioIngresa) = UPPER(:usuario) AND r.estado = 'A' ORDER BY r.fechaIngresa DESC ")
	public abstract List<Rendicion> listAllByUsuario(@Param("usuario") String usuario);
	
	
	@Query("SELECT r FROM Rendicion r WHERE UPPER(r.usuarioIngresa) = UPPER(:usuario) AND r.idRendicion = :idRendicion")
	public abstract Rendicion getRendicionById(@Param("idRendicion") BigDecimal idRendicion,@Param("usuario") String usuario);
	
	@Query("SELECT r FROM Rendicion r WHERE r.idCuentadancia.codigoCuentadancia = :codigo AND  r.anio = :anio AND r.mes = :mes AND r.estado = 'A'")
	public abstract Rendicion getRendicionByCuentadanteAnioMes(@Param("codigo") String codigo,@Param("anio") String anio,@Param("mes") String mes);
	
}

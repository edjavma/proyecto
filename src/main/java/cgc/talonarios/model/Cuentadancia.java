package cgc.talonarios.model;

import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "RCC_CUENTADANTES")
public class Cuentadancia {
	
	@Id
	@Basic(optional = false)
	@SequenceGenerator(name = "eSequence", sequenceName = "SEQ_CUENTADANCIA", allocationSize = 1)
    @GeneratedValue(generator = "eSequence",strategy = GenerationType.SEQUENCE)
	@Column(name = "ID_CUENTADANCIA")
	private BigDecimal idCuentadancia;
	
	@Basic(optional = false)
	@Column(name = "CODIGO_CUENTADANCIA")
	private String codigoCuentadancia;
	
	@Basic(optional = false)
	@Column(name = "DESCRIPCION")
	private String descripcion;
	
	@Basic(optional = false)
	@Column(name = "CODIGO_FINANCIERO")
	private BigDecimal codigoFinanciero;
	
	@Basic(optional = false)
	@Column(name = "ESTADO")
	private String estado;
	
	public Cuentadancia() {
		// TODO Auto-generated constructor stub
	}
	
	

	public Cuentadancia(BigDecimal idCuentadancia, String codigoCuentadancia,
			String descripcion, BigDecimal codigoFinanciero, String estado) {
		super();
		this.idCuentadancia = idCuentadancia;
		this.codigoCuentadancia = codigoCuentadancia;
		this.descripcion = descripcion;
		this.codigoFinanciero = codigoFinanciero;
		this.estado = estado;
	}



	public BigDecimal getIdCuentadancia() {
		return idCuentadancia;
	}

	public void setIdCuentadancia(BigDecimal idCuentadancia) {
		this.idCuentadancia = idCuentadancia;
	}

	public String getCodigoCuentadancia() {
		return codigoCuentadancia;
	}

	public void setCodigoCuentadancia(String codigoCuentadancia) {
		this.codigoCuentadancia = codigoCuentadancia;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getCodigoFinanciero() {
		return codigoFinanciero;
	}

	public void setCodigoFinanciero(BigDecimal codigoFinanciero) {
		this.codigoFinanciero = codigoFinanciero;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	

}

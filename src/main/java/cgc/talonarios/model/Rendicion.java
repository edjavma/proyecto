package cgc.talonarios.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "RCC_RENDICION_CONSUL")
public class Rendicion {
	
	@Id
	@Basic(optional = false)
	@SequenceGenerator(name = "dSequence", sequenceName = "SEQ_RENDICION", allocationSize = 1)
    @GeneratedValue(generator = "dSequence",strategy = GenerationType.SEQUENCE)
	@Column(name = "ID_RENDICION")
	private BigDecimal idRendicion;
	
	@Basic(optional = false)
	@ManyToOne
	@JoinColumn(name = "ID_CUENTADANCIA", referencedColumnName = "ID_CUENTADANCIA")
	private Cuentadancia idCuentadancia;
	
	@Basic(optional = false)
	@Column(name = "MES")
	private String mes;
	
	@Basic(optional = false)
	@Column(name = "ANIO")
	private String anio;
	
	@Basic(optional = false)
	@Column(name = "ARCHIVO")
	private String archivo;
	
	@Basic(optional = false)
	@Column(name = "USUARIO_INGRESA")
	private String usuarioIngresa;
	
	@Basic(optional = false)
	@Column(name = "FECHA_INGRESA")
	private Date fechaIngresa;
	
	@Basic(optional = false)
	@Column(name = "ESTADO")
	private String estado;
	
	@Basic(optional = true)
	@Column(name = "USUARIO_ANULA")
	private String usuarioAnula;
	
	@Basic(optional = true)
	@Column(name = "FECHA_ANULA")
	private Date fechaAnula;
	
	@Basic(optional = true)
	@Column(name = "MOTIVO_ANULACION")
	private String motivoAnulacion;
	
	public Rendicion() {
		// TODO Auto-generated constructor stub
	}

	public Rendicion(BigDecimal idRendicion, String mes, String anio,
			String archivo, String usuarioIngresa, Date fechaIngresa) {
		super();
		this.idRendicion = idRendicion;
		this.mes = mes;
		this.anio = anio;
		this.archivo = archivo;
		this.usuarioIngresa = usuarioIngresa;
		this.fechaIngresa = fechaIngresa;
	}

	public BigDecimal getIdRendicion() {
		return idRendicion;
	}

	public void setIdRendicion(BigDecimal idRendicion) {
		this.idRendicion = idRendicion;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	
	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	public String getUsuarioIngresa() {
		return usuarioIngresa;
	}

	public void setUsuarioIngresa(String usuarioIngresa) {
		this.usuarioIngresa = usuarioIngresa;
	}

	public Date getFechaIngresa() {
		return fechaIngresa;
	}

	public void setFechaIngresa(Date fechaIngresa) {
		this.fechaIngresa = fechaIngresa;
	}

	public Cuentadancia getIdCuentadancia() {
		return idCuentadancia;
	}

	public void setIdCuentadancia(Cuentadancia idCuentadancia) {
		this.idCuentadancia = idCuentadancia;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getUsuarioAnula() {
		return usuarioAnula;
	}

	public void setUsuarioAnula(String usuarioAnula) {
		this.usuarioAnula = usuarioAnula;
	}

	public Date getFechaAnula() {
		return fechaAnula;
	}

	public void setFechaAnula(Date fechaAnula) {
		this.fechaAnula = fechaAnula;
	}

	public String getMotivoAnulacion() {
		return motivoAnulacion;
	}

	public void setMotivoAnulacion(String motivoAnulacion) {
		this.motivoAnulacion = motivoAnulacion;
	}
	
	

}

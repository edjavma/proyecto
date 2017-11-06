package cgc.talonarios.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "RCC_USUARIO")
public class Usuario {
	
	@Id
	@Basic(optional = false)
	@SequenceGenerator(name = "bSequence", sequenceName = "SEQ_USUARIO", allocationSize = 1)
    @GeneratedValue(generator = "bSequence",strategy = GenerationType.SEQUENCE)
	@Column(name = "ID_USUARIO")
	private BigDecimal idUsuario;
	
	@Basic(optional = false)
	@Column(name = "NOMBRE")
	private String nombre;
	
	@Basic(optional = false)
	@Column(name = "PASSWORD")
	private String password;
	
	@Basic(optional = false)
	@Column(name = "ESTADO")
	private String estado;
	
	@Basic(optional = false)
	@Column(name = "USUARIO_INSERT")
	private String usuarioInsert;
	
	@Basic(optional = false)
	@Column(name = "FECHA_INSERT")
	private Date fechaInsert;
	
	@Basic(optional = true)
	@Column(name = "USUARIO_UPDATE")
	private String usuarioUpdate;
	
	@Basic(optional = true)
	@Column(name = "FECHA_UPDATE")
	private Date fechaUpdate;
	
	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	public Usuario(BigDecimal idUsuario, String nombre, String password,
			String estado, String usuarioInsert, Date fechaInsert,
			String usuarioUpdate, Date fechaUpdate) {
		super();
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.password = password;
		this.estado = estado;
		this.usuarioInsert = usuarioInsert;
		this.fechaInsert = fechaInsert;
		this.usuarioUpdate = usuarioUpdate;
		this.fechaUpdate = fechaUpdate;
	}

	public BigDecimal getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(BigDecimal idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getUsuarioInsert() {
		return usuarioInsert;
	}

	public void setUsuarioInsert(String usuarioInsert) {
		this.usuarioInsert = usuarioInsert;
	}

	public Date getFechaInsert() {
		return fechaInsert;
	}

	public void setFechaInsert(Date fechaInsert) {
		this.fechaInsert = fechaInsert;
	}

	public String getUsuarioUpdate() {
		return usuarioUpdate;
	}

	public void setUsuarioUpdate(String usuarioUpdate) {
		this.usuarioUpdate = usuarioUpdate;
	}

	public Date getFechaUpdate() {
		return fechaUpdate;
	}

	public void setFechaUpdate(Date fechaUpdate) {
		this.fechaUpdate = fechaUpdate;
	}
	
	
	

}

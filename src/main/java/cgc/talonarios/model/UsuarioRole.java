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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "RCC_USUARIO_ROLE")
public class UsuarioRole {
	
	@Id
	@Basic(optional = false)
	@SequenceGenerator(name = "cSequence", sequenceName = "SEQ_USUARIO_ROLE", allocationSize = 1)
    @GeneratedValue(generator = "cSequence",strategy = GenerationType.SEQUENCE)
	@Column(name = "ID_USUARIO_ROLE")
	private BigDecimal idUsuarioRole;
	
	@Basic(optional = false)
	@ManyToOne
	@JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
	private Usuario idUsuario;
	
	@Basic(optional = false)
	@ManyToOne
	@JoinColumn(name = "ID_ROLE", referencedColumnName = "ID_ROLE")
	private Role idRole;
	
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
	
	public UsuarioRole() {
		// TODO Auto-generated constructor stub
	}

	public UsuarioRole(BigDecimal idUsuarioRole, Usuario idUsuario,
			Role idRole, String estado, String usuarioInsert, Date fechaInsert,
			String usuarioUpdate, Date fechaUpdate) {
		super();
		this.idUsuarioRole = idUsuarioRole;
		this.idUsuario = idUsuario;
		this.idRole = idRole;
		this.estado = estado;
		this.usuarioInsert = usuarioInsert;
		this.fechaInsert = fechaInsert;
		this.usuarioUpdate = usuarioUpdate;
		this.fechaUpdate = fechaUpdate;
	}

	public BigDecimal getIdUsuarioRole() {
		return idUsuarioRole;
	}

	public void setIdUsuarioRole(BigDecimal idUsuarioRole) {
		this.idUsuarioRole = idUsuarioRole;
	}

	public Usuario getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Usuario idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Role getIdRole() {
		return idRole;
	}

	public void setIdRole(Role idRole) {
		this.idRole = idRole;
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

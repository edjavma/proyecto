package cgc.talonarios.pojo;

import java.math.BigDecimal;
import java.util.List;

import cgc.talonarios.model.Role;

public class ObjectResult {
	
	private List<Role> source;
	private List<Role> target;
	private BigDecimal idUsuario;
	
	public ObjectResult() {
		// TODO Auto-generated constructor stub
	}
	
	

	public ObjectResult(List<Role> source, List<Role> target,
			BigDecimal idUsuario) {
		super();
		this.source = source;
		this.target = target;
		this.idUsuario = idUsuario;
	}



	public List<Role> getSource() {
		return source;
	}

	public void setSource(List<Role> source) {
		this.source = source;
	}

	public List<Role> getTarget() {
		return target;
	}

	public void setTarget(List<Role> target) {
		this.target = target;
	}

	public BigDecimal getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(BigDecimal idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	

}

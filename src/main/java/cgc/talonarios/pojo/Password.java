package cgc.talonarios.pojo;

import java.math.BigDecimal;

public class Password {

	private BigDecimal idUsuario;
	private String password;
	private String lastpass;
	
	public Password() {
		// TODO Auto-generated constructor stub
	}

	public Password(BigDecimal idUsuario, String password, String lastpass) {
		super();
		this.idUsuario = idUsuario;
		this.password = password;
		this.lastpass = lastpass;
	}

	public BigDecimal getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(BigDecimal idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLastpass() {
		return lastpass;
	}

	public void setLastpass(String lastpass) {
		this.lastpass = lastpass;
	}
	
	
	
}

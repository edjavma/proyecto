package cgc.talonarios.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import cgc.talonarios.model.Rendicion;
import cgc.talonarios.repositories.RendicionRepository;

public interface RendicionesService {
	public Rendicion create(Rendicion rendicion) throws Exception;
	public List<Rendicion> listAll();
	public List<Rendicion> listByAnioMes(String anio, String mes);
	public List<Rendicion> listByAnioMesCuentadante(String anio, String mes, String texto);
	public List<Rendicion> listAllByUsuario(String usuario);
	public Rendicion getRendicionById(BigDecimal idRendicion, String usuario);
	public Rendicion getRendicionByIdLector(BigDecimal idRendicion);
	public Rendicion getRendicionByCuentadanteAnioMes(String codigoCuentadante, String anio, String mes);
}

@Service
class RendicionesServiceImpl implements RendicionesService {

	@Autowired
	private RendicionRepository rendicionRepository;

	@Override
	public Rendicion create(Rendicion rendicion) throws Exception {
		try {
			return rendicionRepository.save(rendicion);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	 private Sort sortByFechaDESC() {
	        return new Sort(Sort.Direction.DESC, "fechaIngresa");
	    }

	@Override
	public List<Rendicion> listAll() {
		try {
			return rendicionRepository.findAll(sortByFechaDESC());
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Rendicion>();
		}
	}

	@Override
	public List<Rendicion> listByAnioMes(String anio, String mes) {
		try {
			return rendicionRepository.listByAnioMes(anio, mes);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Rendicion>();
		}
	}

	@Override
	public List<Rendicion> listByAnioMesCuentadante(String anio, String mes,
			String texto) {
		try {
			return rendicionRepository.listByAnioMesCuentadante(anio, mes, texto);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Rendicion>();
		}
	}

	@Override
	public List<Rendicion> listAllByUsuario(String usuario) {
		try {
			return rendicionRepository.listAllByUsuario(usuario);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Rendicion>();
		}
	}

	@Override
	public Rendicion getRendicionById(BigDecimal idRendicion, String usuario) {
		try {
			return rendicionRepository.getRendicionById(idRendicion, usuario);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Rendicion getRendicionByIdLector(BigDecimal idRendicion) {
		try {
			return rendicionRepository.findOne(idRendicion);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Rendicion getRendicionByCuentadanteAnioMes(String codigoCuentadante,
			String anio, String mes) {
		try {
			return rendicionRepository.getRendicionByCuentadanteAnioMes(codigoCuentadante, anio, mes);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
}

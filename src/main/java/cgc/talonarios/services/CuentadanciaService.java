package cgc.talonarios.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cgc.talonarios.model.Cuentadancia;
import cgc.talonarios.repositories.CuentadanciaRepository;

public interface CuentadanciaService {
	public Cuentadancia findByCodigo(String codigo);
	public List<Cuentadancia> findByTexto(String texto);
}

@Service
class CuentadanciaServiceImpl implements CuentadanciaService {

	@Autowired
	private CuentadanciaRepository cuentadanciaRepository;
	
	@Override
	public Cuentadancia findByCodigo(String codigo) {
		try {
			return cuentadanciaRepository.findByCodigo(codigo);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Cuentadancia> findByTexto(String texto) {
		try {
			return cuentadanciaRepository.findByTexto(texto);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Cuentadancia>();
		}
	}

}

package cgc.talonarios.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cgc.talonarios.model.Cuentadancia;
import cgc.talonarios.model.Rendicion;
import cgc.talonarios.pojo.ResponseStatus;
import cgc.talonarios.services.CuentadanciaService;
import cgc.talonarios.services.RendicionesService;
import cgc.talonarios.util.Month;


@RestController
public class RendicionController {

	@Autowired
	RendicionesService rendicionService;
	
	@Autowired
	CuentadanciaService cuentadanciaService;
	
	 private static final String UPLOADED_FOLDER = "C://tmp";
	 private static final Integer MAX_SIZE = 5 * 1024 *1024;
	
	@RequestMapping(value = "/guardar",method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseStatus responseInsert(@RequestParam("file") MultipartFile file, @RequestParam("mes") String mes,
			@RequestParam("anio") String anio, @RequestParam("cuentadante") String cuentadante){
		ResponseStatus response = new ResponseStatus(403,"Peticion Incorrecta");
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if(file != null){
				boolean eval = true;
				
			//	if(!file.getOriginalFilename().endsWith(".pdf")){
				if(!FilenameUtils.getExtension(file.getOriginalFilename()).equalsIgnoreCase("PDF")){
					response.setCode(403);
					response.setMessage("Solo puede a�adir archivos <b>PDF</b>.");
					eval = false;
				}
				
				if(file.getSize() > MAX_SIZE){
					response.setCode(403);
					response.setMessage("El archivo no puede Exceder los <b>5MB</b>.");
					eval = false;
				}
				
				Rendicion existeRendicion = rendicionService.getRendicionByCuentadanteAnioMes(cuentadante, anio, mes);
				if(existeRendicion != null){
					response.setCode(403);
					response.setMessage("Ya Existe un registro del cuentadante: <b>"+cuentadante+"</b> en el mes de <b>"+ Month.getNameByValue(Integer.valueOf(mes))+"</b>");
					eval = false;
				}
				
				if(eval){
					byte[] byteArr = file.getBytes();
					SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
					SimpleDateFormat formatDate = new SimpleDateFormat("ddMMyyyyhhmmss");
					File dir = new File(UPLOADED_FOLDER + File.separator + format.format(new Date()));
					if (!dir.exists())
						dir.mkdirs();
					
					Path path = Paths.get(dir.getAbsolutePath() + File.separator + formatDate.format(new Date()) + "_" + file.getOriginalFilename().replaceAll(" ", "_"));
					Files.write(path, byteArr);
				
					//System.out.println(System.getProperty("catalina.home"));
					Rendicion rendicion = new Rendicion();
					rendicion.setArchivo(path.toAbsolutePath().toString());
					rendicion.setAnio(anio);
					rendicion.setMes(mes);
					rendicion.setUsuarioIngresa(auth.getName().toUpperCase());
					rendicion.setFechaIngresa(new Date());
					rendicion.setIdCuentadancia(cuentadanciaService.findByCodigo(cuentadante));
					rendicion.setEstado("A");
					Rendicion tmp = rendicionService.create(rendicion);
					response.setCode(200);
					response.setMessage("Registro Creado Correctamente. ID de rendicion: <b>"+tmp.getIdRendicion()+"</b>");
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	@RequestMapping(value = "/listar",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Rendicion> listarRendicionPorUsuario(){
		List<Rendicion> results = new ArrayList<Rendicion>();
		try {
			boolean isLector = false;
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Collection<? extends GrantedAuthority> authorities =  auth.getAuthorities();
			for(GrantedAuthority authority: authorities){
				if(authority.getAuthority().equalsIgnoreCase("ROLE_LECTURA")){
					isLector = true;
					break;
				}
			}
			
			/*if(isLector){
				results = rendicionService.listAll();
			}else{
				results = rendicionService.listAllByUsuario(auth.getName());
			}*/
			results = rendicionService.listAllByUsuario(auth.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}
	
	@RequestMapping(value = "/busqueda",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Cuentadancia> searchCuentadancia(@RequestParam("texto") String texto){
		List<Cuentadancia> results = new ArrayList<Cuentadancia>();
		try {
			results = cuentadanciaService.findByTexto(URLDecoder.decode(texto,"UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}
	
	@RequestMapping(value = "/descarga/{id}")
	public void responseFile(@PathVariable ("id" ) BigDecimal id, HttpServletResponse response, HttpServletRequest request){
		try {
			boolean isLector = false;
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Collection<? extends GrantedAuthority> authorities =  auth.getAuthorities();
			for(GrantedAuthority authority: authorities){
				if(authority.getAuthority().equalsIgnoreCase("ROLE_LECTURA")){
					isLector = true;
					break;
				}
			}	
			
			
			if(id != null){
					Rendicion rendicion = new Rendicion();
					if(isLector){
						rendicion = rendicionService.getRendicionByIdLector(id);
					}else{
						rendicion = rendicionService.getRendicionById(id, auth.getName());
					}
				if(rendicion != null){
					File file = new File(rendicion.getArchivo());
					 FileInputStream inputStream = new FileInputStream(file);
					 System.out.println(file.getName());
					 response.setContentType("application/pdf");
					 response.setHeader("Content-disposition", "attachment; filename="+ file.getName());
					 ByteArrayOutputStream baos = new ByteArrayOutputStream();
					 baos = this.convertInputStreamToByteArrayOutputStream(inputStream);
					 OutputStream os = response.getOutputStream();
				        baos.writeTo(os);
				        os.flush();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value = "/ver/{id}", method = RequestMethod.GET)	
	public byte[] viewPdf(@PathVariable ("id" ) BigDecimal id ){
		//ResponseStatus response = new ResponseStatus(403,"Peticion incorrecta");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			boolean isLector = false;
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Collection<? extends GrantedAuthority> authorities =  auth.getAuthorities();
			for(GrantedAuthority authority: authorities){
				if(authority.getAuthority().equalsIgnoreCase("ROLE_LECTURA")){
					isLector = true;
					break;
				}
			}		
			
			if(id != null){
				Rendicion rendicion = new Rendicion();
				if(isLector){
					rendicion = rendicionService.getRendicionByIdLector(id);
				}else{
					rendicion = rendicionService.getRendicionById(id, auth.getName());
				}
				
				
				if(rendicion != null){
					File file = new File(rendicion.getArchivo());
					FileInputStream inputStream = new FileInputStream(file);
					//String base64 = Base64.encodeBase64String(IOUtils.toByteArray(new FileInputStream(file)));
					baos = this.convertInputStreamToByteArrayOutputStream(inputStream);
				//	String base64 = Base64.encodeBase64String(baos.toByteArray());
					
					
					//response.setCode(200);
					//response.setMessage("data:application/pdf;base64,"+base64);
					//response.setMessage(base64);
				}				 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//return response;
		return baos.toByteArray();
	}
	
	@RequestMapping(value = "/filtro",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Rendicion> getRendicionByAnioMes(@RequestParam(value = "mes", required = false) String mes,
			@RequestParam(value = "anio", required = false) String anio, @RequestParam(value="texto", required = false) String texto){
		List<Rendicion> values = new ArrayList<Rendicion>();
		try {
			boolean isLector = false;
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Collection<? extends GrantedAuthority> authorities =  auth.getAuthorities();
			for(GrantedAuthority authority: authorities){
				if(authority.getAuthority().equalsIgnoreCase("ROLE_LECTURA")){
					isLector = true;
					break;
				}
			}
			
			if(mes == null || mes == "undefined"){
				mes = "";
			}
			
			if(anio == null || anio == "undefined"){
				anio = "";
			}
			
			if(texto == null || texto == "undefined"){
				texto = "";
			}else{
				texto = URLDecoder.decode(texto,"UTF-8");
			}
			
						
				if(isLector){
					//values = rendicionService.listByAnioMes(anio, mes);
					values = rendicionService.listByAnioMesCuentadante(anio, mes, texto);
				}else{
					values = rendicionService.listAllByUsuario(auth.getName());
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return values;
	}
	
	
	private ByteArrayOutputStream convertInputStreamToByteArrayOutputStream(FileInputStream inputStream) {
		 
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
 
			byte[] buffer = new byte[MAX_SIZE];
			baos = new ByteArrayOutputStream();
 
			int bytesRead;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				baos.write(buffer, 0, bytesRead);
			}
 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return baos;
	}
}

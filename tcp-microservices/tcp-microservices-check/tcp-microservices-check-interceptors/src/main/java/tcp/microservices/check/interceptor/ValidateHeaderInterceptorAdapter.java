package tcp.microservices.check.interceptor;

import java.lang.reflect.Method;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import tcp.microservices.check.interceptor.annotations.ValidateHeader;

/**
 * Interceptor que analiza la cabecera de las peticiones REST.
 */
public class ValidateHeaderInterceptorAdapter extends HandlerInterceptorAdapter  { 

	private static final Logger log = LoggerFactory.getLogger(ValidateHeaderInterceptorAdapter.class); 

	@Override 
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler) throws Exception { 
		log.info("Pre-handle Adapter"); 
		HandlerMethod hm=(HandlerMethod)handler; 
		Method method=hm.getMethod(); 
				
//		// Si comprueba si tiene la anotacion //
//		if(method.getDeclaringClass().isAnnotationPresent(RestController.class)) {
//			if(method.isAnnotationPresent(ValidateHeader.class)) { 
//				log.info("valor anotacion: "+method.getAnnotation(ValidateHeader.class).value()); 
//				request.setAttribute("STARTTIME",System.currentTimeMillis());
//				log.info("Cabecera: "+request.getHeaderNames().nextElement());
//				for(Enumeration<String> en=request.getHeaderNames(); en.hasMoreElements() ;) {
//					log.info("Header: "+en.nextElement());					
//				}
//			}
//		} 
		
		response.setHeader("Mi-header", "Valor header");
//		response.sendError(401, "Mi error"); // Si se pone esta línea no hace caso a getWriter
		response.getWriter().write("Mi respuesta de error");
		response.flushBuffer();
		
		// Si es true, progresa al Controlador. Si no, no progresa la petición //
		// Si ya se llama a getWriter no se puede devolver true y dar paso al Controlador. Sí se puede devolver true si se llama a otro interceptor //
		return false; 
	} 

}
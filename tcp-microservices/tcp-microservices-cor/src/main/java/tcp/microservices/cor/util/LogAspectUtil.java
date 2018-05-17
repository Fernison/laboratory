package tcp.microservices.cor.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import tcp.microservices.cor.model.ParameterInfo;

public class LogAspectUtil {
	
	private static final Logger log = LoggerFactory.getLogger(LogAspectUtil.class);
	
	private static final String PATTERN_DATE = "dd/MM/yyyy HH:mm";
	
	public static List<ParameterInfo> getImputParameters(final CodeSignature codeSignature,  final Object[] parameterValues){
		List<ParameterInfo> parameters = new ArrayList<>();
		
		try{
		
			String[] parameterNames = codeSignature.getParameterNames();
			Object[] parameterTypes = codeSignature.getParameterTypes();
			
			for(int i=0; i < parameterNames.length; i++)
				parameters.add(buildParameterInfo(parameterNames[i], parameterTypes[i], parameterValues[i]));
		
		}catch(Exception e){
			log.debug("AuditAspect error {}", e.getMessage(), e);
		}
		
		return parameters;
	}

	public static ParameterInfo buildParameterInfo(String parameterName, Object parameterType, Object parameterValue) {
		ParameterInfo parameter = new ParameterInfo();
		parameter.setName(parameterName);
		parameter.setType(parameterType.toString());
		parameter.setJsonValue(getValueAsJson(parameterValue));
		return parameter;
	}	
	
	public static String getValueAsJson(final Object parameterValue){
		String valueAsJson = null;
		try{ 			
			valueAsJson = createObjectMapper().writeValueAsString(createModelMapper().map(parameterValue, parameterValue.getClass()));
		}catch(Exception e){
			log.debug("Error creating valueAsJson: {}", e.getMessage(), e);
		}
		return valueAsJson;
	}
	
	public static ParameterInfo getOutputParameter(final ProceedingJoinPoint jointPoint, final Object result) {
		ParameterInfo output = new ParameterInfo();
		try{
			MethodSignature methodSignature = (MethodSignature) jointPoint.getSignature();
			Class<?> clazz = Class.forName(methodSignature.getMethod().getReturnType().getCanonicalName());
			output = buildParameterInfo(null, clazz, result);			
		}catch(Exception e){
			log.debug("AuditAspect error {}", e.getMessage(), e);
		}
		return output;
	}
	
	private static ModelMapper createModelMapper(){
		return new ModelMapper(); 
	}
	
	private static ObjectMapper createObjectMapper(){
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		objectMapper.setDateFormat(new SimpleDateFormat(PATTERN_DATE));
		objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		objectMapper.disable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES);
		objectMapper.disable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

		return objectMapper;
	}
}

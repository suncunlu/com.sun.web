package com.sun.webexample.entity.log;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;



@Aspect
@Component
public class WebLogAspect {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Pointcut("within(com.sun.webexample.mvc.*.*)")
	public void webLog() {
	}
	

	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		
		String url = request.getRequestURL().toString();
		String method = request.getMethod();
		String ip = request.getRemoteAddr();
		logger.info("REQUEST : url={},method={},ip={}",url,method,ip);
		Enumeration<String> parameterNames = request.getParameterNames();
		StringBuilder s = new StringBuilder();
		while(parameterNames.hasMoreElements()) {
			String name= parameterNames.nextElement();
			String value = request.getParameter(name);
			s.append(name)
			 .append("=")
			 .append(value)
			 .append(", ");
		}
		logger.info(s.toString());
		
	}
	
    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public <T> void doAfterReturning(Object ret) throws Throwable {
        
        logger.info("RESPONSE : " + ret);
        
    }

    
}

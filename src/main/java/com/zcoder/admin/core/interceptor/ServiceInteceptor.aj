package com.zcoder.admin.core.interceptor;

import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.zcoder.admin.sys.dao.SysLogDao;
import com.zcoder.admin.sys.domain.SysLog;

/**
 * 服务层拦截器 Created by lin on 2016-05-19.
 */
@Aspect
@Component
@Configuration
public class ServiceInteceptor {

	private final Logger logger = LoggerFactory.getLogger(ServiceInteceptor.class);
	
	@Value("${system.log.enabled}")
	private boolean enabled = true;

	private final static int LOG_TYPE_ACCESS = 1;

	private final static int LOG_TYPE_EXCEPTION = 2;

	@Autowired
	private SysLogDao sysLogDao;

	private ThreadLocal<Long> startTime = new ThreadLocal<>();

	private ThreadLocal<SysLog> logThread = new ThreadLocal<>();

	private ExecutorService executor = Executors.newCachedThreadPool();

	@Pointcut("execution(* com.zcoder.admin.*.service..*Service.*(..))")
	public void webLog() {
	}

	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		startTime.set(System.currentTimeMillis());
		// 省略日志记录内容
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		// 记录下请求内容
		if (logger.isDebugEnabled()) {
			logger.debug("URL : " + request.getRequestURL().toString());
			logger.debug("HTTP_METHOD : " + request.getMethod());
			logger.debug("IP : " + request.getRemoteAddr());
			logger.debug("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "."
					+ joinPoint.getSignature().getName());
			logger.debug("ARGS : " + Arrays.toString(joinPoint.getArgs()));
		}

		SysLog log = new SysLog();
		// sysLog.setCreateBy();
		log.setCreateDate(new Date());
		Object[] args = joinPoint.getArgs();
		log.setMethod(joinPoint.getSignature().getName());
		if (args.length > 0) {
			log.setParams(Arrays.toString(args));
		}
		log.setRemoteAddr(request.getRemoteAddr());
		log.setRequestUri(request.getRequestURL().toString());
		log.setUserAgent(request.getHeader("user-agent"));
		log.setType(String.valueOf(LOG_TYPE_ACCESS));
		logThread.set(log);
	}

	@AfterReturning(returning = "ret", pointcut = "webLog()")
	public void doAfterReturning(Object ret) throws Throwable {

		if (logger.isDebugEnabled()) {
			logger.debug("RESPONSE : " + ret);
			logger.debug("SPEND TIME : " + (System.currentTimeMillis() - startTime.get()));
		}

		SysLog currentLog = logThread.get();
		currentLog.setResponse(String.valueOf(ret));
		executor.execute(new MyLog());

	}
	
	/**
	 * 方法抛出异常走此方法
	 * @param joinPoint
	 * @param ex
	 */
	@AfterThrowing(value = "webLog()", throwing = "ex")
	public void afterThrowing(JoinPoint joinPoint, Exception ex) {
		logger.error("method : {}", joinPoint.getSignature().getName());
		logger.error("exception : {}", ex);
		SysLog currentLog = logThread.get();
		currentLog.setType(String.valueOf(LOG_TYPE_EXCEPTION));
		currentLog.setException(ex.getMessage());
		executor.execute(new MyLog());
	}

	class MyLog extends Thread {

		@Override
		public void run() {
			SysLog currentLog = logThread.get();
			if (enabled) {
				sysLogDao.save(currentLog);
			}else{
				logger.warn("system log isnot enabled.please checking 'system.log.enabled' config.");
			}
		}

	}

}

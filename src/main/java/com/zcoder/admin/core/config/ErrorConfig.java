package com.zcoder.admin.core.config;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
/**
 * 500,404页面配置
 * @author lin
 *
 */
@Configuration
public class ErrorConfig  implements EmbeddedServletContainerCustomizer{

	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		
		container.setSessionTimeout(3000);
		container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND,"/error/404"));
		container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,"/error/500"));
		container.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST,"/error/400"));
	}
	
	

}

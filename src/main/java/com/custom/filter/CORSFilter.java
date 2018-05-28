package com.custom.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

@Component
@ServletComponentScan
@WebFilter(urlPatterns = "/*",filterName = "CORSFilter")
public class CORSFilter implements Filter {

	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.setHeader("Access-Control-Allow-Origin", "*");
		httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
		httpResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
		httpResponse.setHeader("Access-Control-Max-Age", "3600");
		httpResponse.setHeader("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, Authorization, Origin, Accept, Access-Control-Request-Method, Access-Control-Request-Headers");
		chain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("--------------------init CORSFilter---------------------------");
	}

}

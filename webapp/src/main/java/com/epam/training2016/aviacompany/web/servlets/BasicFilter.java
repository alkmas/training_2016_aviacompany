package com.epam.training2016.aviacompany.web.servlets;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.epam.training2016.aviacompany.services.AuthenticationService;
import com.epam.training2016.aviacompany.web.components.UserDataStorage;
import com.epam.training2016.aviacompany.web.utils.Headers;

public class BasicFilter implements Filter {
	private AuthenticationService authService;
	private UserDataStorage userDataStorage;
    private ApplicationContext appContext;

	@Override
	public void init(FilterConfig config) throws ServletException {
        WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(config
                .getServletContext());
        authService = context.getBean(AuthenticationService.class);
        appContext = context;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws java.io.IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		userDataStorage = appContext.getBean(UserDataStorage.class);
		
		Map<String, String> headers = Headers.getHeadersInfo(req);
//		for (String key : headers.keySet()) {
//			System.out.println(key + "=" + headers.get(key));
//		}

		String[] credentials = resolveCredentials(headers);

		boolean isCredentialsResolved = credentials != null && credentials.length == 2;
		if (!isCredentialsResolved) {
			res.sendError(401);
			return;
		}

		String username = credentials[0];
		String password = credentials[1];
		if (authService.validateUserPassword(username, password)) {
			userDataStorage.setLoggedIn(true);
			userDataStorage.setLocale(headers.get("locale"));
			chain.doFilter(request, response);
		} else {
			res.sendError(401);
		}
	}

	
	/**
	 * Получение имени и пароля из header
	 * @param headers
	 * @return
	 */
	private String[] resolveCredentials(Map<String, String> headers) {
		try {
			String base64Credentials = headers.get("Authorization");
			if (base64Credentials == null) {
				return null;
			}
			base64Credentials = base64Credentials.substring("Basic".length()).trim();
			String credentials = new String(Base64.getDecoder().decode(base64Credentials), Charset.forName("UTF-8"));
			return credentials.split(":", 2);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void destroy() {
	}

}
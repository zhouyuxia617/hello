package cn.et.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpSession;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;

@WebFilter("/*")
public class TestFilter implements Filter {

    public TestFilter() {}
    
	public void destroy() {}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		String url = req.getRequestURI().toString();
		
		//是登录页面和登录的逻辑让它过
		if(url.endsWith("login.jsp") || url.endsWith("/t")) {
			chain.doFilter(request, response);
			return ;
		}else { 
			//不是登录页面拦截，判断session中有没有这个值，有就让过
			HttpSession session = req.getSession();
			if(session.getAttribute("userName") != null) {
				chain.doFilter(request, response);
				return ;
			}
		}
		
		//又不是进入登录页又不是用户，直接跳回登录页面
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
	}

}

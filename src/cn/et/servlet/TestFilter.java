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
		
		//�ǵ�¼ҳ��͵�¼���߼�������
		if(url.endsWith("login.jsp") || url.endsWith("/t")) {
			chain.doFilter(request, response);
			return ;
		}else { 
			//���ǵ�¼ҳ�����أ��ж�session����û�����ֵ���о��ù�
			HttpSession session = req.getSession();
			if(session.getAttribute("userName") != null) {
				chain.doFilter(request, response);
				return ;
			}
		}
		
		//�ֲ��ǽ����¼ҳ�ֲ����û���ֱ�����ص�¼ҳ��
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
	}

}

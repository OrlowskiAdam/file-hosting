package pl.coderslab.filehosting.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@WebFilter(filterName = "AuthFilter", urlPatterns = {"*"})
public class AuthFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        String[] allowedUriArr = {"/login", "/register","/"};
        List<String> allowedUri = Arrays.asList(allowedUriArr);
        if (session.getAttribute("user") == null &&  !allowedUri.contains(request.getRequestURI()) && !request.getRequestURI().startsWith("/css") && !request.getRequestURI().startsWith("/js")) {
            HttpServletResponse response = (HttpServletResponse) resp;
            response.sendRedirect("/login");
            return;
        }

        chain.doFilter(req, resp);

    }

    public void init(FilterConfig config) throws ServletException {

    }

}

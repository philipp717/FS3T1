package com.example.fs3t1.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        String uri = request.getRequestURI();

        if (uri.startsWith("/admin")) {
            if (session == null || !"ADMIN".equals(session.getAttribute("usuarioRol"))) {
                response.sendRedirect("/login?acceso=denegado");
                return false;
            }
        } else if (uri.equals("/mi-cuenta")) {
            if (session == null || session.getAttribute("usuarioId") == null) {
                response.sendRedirect("/login");
                return false;
            }
        }
        return true;
    }
}

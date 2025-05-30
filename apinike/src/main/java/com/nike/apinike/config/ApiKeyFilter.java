package com.nike.apinike.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ApiKeyFilter implements Filter {

    @Value("${CHAVE_API}")
    private String CHAVE_API;
    @Value("${X_API_KEY}")
    private String X_API_KEY;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String path = httpRequest.getRequestURI();
        if (path.startsWith("/catalogo/listar")) {
            String apiKey = httpRequest.getHeader("X_API_KEY");

            if (!CHAVE_API.equals(apiKey)) {
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                httpResponse.getWriter().write("API KEY INVALIDA");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}

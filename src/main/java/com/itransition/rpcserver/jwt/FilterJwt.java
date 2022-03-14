package com.itransition.rpcserver.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.util.StringUtils.hasText;

@Component
public class FilterJwt extends GenericFilterBean {

    private String api = "/api/";

    @Autowired
    JwtVerify jwtVerify;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String url = httpServletRequest.getRequestURI();
        String token = getTokenFromRequest((HttpServletRequest) request);
        if(url.contains(api)) {
            if (token == null) {
                httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            } else if (!jwtVerify.verifier(token)) {
                httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            }
        }
        chain.doFilter(request, response);
    }


    private String getTokenFromRequest(HttpServletRequest request) {
        String bearer = request.getHeader("Authorization");
        if (hasText(bearer) && bearer.startsWith("Bearer")) {
            return bearer.substring(7);
        }
        return null;
    }
}

package kr.co.fastcampus.eatgo.filters;

import io.jsonwebtoken.Claims;
import kr.co.fastcampus.eatgo.utils.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    private JwtUtil jwtUtil;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response
            , FilterChain chain) throws IOException, ServletException {
        Authentication authentication = getAuthentication(request);

        if(authentication != null) {
            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(authentication);
        }
        
        //무조건 실행 함
        chain.doFilter(request, response);

    }

    //사용자가 사용 하는 Authentication이 아니라 spring 내부에서 사용하는 것
    private Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null) {
            return null;
        }
        //todo JwtUtil에서 clims 얻기

        Claims claims = jwtUtil.getClaims(token.substring("Bearer ".length()));

        Authentication authentication = new UsernamePasswordAuthenticationToken(claims, null);

        return authentication;
    }
}

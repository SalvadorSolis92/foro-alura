package alura.api.foro.security;

import alura.api.foro.domain.security.TokenService;
import alura.api.foro.repository.AutorRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecutiryFilter extends OncePerRequestFilter {

    @Value("${api.security.secret}")
    private String apiSecret;

    @Autowired
    private AutorRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Verifica si la solicitud tiene un token
        String token = request.getHeader("Authorization");

        if (token != null ) {
            // Si no tiene token o el token no es válido, permite que la solicitud pase al siguiente filtro
            System.out.println("hacer autenticacion con token");
            //return;
        }

        // Continúa con el filtro
        filterChain.doFilter(request, response);
    }

}
package com.swe.project.config.filter;

import com.swe.project.constant.Constants;
import com.swe.project.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

/**
 * JWT Authentication Filter
 * Intercepts requests and validates JWT tokens from Authorization header
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
//    private final BlacklistTokenRepository blacklistTokenRepository;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        // Check if Authorization header exists and starts with "Bearer "
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            // Extract token from header
            final String jwt = authHeader.substring(7);
            final String userEmail = jwtUtil.extractUsername(jwt);
            final Long userId = jwtUtil.extractUserId(jwt);
            final String role = jwtUtil.extractRole(jwt);

//            if (isUserLoggedOut(jwt)) {
//                log.warn("Attempt to use a logged-out token for user: {}", userEmail);
//                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                response.setContentType("application/json;charset=UTF-8");
//                response.getWriter().write("{\"error\":\"" + Constants.USER_LOGGED_OUT + "\"}");
//                return;
//            }

            // If token is valid and user is not already authenticated
            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                // Validate token
                if (!jwtUtil.isTokenExpired(jwt)) {

                    // Create authentication object
                    SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);

                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userId, // Principal is userId for easy access
                            jwt,    // Credentials is the access token
                            List.of(authority)
                    );

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    // Set authentication in security context
                    SecurityContextHolder.getContext().setAuthentication(authToken);

                    log.debug("JWT authentication successful for user: {} (ID: {})", userEmail, userId);
                } else {
                    // Token expired - send 401 Unauthorized
                    log.error("JWT token expired for user: {}", userEmail);
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.setContentType("application/json;charset=UTF-8");
                    response.getWriter().write("{\"error\":\"" + Constants.JWT_TOKEN_EXPIRED + "\"}");
                    return;
                }
            }
        } catch (Exception e) {
            // Invalid token - send 401 Unauthorized
            log.error("JWT authentication failed: {} | Exception: {}", e.getMessage(), e.getClass().getSimpleName(), e);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"error\":\"" + Constants.JWT_TOKEN_INVALID + "\",\"details\":\"" + e.getMessage() + "\"}");
            return;
        }

        filterChain.doFilter(request, response);
    }

//    boolean isUserLoggedOut(String token) {
//        return blacklistTokenRepository.existsByToken(token);
//    }
}

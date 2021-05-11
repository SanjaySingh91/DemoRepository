package com.demo.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.demo.config.UserDetailsServiceImpl;

@Component
public class JwtRequestFilter {

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    /*
     * @Override protected void doFilterInternal(HttpServletRequest request, HttpServletResponse
     * response, FilterChain chain) throws ServletException, IOException {
     * 
     * final String requestTokenHeader = request.getHeader("Authorization");
     * 
     * String username = null; String jwtToken = null; // JWT Token is in the form "Bearer token".
     * Remove Bearer word and get // only the Token if (requestTokenHeader != null &&
     * requestTokenHeader.startsWith("Bearer ")) { jwtToken = requestTokenHeader.substring(7);
     * 
     * System.out.println("jwtToken::" + jwtToken); try { username =
     * jwtTokenUtil.getUsernameFromToken(jwtToken); System.out.println("username::" + username); }
     * catch (IllegalArgumentException e) { System.out.println("Unable to get JWT Token"); } catch
     * (ExpiredJwtException e) { System.out.println("JWT Token has expired"); } } else {
     * logger.warn("JWT Token does not begin with Bearer String"); }
     * 
     * // Once we get the token validate it. if (username != null &&
     * SecurityContextHolder.getContext() .getAuthentication() == null) {
     * 
     * UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(username);
     * System.out.println("userDetails::::::" + userDetails); // if token is valid configure Spring
     * Security to manually set // authentication if (jwtTokenUtil.validateToken(jwtToken,
     * userDetails)) {
     * 
     * UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new
     * UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
     * usernamePasswordAuthenticationToken.setDetails(new
     * WebAuthenticationDetailsSource().buildDetails(request)); // After setting the Authentication
     * in the context, we specify // that the current user is authenticated. So it passes the //
     * Spring Security Configurations successfully. SecurityContextHolder.getContext()
     * .setAuthentication(usernamePasswordAuthenticationToken); } } chain.doFilter(request,
     * response); }
     */
}

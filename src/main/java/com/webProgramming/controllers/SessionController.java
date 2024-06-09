package com.webProgramming.controllers;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// @WebFilter("/test")
// public class SessionController {
//     public void init(FilterConfig filterConfig) throws ServletException {
//         ServletContext context = filterConfig.getServletContext();
//         context.log("Initializing SessionController filter");
//     }
//     public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
//         HttpServletRequest req = (HttpServletRequest) request;
//         HttpServletResponse resp = (HttpServletResponse) response;
//         HttpSession session = req.getSession(false);

//         System.out.println("Filtering request: " + req.getRequestURI());
//         chain.doFilter(request, response);

//         if (req.getRequestURI().startsWith("/index") || req.getRequestURI().startsWith("/login") || (session != null && session.getAttribute("user") != null)) {
//             chain.doFilter(request, response);
//         } else {
//             resp.sendRedirect("index.html");
//         }
//     }
//     public void destroy() {
//     }
// }

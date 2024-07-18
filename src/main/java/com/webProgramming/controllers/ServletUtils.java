package com.webProgramming.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Map;

public class ServletUtils {
    public static void forwardToPage(HttpServletRequest request, HttpServletResponse response, String path, Map<String, Object> attributes) {
        try {
            if (attributes != null) {
                attributes.forEach(request::setAttribute);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher(path);
            dispatcher.forward(request, response);
        } catch (ServletException | IOException exception) {
            exception.printStackTrace();
        }
    }

    public static void forwardToPage(HttpServletRequest request, HttpServletResponse response, String path) {
        forwardToPage(request, response, path, null);
    }
}

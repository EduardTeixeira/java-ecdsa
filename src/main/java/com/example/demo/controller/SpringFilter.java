package com.example.demo.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

//@WebFilter("/*")
@Component
public class SpringFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init()");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        //HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        //String requestBody= IOUtils.toString(request.getInputStream(), StandardCharsets.UTF_8);

        //String body = getBody((HttpServletRequest) request);

        /*
        ContentCachingRequestWrapper httpRequest = new ContentCachingRequestWrapper(currentRequest);

        String requestBody = httpRequest.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        Map<String, Object> inputBody = readValueAsMap(requestBody);

         */

        //httpRequest.getInputStream();

        chain.doFilter(request, response);
    }

    public static String getBody(HttpServletRequest request) throws IOException {

        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }

        body = stringBuilder.toString();
        return body;
    }

    @Override
    public void destroy() {
        System.out.println("destroy()");
        Filter.super.destroy();
    }

    private ObjectMapper objm = new ObjectMapper();

    private Map<String, Object> readValueAsMap(String value) {
        try {
            return objm.readValue(value, new TypeReference<Map<String, Object>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

package com.sunbase.CustomerApp.filter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
/**
 * RequestInterceptor class to intercept and log HTTP requests.
 */

@Configuration
public class RequestInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);
    /**
     * Method executed before the actual handler is executed.
     *
     * @param request  HttpServletRequest object representing the request.
     * @param response HttpServletResponse object representing the response.
     * @param handler  The handler (controller method) that will be executed.
     * @return true if the execution chain should proceed, false if it should be halted.
     * @throws IOException in case of I/O errors.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        logger.info("In preHandle interceptor: {}", request.getRequestURI());
        return true;
    }
    /**
     * Method executed after the handler is executed, before the view is rendered.
     *
     * @param request        HttpServletRequest object representing the request.
     * @param response       HttpServletResponse object representing the response.
     * @param handler        The handler (controller method) that was executed.
     * @param modelAndView  ModelAndView object representing the model and view.
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        logger.info("In postHandle interceptor: {}", response.getStatus());
    }
    /**
     * Method executed after the request is completed, after rendering the view.
     *
     * @param request        HttpServletRequest object representing the request.
     * @param response       HttpServletResponse object representing the response.
     * @param handler        The handler (controller method) that was executed.
     * @param ex             Exception thrown during execution, or null if none was thrown.
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        logger.info("After completion of request");
    }
}


package com.adidas.subscription.interceptors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*this class is used to measure service time*/
@Component
public class LoggerInterceptor implements HandlerInterceptor {

    private final static Logger LOGGER = LogManager.getLogger(LoggerInterceptor.class);

    /* this method is executed before processing*/
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler)
            throws Exception
    {
        long startTime = System.currentTimeMillis();

        request.setAttribute("startTime", startTime);

        return true;
    }

    /*This method is executed after processing*/
    public void postHandle( HttpServletRequest request,
                            HttpServletResponse response,
                            Object handler,
                            ModelAndView modelAndView)
            throws Exception
    {
        long startTime = (Long) request.getAttribute("startTime");
        //request.removeAttribute("startTime");

        long endTime = System.currentTimeMillis();
        //modelAndView.addObject("totalTime", endTime - startTime);

        LOGGER.info("Request Prcessing Time :: " + (endTime - startTime));
        System.out.println("Request Prcessing Time :: " + (endTime - startTime));
        //System.out.println(endTime +" "+ startTime);
    }
}


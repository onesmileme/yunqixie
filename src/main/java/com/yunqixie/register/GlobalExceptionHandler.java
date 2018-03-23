package com.yunqixie.register;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    public  static  final  String DEFAULT_ERROR_VIEW = "error";


    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView defaultErrorHandler(HttpServletRequest request,Exception exception) throws  Exception {

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception",exception);
        mav.addObject("url",request.getRequestURL());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView defaultRumtimeErrorHandler(HttpServletRequest request,Exception exception) throws  Exception {

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception",exception);
        mav.addObject("url",request.getRequestURL());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }
}

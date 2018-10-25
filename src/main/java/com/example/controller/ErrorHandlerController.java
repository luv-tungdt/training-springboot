package com.example.controller;

import com.example.utils.Constant;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

@ControllerAdvice
public class ErrorHandlerController {

	/**
	 * Catch  Parse Exception
	 *
	 * @param model model
	 *
	 * @return error.html
	 */
	@ExceptionHandler(ParseException.class)
	protected  ModelAndView parseException(HttpServletRequest request, ParseException p) {
		Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Request: " + request.getRequestURL() + " raised " + p);
		return new ModelAndView("error");
	}

	/**
	 * Catch IO Exception
	 *
	 *@param model model
	 *
	 * @return error.html
	 */
	@ExceptionHandler(IOException.class)
	protected ModelAndView IOException(HttpServletRequest request, IOException i) {
		//model.addAttribute(Constant.MESSAGE_ERROR, Constant.ERROR_IO);
		Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Request: " + request.getRequestURL() + " raised " + i);
		return new ModelAndView("error");
	}

	/**
	 * Catch  Runtime Exception
	 *
	 * @return list.html
	 */
	@ExceptionHandler(RuntimeException.class)
	protected ModelAndView catchExceptionRuntime(HttpServletRequest request, RuntimeException i) {
		Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Request: " + request.getRequestURL() + " raised " + i);
		return new ModelAndView("error");
	}
}

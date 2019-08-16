package com.example.demo.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.exception.AbstractException;
import com.example.demo.exception.ExceptionUtil;
import com.example.demo.model.vo.ResponseVO;

/**
  * 全局异常处理器（处理Controller 层抛出的异常）
 * @author sandy0823
 *
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
	
	@ExceptionHandler
	public ResponseVO<String> handlerException(Throwable ex,HttpServletRequest request,HttpServletResponse response) {
		ResponseVO<String> responseVO = new ResponseVO<String>();
		if(ex instanceof AbstractException) {
			response.setStatus(((AbstractException) ex).getHttpStatus().value());
			responseVO.setMsg(((AbstractException) ex).getMessage());
		}else {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			responseVO.setMsg(ExceptionUtil.findMsg(ex));
		}
		responseVO.setCode(response.getStatus()+"");
		return responseVO;
	}
}

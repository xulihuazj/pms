package com.xulihuazj.pms.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.xulihuazj.pms.entity.error.BizErrorCode;
import com.xulihuazj.pms.entity.exception.BusinessException;
import com.xulihuazj.pms.response.APIResponse;
import com.xulihuazj.pms.utils.log.LogHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 统一的异常处理类
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<Object> errorHandler(HttpServletRequest request, Exception e) {
        APIResponse apiResponse = new APIResponse();
        // http 响应码
        HttpStatus httpStatus;
        // 如果是 BusinessException
        if (e instanceof BusinessException) {
            LogHelper.error(logger, "BusinessException异常,errorcode={0}, message={1}", ((BusinessException) e).getErrorCode(), e.getMessage());
            apiResponse.setStatusCode(((BusinessException) e).getExErrorCode());
            apiResponse.setMessage(e.getMessage());
            // 如果HttpStatus 不为空
            if (null != ((BusinessException) e).getHttpStatus()) {
                try {
                    httpStatus = HttpStatus.valueOf(((BusinessException) e).getHttpStatus());
                } catch (Exception ex) {
                    httpStatus = HttpStatus.EXPECTATION_FAILED;
                }
            } else {
                //如果业务异常并且没有定义http状态吗 这里默认是200 尽量不要使用500
                // 否则前端拿到这个状态码会认为是不允许跨域
                httpStatus = HttpStatus.OK;
            }
        } else if (e instanceof MethodArgumentNotValidException) {
            List<FieldError> fieldErrors = ((MethodArgumentNotValidException) e).getBindingResult().getFieldErrors();
            for (FieldError error : fieldErrors) {
                apiResponse.setStatusCode(BizErrorCode.REQUEST_PARAM_EMPTY_ERROR.getCode());
                apiResponse.setMessage(error.getDefaultMessage());
                logger.info(error.getField() + ":" + error.getDefaultMessage());
            }
            return new ResponseEntity<>(apiResponse, HttpStatus.EXPECTATION_FAILED);
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            LogHelper.exception(e, logger, "请求方式不支持，异常信息为：{0}", e);
            apiResponse.setMessage("此API的请求方式不正确，请转换正确的请求方式！");
            return new ResponseEntity<>(apiResponse, HttpStatus.EXPECTATION_FAILED);
        } else {
            LogHelper.exception(e, logger, "发生运行时异常");
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            apiResponse.setMessage("服务器处理错误");
            apiResponse.setStatusCode(httpStatus.toString());
        }
        ObjectMapper objectMapper = new ObjectMapper();
        // 转成蛇形
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return new ResponseEntity<>(objectMapper.convertValue(apiResponse, Object.class), httpStatus);
    }


}

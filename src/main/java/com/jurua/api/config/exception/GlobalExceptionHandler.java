package com.jurua.api.config.exception;

import com.jurua.api.common.constants.StatusCode;
import com.jurua.api.common.model.result.ResultApi;
import com.jurua.api.config.exception.service.TestServiceException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 张博【zhangb@lianliantech.cn】
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private final static Log logger = LogFactory.getLog(GlobalExceptionHandler.class);

    @ExceptionHandler(TestServiceException.class)
    @ResponseBody
    public ResultApi<Object> testServiceExceptionHandler(Exception e) {
        //return getResultApi(StatusCode.TEST, e);
        return null;
    }

    private ResultApi<Object> getResultApi(StatusCode code, Exception e) {
        ResultApi<Object> result = new ResultApi<>();
        result.setStatusCode(code.getCode());
        result.setMessage(code.getMessage() + (e.getMessage() == null ? "" : ":" + e.getMessage()));
        //if (logger.isDebugEnabled() && e.getCause().getMessage() != null) {
        //    String causeMessage = e.getCause().getMessage();
        //    String splitStr = "###";
        //    int startIndex = causeMessage.indexOf(splitStr);
        //    int endIndex = causeMessage.indexOf(splitStr, startIndex + splitStr.length());
        //    if (endIndex > 0 && endIndex > startIndex && endIndex < causeMessage.length()) {
        //        causeMessage = causeMessage.substring(startIndex < 0 ? 0 : startIndex, endIndex);
        //        result.setMessage(result.getMessage() + "[JURUA_API_DEBUG CAUSE:" + causeMessage + "]");
        //    }
        //}
        return result;
    }
}

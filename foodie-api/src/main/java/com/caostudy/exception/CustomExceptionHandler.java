package com.caostudy.exception;

import com.caostudy.utils.CaoJSONResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 * @author Cao Study
 * @description CustomExceptionHandler
 * @date 2021/7/12 23:49
 */
@RestControllerAdvice
public class CustomExceptionHandler {

    // 上传文件超过500k，捕获异常：MaxUploadSizeExceededException
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public CaoJSONResult handlerMaxUploadFile(MaxUploadSizeExceededException ex) {
        return CaoJSONResult.errorMsg("文件上传大小不能超过500k，请压缩图片或者降低图片质量再上传！");
    }
}

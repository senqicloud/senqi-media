package com.senqicloud.senqimediaserver.handler;

import com.senqicloud.senqimediaserver.response.Result;
import com.senqicloud.senqimediaserver.response.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@Slf4j
@ControllerAdvice(annotations = RestController.class)
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {

    // 是否支持 Advice（这里所有 RestController 都支持）
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    // 对响应体做包装处理
    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  org.springframework.http.server.ServerHttpRequest request,
                                  org.springframework.http.server.ServerHttpResponse response) {

        // 已是 Result 类型，无需再次包装（如异常返回、手动返回）
        if (body instanceof Result<?> || body instanceof ResponseEntity) {
            return body;
        }

        // 正常包装为成功返回
        return ResultUtils.success("请求成功！", body);
    }
}

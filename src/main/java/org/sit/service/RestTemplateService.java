package org.sit.service;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.extern.slf4j.Slf4j;
import org.sit.enums.ErrorCode;
import org.sit.exceptions.BusinessException;
import org.sit.vo.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

/**
 * @author WangZhen
 * @Date 2023/12/14 21:23
 */
@Service
@Slf4j
public class RestTemplateService {
    @Autowired
    private RestTemplate restTemplate;

    public <T> T startRequest(String url, Class<T> reponseClass, HttpMethod httpMethod) throws BusinessException {
        T result = null;
        try {
            ParameterizedTypeReference<ResponseData<T>> parameterizedTypeReference = new ParameterizedTypeReference<>() {};
            ResponseEntity<ResponseData<T>> exchange = restTemplate.exchange(url, httpMethod, null, parameterizedTypeReference);
            if(exchange.getStatusCode() != HttpStatus.OK){
                throw new BusinessException(ErrorCode.ERROR.getCode(), "请求地址：" + url + "返回状态码：" + exchange.getStatusCode().value());
            }

            ResponseData<T> body = exchange.getBody();
            if(body.getCode() != ErrorCode.SUCCESS.getCode()){
                throw new BusinessException(ErrorCode.ERROR.getCode(), "请求地址：" + url + "返回结果200但错误" + body.getMessage());
            }
            Object object = body.getData();
            String jsonString = JSONObject.toJSONString(object);
            result = JSONObject.parseObject(jsonString, reponseClass);
            return result;
        }finally {
            log.info("请求地址：{}, 方法类型：{}, 返回结果：{}", url, httpMethod.name(), result);
        }
    }
}

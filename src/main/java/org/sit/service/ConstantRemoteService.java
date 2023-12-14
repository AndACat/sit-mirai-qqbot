package org.sit.service;

import lombok.extern.slf4j.Slf4j;
import org.sit.dto.ConstantRespDto;
import org.sit.enums.ErrorCode;
import org.sit.exceptions.BusinessException;
import org.sit.vo.ConstantRespVo;
import org.sit.vo.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author WangZhen
 * @Date 2023/12/13 22:41
 */
@Service
@Slf4j
public class ConstantRemoteService {
    @Value("${url.aliyunIp}")
    private String aliyunUrl;
    @Autowired
    private RestTemplateService restTemplateService;
    @Autowired
    private RestTemplate restTemplate;
    private final static String requestPath = "/public/constantList/";

    @Cacheable(cacheNames = "getConstantListByType", key = "#type", cacheManager = "caffeineCacheManager")
    public ConstantRespVo getConstantListByType(String type){
        log.info("远程请求常量表入参：{}", type);
        final String url = aliyunUrl + requestPath + "/" + type;
        try {
            ConstantRespVo constantRespVo = restTemplateService.startRequest(url, ConstantRespVo.class, HttpMethod.GET);
            log.info("远程请求常量表返回结果：{}", constantRespVo);
            return constantRespVo;
        }catch (Exception e){
            log.error("远程请求常量表服务错误", e);
            throw e;
        }
    }
    @Cacheable(cacheNames = "getConstantStringListByType", key = "#type", cacheManager = "caffeineCacheManager")
    public List<String> getConstantStringListByType(String type){
        ConstantRespVo constantListByType = this.getConstantListByType(type);
        return constantListByType.getConstantRespDtoList().stream().map(ConstantRespDto::getValue).collect(Collectors.toList());
    }
}

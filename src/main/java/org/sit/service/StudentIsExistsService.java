package org.sit.service;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.sit.enums.ErrorCode;
import org.sit.exceptions.BusinessException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * 用于判断一个学生是否是上应大学生
 * @author WangZhen
 */
@Service
public class StudentIsExistsService {
    private final static String BASE_URL = "https://service-0c20xjtb-1256537839.sh.apigw.tencentcs.com/release/searchForAddGroup?";

    /**
     * 判断一个学生是否是上应大学生
     * @param sno 学号
     * @param sname 姓名
     * @return 是
     * @throws BusinessException 异常，不是
     */
    public boolean isExists(String sno, String sname) throws BusinessException {
        final String requestUrl = BASE_URL + "sno=" +sno + "&sname=" + URLEncoder.encode(sname, StandardCharsets.UTF_8);
        GetMethod getMethod = new GetMethod(requestUrl);
        try {
            new HttpClient().executeMethod(getMethod);
            String responseBodyAsString = getMethod.getResponseBodyAsString();
            if(responseBodyAsString != null && responseBodyAsString.contains("存在")){
                return true;
            }else{
                throw BusinessException.error("不存在您的信息，请输入正确的学号-姓名");
            }
        } catch (IOException e) {
            throw BusinessException.error(ErrorCode.NETWORK_ERROR);
        }
    }
}

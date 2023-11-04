package org.sit.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.sit.enums.ErrorCode;
import org.sit.exceptions.BusinessException;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * �����ж�һ��ѧ���Ƿ�����Ӧ��ѧ��
 * @author WangZhen
 */
public class StudentIsExists {
    private final static String BASE_URL = "https://service-0c20xjtb-1256537839.sh.apigw.tencentcs.com/release/searchForAddGroup?";

    /**
     * �ж�һ��ѧ���Ƿ�����Ӧ��ѧ��
     * @param sno ѧ��
     * @param sname ����
     * @return ��
     * @throws BusinessException �쳣������
     */
    public static boolean isExists(String sno, String sname) throws BusinessException {
        final String requestUrl = BASE_URL + "sno=" +sno + "&sname=" + URLEncoder.encode(sname, StandardCharsets.UTF_8);
        GetMethod getMethod = new GetMethod(requestUrl);
        try {
            new HttpClient().executeMethod(getMethod);
            String responseBodyAsString = getMethod.getResponseBodyAsString();
            if(responseBodyAsString != null && responseBodyAsString.contains("����")){
                return true;
            }else{
                throw BusinessException.error("������������Ϣ����������ȷ��ѧ��-����");
            }
        } catch (IOException e) {
            throw BusinessException.error(ErrorCode.NETWORK_ERROR);
        }
    }
}

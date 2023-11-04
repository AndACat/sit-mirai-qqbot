package org.sit;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Test {
    private final static String BASE_URL = "https://service-0c20xjtb-1256537839.sh.apigw.tencentcs.com/release/searchForAddGroup?";
    public static void main(String[] args) throws IOException {
        final String requestUrl = BASE_URL + "sno=231042Y420&sname=" + URLEncoder.encode("张馨鸣", StandardCharsets.UTF_8);
        GetMethod getMethod = new GetMethod(requestUrl);
        try {
            new HttpClient().executeMethod(getMethod);
            String responseBodyAsString = getMethod.getResponseBodyAsString();
            System.out.println(responseBodyAsString);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

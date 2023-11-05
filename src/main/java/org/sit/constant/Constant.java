package org.sit.constant;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author WangZhen
 * @Date 2023/11/5 15:56
 */
@Component
@ConfigurationProperties(prefix = "constant")
@Configuration
@Setter
@Getter
public class Constant {
    private String qqNum;
    private String password;
    private List<String> filter;
}

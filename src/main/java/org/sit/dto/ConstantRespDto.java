package org.sit.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author WangZhen
 * @Date 2023/12/13 22:19
 */
@Setter
@Getter
@ToString
public class ConstantRespDto {
    /**
     * 分类
     */
    private String type;

    /**
     * 子分类
     */
    private String subtype;

    /**
     * 分类值
     */
    private String value;
}

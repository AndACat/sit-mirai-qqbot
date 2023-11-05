package org.sit.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 学生入群申请VO
 * @author WangZhen
 * @Date 2023/11/5 12:15
 */
@Setter
@Getter
@ToString
public class StudentJoinGroupVO {
    /**
     * 学号
     */
    private String sno;
    /**
     * 姓名
     */
    private String name;
}

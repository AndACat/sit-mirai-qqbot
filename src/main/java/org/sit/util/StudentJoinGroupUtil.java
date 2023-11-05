package org.sit.util;
import org.apache.commons.lang3.StringUtils;
import org.sit.enums.ErrorCode;
import org.sit.exceptions.BusinessException;
import org.sit.vo.StudentJoinGroupVO;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author WangZhen
 * @Date 2023/11/5 12:10
 */
public class StudentJoinGroupUtil {
    private final static Pattern SNAME_PATTERN = Pattern.compile("[\\u4e00-\\u9fa5]+");
    private final static Pattern SNO_PATTERN = Pattern.compile("[A-Za-z0-9]+");

    public static StudentJoinGroupVO getStudentJoinGroupVO(String input) throws BusinessException {
        StudentJoinGroupVO studentJoinGroupVO = new StudentJoinGroupVO();
        // 定义正则表达式模式，匹配中文字符
        // 创建 Matcher 对象
        Matcher matcher_sname = SNAME_PATTERN.matcher(input);
        // 查找匹配的中文字符
        while (matcher_sname.find()) {
            String sname = matcher_sname.group(); // 匹配的中文姓名
            if(StringUtils.isEmpty(studentJoinGroupVO.getName())){
                studentJoinGroupVO.setName(sname);
            }
            input = input.replace(sname, "");
        }
        Matcher matcher_sno = SNO_PATTERN.matcher(input);
        if(matcher_sno.find()){
            String sno = matcher_sno.group();
            studentJoinGroupVO.setSno(sno);
        }
        if(StringUtils.isEmpty(studentJoinGroupVO.getSno()) || StringUtils.isEmpty(studentJoinGroupVO.getName())){
            throw new BusinessException(ErrorCode.FAIL.getCode(), "未从入群信息中找到学生信息");
        }
        return studentJoinGroupVO;
    }
    public static void main(String[] args) throws BusinessException {
        String input = "21556848Y102 张强· 123 李四"; // 包含中文字符的字符串
        StudentJoinGroupVO studentJoinGroupVO = getStudentJoinGroupVO(input);
        System.out.println(studentJoinGroupVO.toString());
    }
}

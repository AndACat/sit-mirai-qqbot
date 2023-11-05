package org.sit.handler;

import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.contact.NormalMember;
import net.mamoe.mirai.event.events.MemberJoinRequestEvent;
import org.sit.abstractclass.AbstractEventHandler;
import org.sit.constant.Constant;
import org.sit.exceptions.BusinessException;
import org.sit.util.StudentIsExists;
import org.sit.util.StudentJoinGroupUtil;
import org.sit.vo.StudentJoinGroupVO;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

/**
 * 群聊加群申请处理事件
 * @author WangZhen
 * @date 2023/11/4 8:15
 */
@Component
@Slf4j
public class MemberJoinRequestEventHandler extends AbstractEventHandler<MemberJoinRequestEvent> {

    @Resource
    private Constant constant;

    @Override
    public void handler(MemberJoinRequestEvent event) {
        log.info("入群请求：{}", event);
        // 1.指定人邀请入群，可直接入群
        NormalMember invitor = event.getInvitor();
        if(invitor != null){
            int level = invitor.getPermission().getLevel();
            if(level == 1 || level == 2){
                // 邀请人为群主或管理员，则自动同意入群
                event.accept();
            }
        }

        // 2.输入学号姓名，验证信息后可入群
        String message = event.getMessage();
        if(message != null){
            try {
                for (String s : constant.getFilter()) {
                    message = message.replace(s, "");
                }
                message = message.replace("\n", "");
                log.info("去除题目之后的message:{}", message);
                StudentJoinGroupVO studentJoinGroupVO = StudentJoinGroupUtil.getStudentJoinGroupVO(message);
                if(StudentIsExists.isExists(studentJoinGroupVO.getSno(), studentJoinGroupVO.getName())){
                    log.info("同意入群申请，原始信息：{}， 转换信息为：{}", message, studentJoinGroupVO);
                    event.accept();
                }
            } catch (BusinessException e) {
                log.info("原始信息：{}， 错误信息：{}", message, e.getMessage());
            }
        }
    }
}

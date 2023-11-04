package org.sit.function;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.contact.NormalMember;
import net.mamoe.mirai.event.events.MemberJoinRequestEvent;
import org.sit.abstractclass.AbstractEventHandler;
import org.sit.exceptions.BusinessException;
import org.sit.util.StudentIsExists;

/**
 * 群聊加群申请处理事件
 * @author WangZhen
 * @date 2023/11/4 8:15
 */
public class MemberJoinRequestEventHandler extends AbstractEventHandler<MemberJoinRequestEvent> {

    @Override
    public void handler(MemberJoinRequestEvent event) {
        // 1.指定人邀请入群，可直接入群
        NormalMember invitor = event.getInvitor();
        if(invitor != null){
            long id = invitor.getId();
            if(1677688026 == id){
                event.accept();
                return;
            }
        }

        // 2.输入学号-姓名，验证信息后可入群
        String message = event.getMessage();
        if(message != null){
            String[] split = message.split("-");
            if(split.length != 2){
                event.reject(false, "入群申请输入格式错误。学号-姓名");
            }else{
                String sno = split[0];
                String sname = split[1];
                try {
                    if(StudentIsExists.isExists(sno, sname)){
                        event.accept();
                    }
                } catch (BusinessException e) {
                    event.reject(false, e.getMessage());
                }
            }
        }else {
            event.reject(false, "请输入学号-姓名，并重新申请加群。");
        }
    }
}

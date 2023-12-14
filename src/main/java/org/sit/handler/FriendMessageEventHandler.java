package org.sit.handler;

import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.message.data.*;
import org.sit.abstractclass.AbstractEventHandler;
import org.sit.exceptions.BusinessException;
import org.sit.service.StudentIsExistsService;
import org.sit.util.StudentJoinGroupUtil;
import org.sit.vo.StudentJoinGroupVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WangZhen
 * @Date 2023/11/4 8:19
 */
@Component
@Slf4j
public class FriendMessageEventHandler extends AbstractEventHandler<FriendMessageEvent> {
    @Autowired
    private StudentIsExistsService studentIsExistsService;

    private List<Long> list = new ArrayList<>(){{
        add(1677688026L);
        add(821191986L);
        add(3205928477L);
    }};
    @Override
    public void handler(FriendMessageEvent event) {
        long id = event.getFriend().getId();
        if(list.contains(id)){
            for (SingleMessage singleMessage : event.getMessage()) {
                if(singleMessage instanceof PlainText plainText){
                    String content = plainText.getContent();
                    log.info("收到QQ：{} 发来的消息：{}", id, content);
                    try {
                        StudentJoinGroupVO studentJoinGroupVO = StudentJoinGroupUtil.getStudentJoinGroupVO(content);
                        boolean exists = studentIsExistsService.isExists(studentJoinGroupVO.getSno(), studentJoinGroupVO.getName());
                        event.getSubject().sendMessage(new MessageChainBuilder()
                                .append(new QuoteReply(event.getMessage()))
                                .append(exists ? "存在" + content : "不存在" + content)
                                .build()
                        );
                    } catch (BusinessException e) {
                        event.getSubject().sendMessage(new MessageChainBuilder()
                                .append(new QuoteReply(event.getMessage()))
                                .append("不存在" + content)
                                .build()
                        );
                    }
                }
            }
        }
    }
}

package org.sit.handler;

import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import net.mamoe.mirai.message.data.QuoteReply;
import org.sit.abstractclass.AbstractEventHandler;
import org.springframework.stereotype.Component;

/**
 * @author WangZhen
 * @Date 2023/11/4 8:19
 */
@Component
public class FriendMessageEventHandler extends AbstractEventHandler<FriendMessageEvent> {
    @Override
    public void handler(FriendMessageEvent event) {
        event.getSubject().sendMessage(new MessageChainBuilder()
                .append(new QuoteReply(event.getMessage()))
                .append("Hi, ")
                .build()
        );
    }
}

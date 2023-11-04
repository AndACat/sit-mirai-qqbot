package org.sit.function;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import net.mamoe.mirai.message.data.QuoteReply;
import org.sit.abstractclass.AbstractEventHandler;

/**
 * @author WangZhen
 * @Date 2023/11/4 8:19
 */
public class FriendMessageEventHandler extends AbstractEventHandler<FriendMessageEvent> {
    @Override
    public void handler(FriendMessageEvent event) {
        event.getSubject().sendMessage(new MessageChainBuilder()
                .append(new QuoteReply(event.getMessage()))
                .append("Hi, 我是SIT QQ Bot, 智能一键同意入群申请~")
                .build()
        );
    }
}

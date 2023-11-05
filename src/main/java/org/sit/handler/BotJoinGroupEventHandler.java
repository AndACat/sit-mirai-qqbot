package org.sit.handler;

import net.mamoe.mirai.event.events.BotJoinGroupEvent;
import org.sit.abstractclass.AbstractEventHandler;
import org.sit.annotations.IgnoreHandler;
import org.springframework.stereotype.Component;

/**
 * @author WangZhen
 * @Date 2023/11/4 8:27
 */
@Component
@IgnoreHandler
public class BotJoinGroupEventHandler extends AbstractEventHandler<BotJoinGroupEvent> {
    @Override
    public void handler(BotJoinGroupEvent botJoinGroupEvent) {
        
    }
}

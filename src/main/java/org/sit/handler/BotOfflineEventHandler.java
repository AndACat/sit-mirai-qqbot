package org.sit.handler;

import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.event.events.BotOfflineEvent;
import org.sit.abstractclass.AbstractEventHandler;
import org.springframework.stereotype.Component;

/**
 * 机器人下线通知
 * @author WangZhen
 * @Date 2023/11/5 13:12
 */
@Component
@Slf4j
public class BotOfflineEventHandler extends AbstractEventHandler<BotOfflineEvent> {

    @Override
    public void handler(BotOfflineEvent botOfflineEvent) {
        log.error("qqbot机器人检测到已下线， 请重新登录！");
    }
}

package org.sit.abstractclass;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.event.Event;

/**
 * @author WangZhen
 * @Date 2023/11/4 8:27
 */
public abstract class AbstractEventHandler<E extends Event> {
    abstract public void handler(E e);

    public void joinEvent(Bot bot, Class<E> e){
        bot.getEventChannel().subscribeAlways(e, (event) ->{
            if(event != null){
                handler(event);
            }
        });
    }
}

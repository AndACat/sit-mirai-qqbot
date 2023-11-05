package org.sit.handler.login;

import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.auth.BotAuthorization;
import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.events.BotJoinGroupEvent;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.event.events.MemberJoinRequestEvent;
import net.mamoe.mirai.utils.BotConfiguration;
import org.sit.abstractclass.AbstractEventHandler;
import org.sit.annotations.IgnoreHandler;
import org.sit.handler.BotJoinGroupEventHandler;
import org.sit.handler.FriendMessageEventHandler;
import org.sit.handler.MemberJoinRequestEventHandler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * qq bot登录的主程序
 * @author WangZhen
 * @date 2023/11/4 8:15
 */
@Component
@Slf4j
public class QQBotLoginHandler implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    /**
     * QQ号
     */
    @Value("${constant.qqNum}")
    private Long qqNum = 3377576149L;
    /**
     * 密码
     */
    @Value("${constant.password}")
    private String password;

    public void login() {
        // 使用自定义配置
        Bot bot = BotFactory.INSTANCE.newBot(qqNum, BotAuthorization.byQRCode(), new BotConfiguration() {{
            setProtocol(MiraiProtocol.ANDROID_WATCH); // 切换协议
        }});
        bot.login();


        afterLogin(bot);
    }

    /**
     * 自动加载处理类
     * @param bot
     */
    public void afterLogin(Bot bot) {
        Map<String, AbstractEventHandler> beansOfType = applicationContext.getBeansOfType(AbstractEventHandler.class);
        for (Map.Entry<String, AbstractEventHandler> stringEventEntry : beansOfType.entrySet()) {
            AbstractEventHandler eventHandler = stringEventEntry.getValue();
            IgnoreHandler annotation = eventHandler.getClass().getAnnotation(IgnoreHandler.class);
            if(annotation == null){

                Type genericSuperclass = eventHandler.getClass().getGenericSuperclass();
                if(genericSuperclass instanceof ParameterizedType){
                    ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
                    Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                    if(actualTypeArguments != null && actualTypeArguments.length == 1){
                        Type clazz = actualTypeArguments[0];
                        if(clazz instanceof Class<?>){
                            Class clazz1 = (Class) clazz;
                            log.info("加载Handler: {}", clazz1);
                            eventHandler.joinEvent(bot, clazz1);
                        }
                    }
                }
            }else{
                log.info("忽略Handler: {}", eventHandler.getClass().getName());
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static void main(String[] args) {
        new QQBotLoginHandler().login();
    }
}
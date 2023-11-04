package org.sit;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.auth.BotAuthorization;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import net.mamoe.mirai.message.data.QuoteReply;
import net.mamoe.mirai.utils.BotConfiguration;
import org.sit.function.MemberJoinRequestEventHandler;

public class JavaMain {
    public static void main(String[] args) {
        Long qqNum = 3377576149L;
        String password = "50+50=100";

//        Long qqNum = 3477688026L;
//        String password = "15+15=30";
        // 使用自定义配置
        Bot bot = BotFactory.INSTANCE.newBot(qqNum, BotAuthorization.byQRCode(), new BotConfiguration() {{
//            fileBasedDeviceInfo(); // 使用 device.json 存储设备信息
            setProtocol(MiraiProtocol.ANDROID_WATCH); // 切换协议
        }});
        bot.login();


        JavaMain.afterLogin(bot);
    }

    public static void afterLogin(Bot bot) {
        long yourQQNumber = 1677688026L;
        bot.getEventChannel().subscribeAlways(FriendMessageEvent.class, (event) -> {
            if (event.getSender().getId() == yourQQNumber) {
                event.getSubject().sendMessage(new MessageChainBuilder()
                        .append(new QuoteReply(event.getMessage()))
                        .append("Hi, you just said: '")
                        .append(event.getMessage())
                        .append("'")
                        .build()
                );
            }
        });

        MemberJoinRequestEventHandler.handler(bot);
    }
}
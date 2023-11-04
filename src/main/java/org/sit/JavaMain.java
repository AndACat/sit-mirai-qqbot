package org.sit;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.auth.BotAuthorization;
import net.mamoe.mirai.event.events.BotJoinGroupEvent;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.event.events.MemberJoinRequestEvent;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import net.mamoe.mirai.message.data.QuoteReply;
import net.mamoe.mirai.utils.BotConfiguration;
import org.sit.function.BotJoinGroupEventHandler;
import org.sit.function.FriendMessageEventHandler;
import org.sit.function.MemberJoinRequestEventHandler;
/**
 * qq bot登录的主程序
 * @author WangZhen
 * @date 2023/11/4 8:15
 */
public class JavaMain {
    /**
     * QQ号
     */
    private static final Long qqNum = 3377576149L;
    /**
     * 密码
     */
    private static final String password = "50+50=100";

    public static void main(String[] args) {
        // 使用自定义配置
        Bot bot = BotFactory.INSTANCE.newBot(qqNum, BotAuthorization.byQRCode(), new BotConfiguration() {{
            setProtocol(MiraiProtocol.ANDROID_WATCH); // 切换协议
        }});
        bot.login();


        JavaMain.afterLogin(bot);
    }

    public static void afterLogin(Bot bot) {
        // 自动回复私聊消息功能
        new FriendMessageEventHandler().joinEvent(bot, FriendMessageEvent.class);

        // 自动处理入群申请功能
        new MemberJoinRequestEventHandler().joinEvent(bot, MemberJoinRequestEvent.class);

        new BotJoinGroupEventHandler().joinEvent(bot, BotJoinGroupEvent.class);
    }
}
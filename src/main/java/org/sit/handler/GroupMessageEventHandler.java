package org.sit.handler;

import jdk.jfr.Unsigned;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import net.mamoe.mirai.message.data.PlainText;
import net.mamoe.mirai.message.data.QuoteReply;
import net.mamoe.mirai.message.data.SingleMessage;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.sit.abstractclass.AbstractEventHandler;
import org.sit.exceptions.BusinessException;
import org.sit.service.ConstantRemoteService;
import org.sit.service.RestTemplateService;
import org.sit.service.StudentIsExistsService;
import org.sit.util.StudentJoinGroupUtil;
import org.sit.vo.ConstantRespVo;
import org.sit.vo.StudentJoinGroupVO;
import org.sit.vo.StuinfoRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WangZhen
 * @Date 2023/11/4 8:19
 */
@Component
@Slf4j
public class GroupMessageEventHandler extends AbstractEventHandler<GroupMessageEvent> {

    @Value("${url.aliyunIp}")
    private String aliyunIp;
    @Autowired
    private StudentIsExistsService studentIsExistsService;
    @Autowired
    private ConstantRemoteService constantRemoteService;
    @Autowired
    private RestTemplateService restTemplateService;

    @Override
    public void handler(GroupMessageEvent event) {
        // 群聊id
        long id = event.getSubject().getId();
        List<String> responseUserQqGroupList = constantRemoteService.getConstantStringListByType("response_user_qq_group_list");
        if(CollectionUtils.isNotEmpty(responseUserQqGroupList) && responseUserQqGroupList.contains(String.valueOf(id))){
            // 在允许回复的qq群列表里
            for (SingleMessage singleMessage : event.getMessage()) {
                if(singleMessage instanceof PlainText plainText){
                    String content = plainText.getContent();
                    log.info("收到QQ：{} 发来的消息：{}", id, content);
                    try {
                        if(StringUtils.isNotEmpty(content) && content.startsWith("查")){
                            String keyword = content.substring(1);
                            if(StringUtils.isNotEmpty(keyword)){
                                final String url = aliyunIp + "/stuinfo/getStuinfoOneByLike?keyword=" + keyword;
                                StuinfoRespVo stuinfoRespVo = restTemplateService.startRequest(url, StuinfoRespVo.class, HttpMethod.GET);
                                String response = "姓名：" + stuinfoRespVo.getStuname() + "\n"
                                        + "学号：" + stuinfoRespVo.getSno() + "\n" +
                                        "学院：" + stuinfoRespVo.getDeptname() + "\n" +
                                        "阶段：" + stuinfoRespVo.getType();
//                            StudentJoinGroupVO studentJoinGroupVO = StudentJoinGroupUtil.getStudentJoinGroupVO(content);
//                            boolean exists = studentIsExistsService.isExists(studentJoinGroupVO.getSno(), studentJoinGroupVO.getName());
                                event.getSubject().sendMessage(new MessageChainBuilder()
                                        .append(new QuoteReply(event.getMessage()))
                                        .append(response)
                                        .build()
                                );
                            }
                        }
                    } catch (BusinessException e) {
                        log.error("恢复群聊查询私人消息时，错误", e);
                        event.getSubject().sendMessage(new MessageChainBuilder()
                                .append(new QuoteReply(event.getMessage()))
                                .append("系统错误，查询失败。")
                                .build()
                        );
                    }
                }
            }
        }
    }
}

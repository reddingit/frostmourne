package com.autohome.frostmourne.spi.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import javax.annotation.Resource;

import com.autohome.frostmourne.spi.dao.impl.FeiShuSender;
import com.autohome.frostmourne.spi.dao.impl.WeChatSender;
import com.autohome.frostmourne.spi.starter.model.AccountInfo;
import com.autohome.frostmourne.spi.starter.model.AlarmMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles({"autohome"})
@IfProfileValue(name = "test-profile", value = "IntegrationTest")
class MessageServiceIntegrationTest {

    @Resource
    private MessageService messageService;

    @Resource
    private WeChatSender weChatSender;

    @Resource
    private FeiShuSender feiShuSender;

    @Test
    void sendByDingRobot() {
        AlarmMessage alarmMessage = new AlarmMessage();
        alarmMessage.setTitle("[霜之哀伤监控系统]test");
        alarmMessage.setContent("测试机器人");
        alarmMessage.setWays(Arrays.asList("dingding"));

        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setMobile("150xxxx0501");
        alarmMessage.setRecipients(Arrays.asList(accountInfo));
        String hook = "hook";
        messageService.sendByDingRobot(hook, alarmMessage, Arrays.asList("150xxxx0501"));
    }

    @Test
    public void sendWechatRobot() {
        boolean result = weChatSender.send(new ArrayList<>(), "[霜之哀伤]测试微信机器人", "测试微信机器人消息",
                "http://weixin.com");
    }

    @Test
    public void sendFeiShuRobot() {
        boolean result = feiShuSender.send(new ArrayList<>(), "[霜之哀伤]测试飞书机器人", "测试飞书机器人消息",
                "https://open.feishu.cn/open-apis/bot/v2/hook/token");
    }
}

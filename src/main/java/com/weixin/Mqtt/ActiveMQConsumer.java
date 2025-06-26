package com.weixin.Mqtt;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.weixin.Devices.mapper.DevicesMapper;
import com.weixin.Websocket.WebSocket;
import com.weixin.WxLogin.mapper.WeixinMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQBytesMessage;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.activemq.command.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import javax.jms.JMSException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class ActiveMQConsumer {

    /**
     * topic 模式的消费者
     */

    @Autowired
    private WebSocket webSocket;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    private DevicesMapper devicesMapper;

    @Autowired
    private WeixinMapper weixinMapper;

    @Autowired
    private JmsTemplate jmsTemplate;

    private boolean flag = false;
    @JmsListener(destination = "info_control", containerFactory = "topicListener")
    public void readActivTopic1(Message message, String msg) {
        log.info("activeMQ topic 消费者接收到text数据：{}", msg);
        if (message instanceof ActiveMQBytesMessage) {
            ActiveMQBytesMessage activeMQBytesMessage = (ActiveMQBytesMessage) message;
            byte[] bytes = new byte[0];
            try {
                bytes = new byte[(int) activeMQBytesMessage.getBodyLength()];
                int i = activeMQBytesMessage.readBytes(bytes);
                String s = new String(bytes, StandardCharsets.UTF_8);
                log.info("activeMQ topic 消费者device001接收到原设备数据：{}", s);
                Gson gson = new Gson();
                // 将 JSON 字符串解析为一个 Map
                Type type = new TypeToken<Map<String, Object>>() {
                }.getType();
                Map<String, Object> data = gson.fromJson(s, type);
                // 通过键获取对应的值
                String secretKey = (String) data.get("secretKey");
                String smoke = (String) data.get("smoke");
                String user = weixinMapper.selectOpenId(secretKey);

                if (smoke.equals("异常")&&!flag) {
                    List<String> topicList = devicesMapper.selectByAuthor(user, "wind");
                    System.out.println(topicList);
                    for (String topic :topicList){
                        Destination destination = new ActiveMQTopic(topic);
                        jmsTemplate.convertAndSend(destination, "on");
                    }
                    flag = true;
                } else if (smoke.equals("正常")&&flag) {
                    List<String> topicList = devicesMapper.selectByAuthor(user, "wind");
                    System.out.println(topicList);
                    for (String topic :topicList){
                        Destination destination = new ActiveMQTopic(topic);
                        jmsTemplate.convertAndSend(destination, "off");
                    }
                    flag = false;
                }
                webSocket.sendOneMessage(user, s);
            } catch (JMSException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * topic 模式的消费者
     */
    @JmsListener(destination = "feedback", containerFactory = "topicListener")
    public void readActivTopic3(Message message, String msg) {
        log.info("activeMQ topic 消费者接收到text数据：{}", msg);
        if (message instanceof ActiveMQBytesMessage) {
            ActiveMQBytesMessage activeMQBytesMessage = (ActiveMQBytesMessage) message;
            byte[] bytes = new byte[0];
            try {
                bytes = new byte[(int) activeMQBytesMessage.getBodyLength()];
                int i = activeMQBytesMessage.readBytes(bytes);
                String s = new String(bytes, StandardCharsets.UTF_8);
                log.info("activeMQ topic 消费者device001接收到原设备数据：{}", s);
            } catch (JMSException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
//    @JmsListener(destination = "feedback", containerFactory = "topicListener")
//    public void readActivTopic3(Message message, String msg) {
//        log.info("activeMQ topic 消费者接收到text数据：{}", msg);
//
//        webSocket.sendOneMessage("123", msg);
//        if (message instanceof ActiveMQBytesMessage) {
//            ActiveMQBytesMessage activeMQBytesMessage = (ActiveMQBytesMessage) message;
//            byte[] bytes = new byte[0];
//            try {
//                bytes = new byte[(int) activeMQBytesMessage.getBodyLength()];
//                int i = activeMQBytesMessage.readBytes(bytes);
//                String s = new String(bytes, StandardCharsets.UTF_8);
//                log.info("activeMQ topic 消费者device001接收到原设备数据：{}", s);
//            } catch (JMSException e) {
//                throw new RuntimeException(e);
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
}

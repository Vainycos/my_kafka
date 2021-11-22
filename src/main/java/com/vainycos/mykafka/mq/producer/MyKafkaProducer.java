package com.vainycos.mykafka.mq.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Resource;

/**
 * @author: Vainycos
 * @description kafka生产者
 * @date: 2021/11/22 11:14
 */
@Component
public class MyKafkaProducer {

    private Logger logger = LoggerFactory.getLogger(MyKafkaProducer.class);

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * 定义MQ主题：Hello-Kafka
     */
    public static final String TOPIC_INVOICE = "Hello-Kafka";

    /**
     * 发送消息
     * @param msg
     * @return
     */
    public ListenableFuture<SendResult<String, Object>> sendMsg(String msg) {
        logger.info("发送MQ消息 topic：{} message：{}", TOPIC_INVOICE, msg);
        return kafkaTemplate.send(TOPIC_INVOICE, msg);
    }
}

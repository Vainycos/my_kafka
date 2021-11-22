package com.vainycos.mykafka.mq.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Optional;

/**
 * @author: Vainycos
 * @description kafka消费者
 * @date: 2021/11/22 11:17
 */
@Component
public class KafkaConsumer {

    private Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "Hello-Kafka", groupId = "Hello-Kafka")
    public void onMessage(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        Optional<?> message = Optional.ofNullable(record.value());
        // 1. 判断消息是否存在
        if (!message.isPresent()) {
            return;
        }
        // 2. 处理 MQ 消息
        try {
            Assert.isTrue(true, "消费成功!");
            // 3. 打印日志
            logger.info("消费MQ消息，完成 topic：{} 消费信息：{}", topic, message.get());

            // 4. 消息消费完成
            ack.acknowledge();
        } catch (Exception e) {
            // 消息重试,需要保证幂等。
            logger.error("消费MQ消息，失败 topic：{} message：{}", topic, message.get());
            throw e;
        }
    }
}

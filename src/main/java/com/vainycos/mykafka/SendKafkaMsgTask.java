package com.vainycos.mykafka;

import com.vainycos.mykafka.mq.producer.MyKafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author: Vainycos
 * @description 定时发送mq消息
 * @date: 2021/11/22 11:22
 */
@Component
public class SendKafkaMsgTask {

    private Logger logger = LoggerFactory.getLogger(SendKafkaMsgTask.class);

    @Resource
    private MyKafkaProducer kafkaProducer;

    /**
     * 每5s执行一次消息发送
     */
    @Scheduled(cron = "0/5 * * * * ?")
    void sendMsg(){
        // 发送当前时间
        ListenableFuture<SendResult<String, Object>> future = kafkaProducer.sendMsg(LocalDateTime.now().toString());
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {

            @Override
            public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                // MQ 消息发送成功
                logger.info("发送MQ消息成功 topic：{}", MyKafkaProducer.TOPIC_INVOICE);
            }

            @Override
            public void onFailure(Throwable throwable) {
                // MQ 消息发送失败
                logger.error("发送MQ消息失败 topic：{}", MyKafkaProducer.TOPIC_INVOICE);
            }

        });
    }
}

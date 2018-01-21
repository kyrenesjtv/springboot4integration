//package me.kyrene.springboot4integration.rabbitMQ.config;
//
//import org.springframework.amqp.core.*;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * Created by wanglin on 2018/1/17.
// */
//@Configuration
//public class RabitConfig {
//
//    @Bean
//    public Queue helloQueue() {
//        return new Queue("hello");//对应路由
//    }
//
//    @Bean
//    public Queue helloQueue2() {
//        return new Queue("hello2");
//    }
//
//    @Bean
//    public Queue userQueue() {
//        return new Queue("user");
//    }
//
//    /*******************************************************************/
//    @Bean
//    public Queue queueMessage() {
//        return new Queue("topic.message");
//    }
//
//    @Bean
//    public Queue queueMessages() {
//        return new Queue("topic.messages");
//    }
//    /**
//     * 定义一个prefix，只有send输入exchange才能匹配到下面两个
//     */
//    @Bean
//    TopicExchange exchange() {
//        return new TopicExchange("exchange");
//    }
//
//    /**
//     * 将队列topic.message与exchange绑定，binding_key为topic.message,就是完全匹配
//     * @param queueMessage
//     * @param exchange
//     * @return
//     */
//    @Bean
//    Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
//        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
//    }
//
//    /**
//     * 将队列topic.messages与exchange绑定，binding_key为topic.#,模糊匹配
//     * # 匹配0个或多个 topic.xxx.xxx
//     * * 匹配一个 topic.xxx
//     * @param queueMessages
//     * @param exchange
//     * @return
//     */
//    @Bean
//    Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange) {
//        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
//    }
//    /*******************************************************************/
//
//
//    @Bean
//    public Queue aMessage() {
//        return new Queue("fanout.A");
//    }
//
//    @Bean
//    public Queue bMessage() {
//        return new Queue("fanout.B");
//    }
//
//    @Bean
//    public Queue cMessage() {
//        return new Queue("fanout.C");
//    }
//
//    /**
//     * FanoutExchange 类似广播信息
//     * 会将消息转发到与其绑定的队列上
//     */
//    @Bean
//    FanoutExchange fanoutExchange() {
//        return new FanoutExchange("fanoutExchange");
//    }
//
//    /**
//     * aMessage 队列绑定
//     * @param aMessage
//     * @param fanoutExchange
//     * @return
//     */
//    @Bean
//    Binding bindingExchangeA(Queue aMessage,FanoutExchange fanoutExchange) {
//        return BindingBuilder.bind(aMessage).to(fanoutExchange);
//    }
//
//    /**
//     * aMessage 队列绑定
//     * @param bMessage
//     * @param fanoutExchange
//     * @return
//     */
//    @Bean
//    Binding bindingExchangeB(Queue bMessage, FanoutExchange fanoutExchange) {
//        return BindingBuilder.bind(bMessage).to(fanoutExchange);
//    }
//
//    /**
//     * aMessage 队列绑定
//     * @param cMessage
//     * @param fanoutExchange
//     * @return
//     */
//    @Bean
//    Binding bindingExchangeC(Queue cMessage, FanoutExchange fanoutExchange) {
//        return BindingBuilder.bind(cMessage).to(fanoutExchange);
//    }
//}

package hu.webuni.orderservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.ConnectionFactory;

@Configuration
public class OrderJmsConfig {

    @Bean
    public BrokerService configBroker() throws Exception{
        BrokerService brokerService = new BrokerService();
        brokerService.addConnector("tcp://localhost:9998");
        brokerService.setPersistent(false);
        return brokerService;
    }

    @Bean
    public ConnectionFactory shipmentConnector(){
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:9998");
        return connectionFactory;
    }


    @Bean
    public MessageConverter configConverter(ObjectMapper objectMapper) {
        MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
        messageConverter.setObjectMapper(objectMapper);
        messageConverter.setTargetType(MessageType.TEXT);
        messageConverter.setTypeIdPropertyName("_type");

        return messageConverter;
    }

    @Bean
    public JmsListenerContainerFactory<?> shipmentFactory(ConnectionFactory shipmentConnector, DefaultJmsListenerContainerFactoryConfigurer defaultJmsListenerContainerFactoryConfigurer) {
        DefaultJmsListenerContainerFactory defaultJmsListenerContainerFactory = new DefaultJmsListenerContainerFactory();
        defaultJmsListenerContainerFactory.setClientId("shipment-service");
        defaultJmsListenerContainerFactoryConfigurer.configure(defaultJmsListenerContainerFactory,shipmentConnector);
        return defaultJmsListenerContainerFactory;
    }

}

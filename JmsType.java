
public void jmsMessageType(JmsConsumer consumer){
Message receive = consumer.receive();
byte[] payload;
    if (message instanceof BytesMessage) {
        payload = message.getBody(byte[].class);
    }
    else if (message instanceof TextMessage) {
        String s = message.getBody(String.class);
        payload = s.getBytes(UTF_8);
    }
    else {
        log.error("Unsupported JMS message type {}", message.getClass());
        throw new ConnectException("Unsupported JMS message type");
    }
    
}

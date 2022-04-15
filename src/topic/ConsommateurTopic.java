package topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

public class ConsommateurTopic {

public static void main(String[] args)  throws Exception {

ConnectionFactory factory = new ActiveMQConnectionFactory
("admin", "admin", "tcp://localhost:61616");


// Creer une connexion et la démarrer

Connection con= factory.createConnection();
con.start();

//Creer une session

Session session= con.createSession(false, Session.AUTO_ACKNOWLEDGE);

// Creer une destination

Topic topic= session.createTopic("myTopic");

MessageConsumer consumer =session.createConsumer(topic);

while(true) {
TextMessage msg= (TextMessage) consumer.receive();

System.out.println("Message recu+ "+msg.getText());

}}}

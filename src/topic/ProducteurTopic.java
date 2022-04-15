package topic;

import java.net.URI;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;

public class ProducteurTopic {

public static void main(String[] args) throws Exception {
	
	BrokerService broker = BrokerFactory.createBroker(new URI(
            "broker:(tcp://localhost:61616)"));
    broker.start();

// creation d'une usine de connexions au broker

ConnectionFactory factory = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");

// Creer une connexion et la démarrer

Connection con = factory.createConnection();
con.start();

// Creer une session

Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);

// Creer une destination

Topic topic = session.createTopic("myTopic");

MessageProducer producer = session.createProducer(topic);

Message message = session.createTextMessage("Il y a un match de foot ce soir à 19 h entre Y et ZE");

producer.send(message);

con.close();

}
}


package queue;

import java.net.URI;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;

public class Producteur {

public static void main(String[] args) throws  Exception {
	/*BrokerService broker = BrokerFactory.createBroker(new URI("broker:(tcp://localhost:61616)"));
    broker.start();*/

//creation d'une usine de connexions au broker
ConnectionFactory factory = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");


// Creer une connexion et la démarrer
Connection con= factory.createConnection();
con.start();

//Creer une session

Session session= con.createSession(false, Session.AUTO_ACKNOWLEDGE);

// Creer une destination

Queue queue= session.createQueue("tp1-jms");

MessageProducer producer =session.createProducer(queue);

//round robin


for (int i=1; i<11; i++) {

Message message = session.createTextMessage("message "+i);

producer.send(message);

}
con.close();
}
}


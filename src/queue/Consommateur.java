package queue;

import java.net.URI;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;

public class Consommateur {

	public static void main(String[] args) throws Exception {
		//creation d'une usine de connexions au broker
		ConnectionFactory factory = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");
		
		// Creer une connexion et la démarrer
		Connection con= factory.createConnection();
		con.start();
		
		//Creer une session
		Session session= con.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		// Creer une destination
		Queue queue= session.createQueue("tp1-jms");
		
		MessageConsumer producer =session.createConsumer(queue);
		
		//Attendre la livraison des massages par le broker
		while(true) {
			TextMessage  messageRecu= (TextMessage) producer.receive();
			System.out.println("Message recu par conso 1 :  "+ messageRecu.getText());
		}
	}
}


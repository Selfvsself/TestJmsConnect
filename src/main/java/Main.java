import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.apache.activemq.artemis.jms.client.ActiveMQJMSConnectionFactory;

import javax.jms.Connection;
import javax.jms.JMSException;

public class Main {

    public static void main(String[] args) {
        String jmsUrl = "(tcp://artemis.ru:8000)";
        String username = "user";
        String password = "pass";
        int count = 1;

        if (args.length >= 1) {
            jmsUrl = args[0];
        }
        if (args.length >= 2) {
            username = args[1];
        }
        if (args.length >= 3) {
            password = args[2];
        }
        if (args.length >= 4) {
            try {
                count = Integer.parseInt(args[3]);
            } catch (NumberFormatException e) {
                System.out.printf("Error parse '%s' to number\n", args[1]);
                count = 1;
            }
        }

        System.out.printf("URL:%s\n", jmsUrl);
        System.out.printf("Username:%s\n", username);
        System.out.printf("Password:%s\n", password);
        System.out.printf("ConnectionNumber:%s\n", count);

        ActiveMQConnectionFactory factory = new ActiveMQJMSConnectionFactory(jmsUrl, username, password);
        System.out.println("ActiveMQ Connection Factory is created");
        for (int i = 0; i < count; i++) {
            System.out.printf("\nStarting connection number %s\n", i);
            try (Connection connection = factory.createConnection()) {
                connection.start();
                System.out.printf("Connection number %s is started\n", i);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}

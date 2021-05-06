package it.aldolushkja.rabbitmq;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;

public class Recv implements Runnable, Consumer {

  private final static String QUEUE_NAME = "hello-queue";
  private final static Logger LOG = Logger.getLogger(Recv.class.getName());


  Channel channel;

  @Override
  public void run() {
    try {
      ConnectionFactory factory = new ConnectionFactory();
      factory.setHost("localhost");
      factory.setPort(35600);
      Connection connection = factory.newConnection();
      channel = connection.createChannel();

      channel.queueDeclare(QUEUE_NAME, false, false, false, null);
      channel.basicConsume(QUEUE_NAME, false, this);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (TimeoutException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
  }

  @Override
  public void handleConsumeOk(String consumerTag) {
    // TODO Auto-generated method stub
    LOG.info("Recv.handleConsumeOk() ---- " + consumerTag);
  }

  @Override
  public void handleCancelOk(String consumerTag) {
    // TODO Auto-generated method stub

  }

  @Override
  public void handleCancel(String consumerTag) throws IOException {
    // TODO Auto-generated method stub

  }

  @Override
  public void handleDelivery(String arg0, Envelope arg1, BasicProperties arg2, byte[] arg3)
      throws IOException {

    LOG.info("Recv.handleDelivery() ---- " + arg0);
    String message = new String(arg3, StandardCharsets.UTF_8);
    LOG.info(" [x] Received '" + message + "'");
    channel.basicAck(arg1.getDeliveryTag(), false);
  }

  @Override
  public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {
    // TODO Auto-generated method stub

  }

  @Override
  public void handleRecoverOk(String consumerTag) {
    // TODO Auto-generated method stub

  }

}

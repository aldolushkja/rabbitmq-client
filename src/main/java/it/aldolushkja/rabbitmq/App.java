package it.aldolushkja.rabbitmq;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
  public static void main(String[] args) throws InterruptedException {
    for (int i = 0; i < 10; i++) {
      Send.sendMessage();
    }
    final ExecutorService pool = Executors.newFixedThreadPool(2);
    pool.execute(new Recv());
    Thread.sleep(2000);
    System.exit(0);
  }
}

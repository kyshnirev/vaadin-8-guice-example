package gk.example.vaadin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Launcher {

  private static final Logger log = LoggerFactory.getLogger(Launcher.class);

  public static void main(String[] args) {

    try (WebServer s = new WebServer()) {
      s.start();
      s.join();
    } catch (Exception e) {
      log.error("failed to start web server", e);
      System.exit(102);
      return;
    }
  }

}

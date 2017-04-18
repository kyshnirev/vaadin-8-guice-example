package gk.example.vaadin;

import com.google.inject.Guice;
import com.google.inject.Injector;
import gk.example.vaadin.di.Module;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Launcher {

  private static final Logger log = LoggerFactory.getLogger(Launcher.class);

  public static void main(String[] args) {

    final Injector injector;
    try {
      injector = Guice.createInjector(new Module());
    } catch (RuntimeException e) {
      log.error("failed to create injector, exit", e);
      System.exit(100);
      return;
    }

    final WebServer ws;
    try {
      ws = injector.getInstance(WebServer.class);
    } catch (RuntimeException e) {
      log.error("failed to create web server, exit", e);
      System.exit(101);
      return;
    }

    try (WebServer s = ws.start()) {
      s.join();
    } catch (Exception e) {
      log.error("failed to start web server", e);
      System.exit(102);
      return;
    }
  }

}

package gk.example.vaadin;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import java.net.InetSocketAddress;
import java.net.URL;
import java.util.Arrays;

import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.base.Preconditions.checkState;

@Singleton
public class WebServer implements AutoCloseable {

  private static final Logger log = LoggerFactory.getLogger(WebServer.class);

  private final String host;
  private final int port;
  private final boolean productionMode; // false - debug mode

  private Server server; // jetty embedded web server

  @Inject
  public WebServer(
      @Named("config.host")
      String host,
      @Named("config.port")
      int port
  ) {
    this.host = host;
    this.port = port;
    this.productionMode = false;
  }

  public WebServer start() throws Exception {

    URL classes = WebServer.class.getProtectionDomain().getCodeSource().getLocation();

    WebAppContext ctx = new WebAppContext();
    ctx.setContextPath("/");
    ctx.setParentLoaderPriority(true);
    ctx.getMetaData().setWebInfClassesDirs(Arrays.asList(Resource.newResource(classes)));
    ctx.setInitParameter("productionMode", String.valueOf(productionMode));

    ctx.setBaseResource(Resource.newResource(getClass().getResource("/"))); // "/VAADIN/*"

    ctx.setConfigurations(new Configuration[] {
        new AnnotationConfiguration()
    });

    server = new Server(new InetSocketAddress(host, port));
    server.setHandler(ctx);

    server.start();

    return this;
  }

  public void join() throws InterruptedException {
    checkState(server != null, "server is not started");
    server.join();
  }

  @Override
  public void close() throws Exception {
    if (server != null) {
      server.stop();
      server = null;
    }
  }

}

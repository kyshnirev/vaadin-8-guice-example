package gk.example.vaadin;

import javax.servlet.annotation.WebServlet;

import com.vaadin.guice.annotation.Configuration;
import com.vaadin.guice.server.GuiceVaadinServlet;
import gk.example.vaadin.di.Module;

@WebServlet(
    name = "WebUIServlet",
    asyncSupported = true,
    urlPatterns = { "/*", "/VAADIN/*" }
)
@Configuration( // guice vaadin
    modules = { Module.class },
    basePackages = "gk.example.vaadin"
)
public class WebUIServlet extends GuiceVaadinServlet {

  private static final long serialVersionUID = 20170324L;

}

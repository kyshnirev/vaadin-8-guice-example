package gk.example.vaadin.ui;

import javax.inject.Inject;

import com.vaadin.annotations.Theme;
import com.vaadin.guice.annotation.GuiceUI;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import gk.example.vaadin.ui.layout.RootLayout;
import gk.example.vaadin.ui.layout.ViewContainer;

@Theme("web")
@GuiceUI(viewContainer = ViewContainer.class)
public class WebUI extends UI {

  private static final long serialVersionUID = 201702027L;

  @Inject
  RootLayout root;

  @Override
  protected void init(VaadinRequest request) {

    getPage().setTitle("Web UI title");

    setContent(root);

    getNavigator().navigateTo("");

  }

}

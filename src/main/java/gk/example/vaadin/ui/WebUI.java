package gk.example.vaadin.ui;

import javax.inject.Inject;

import com.vaadin.annotations.Theme;
import com.vaadin.guice.annotation.GuiceUI;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import gk.example.vaadin.auth.AccessControl;
import gk.example.vaadin.ui.layout.RootLayout;
import gk.example.vaadin.ui.layout.ViewContainer;
import gk.example.vaadin.ui.view.LoginView;
import gk.example.vaadin.ui.view.UsersView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Theme("web")
@GuiceUI(viewContainer = ViewContainer.class)
public class WebUI extends UI implements ViewChangeListener {

  private static final long serialVersionUID = 201702027L;

  private static final Logger log = LoggerFactory.getLogger(WebUI.class);

  @Inject
  RootLayout root;

  @Inject
  AccessControl ac;

  @Override
  protected void init(VaadinRequest request) {

    getPage().setTitle("Web UI title");

    setContent(root);

    getNavigator().addViewChangeListener(this);
    getNavigator().navigateTo(LoginView.NAME);

    ac.addLoginListener(user -> {
        getNavigator().navigateTo(UsersView.NAME);
    });
    ac.addLogoutListener(() -> {
        getNavigator().navigateTo(LoginView.NAME);
    });
  }

  // return true - if view change is allowed
  @Override
  public boolean beforeViewChange(ViewChangeEvent event) {
    log.debug("beforeViewChange: view name = {}, user = {}", event.getViewName(), ac.getUser());

    if (event.getViewName().equals(LoginView.NAME)) {
      return true;
    }

    if (ac.isUserSignedIn()) {
      return true;
    }

    getNavigator().navigateTo(LoginView.NAME);
    return false;
  }


}

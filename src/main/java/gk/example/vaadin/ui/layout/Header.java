package gk.example.vaadin.ui.layout;

import javax.inject.Inject;

import com.vaadin.guice.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import gk.example.repository.model.User;
import gk.example.vaadin.auth.AccessControl;

@UIScope
public class Header extends HorizontalLayout implements AccessControl.LoginListener, AccessControl.LogoutListener {

  private static final long serialVersionUID = 20170324L;

  private final Label title;
  private final Label hello;
  private final Button logout;

  @Inject
  Header(AccessControl ac) {
    this.title = new Label("Header");
    this.hello = new Label("");

    this.logout = new Button("logout");
    this.logout.addClickListener(evt -> {
        ac.signOut();
    });

    // init default logout state
    onLogout();

    addComponent(title);
    addComponent(hello);
    addComponent(logout);


    setWidth("100%");

    setExpandRatio(title, 1.0f);

    ac.addLoginListener(this);
    ac.addLogoutListener(this);
  }

  @Override
  public void onLogin(User user) {
    this.hello.setValue("Hello, "+ user.getLogin() +"!");
    this.logout.setVisible(true);
  }

  @Override
  public void onLogout() {
    this.hello.setValue("");
    this.logout.setVisible(false);
  }

  public void setTitle(String text) {
    this.title.setValue(text);
  }

}

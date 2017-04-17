package gk.example.vaadin.ui.view;

import javax.inject.Inject;

import com.vaadin.guice.annotation.GuiceView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import gk.example.vaadin.auth.AccessControl;
import gk.example.vaadin.ui.layout.Header;

@GuiceView(name = LoginView.NAME)
public class LoginView extends VerticalLayout implements View {

  private static final long serialVersionUID = 20170414L;

  public static final String NAME = "";

  @Inject
  AccessControl ac;

  @Inject
  Header header;

  private final TextField usernameTF;
  private final TextField passwordTF;

  LoginView() {
    usernameTF = new TextField("Username");
    passwordTF = new PasswordField("Password");

    Button loginBtn = new Button("Login");
    loginBtn.setDisableOnClick(true);
    loginBtn.addClickListener(evt -> {
        try {
          login();
        } finally {
          loginBtn.setEnabled(true); // setDisableOnClick(true)
        }
    });

    FormLayout contentFL = new FormLayout();
    contentFL.setMargin(true);
    contentFL.addComponent(usernameTF);
    contentFL.addComponent(passwordTF);
    contentFL.addComponent(loginBtn);

    Panel panel = new Panel("Login");
    panel.setSizeUndefined();
    panel.setContent(contentFL);

    addComponent(panel);
    setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
  }

  private void login() {
    if (ac.signIn(usernameTF.getValue(), passwordTF.getValue())) {
      Notification.show("Auth Ok", Notification.Type.TRAY_NOTIFICATION);
      // see: BasicAccessControl.fireLogin(..), WebUI { ac.addLoginListener(..) }
    } else {
      Notification.show("Invalid credentials", Notification.Type.ERROR_MESSAGE);
    }
  }

  @Override
  public void enter(ViewChangeEvent event) {
    header.setTitle("Login");
    passwordTF.setValue("");
  }

}

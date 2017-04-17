package gk.example.vaadin.auth;

import java.io.Serializable;

import gk.example.repository.model.User;

/**
 * Интерфейс проверки авторизации.
 * Позволяет навешать слушателей на события успешного логина или разлогина.
 */
public interface AccessControl extends Serializable {

  interface LoginListener {
    void onLogin(User user);
  } // :~

  interface LogoutListener {
    void onLogout();
  } // :~

  boolean signIn(String username, String password);

  void signOut();

  boolean isUserSignedIn();

  User getUser();

  void addLoginListener(LoginListener ll);

  void addLogoutListener(LogoutListener ll);

}

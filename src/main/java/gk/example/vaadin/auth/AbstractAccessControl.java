package gk.example.vaadin.auth;

import java.util.LinkedList;
import java.util.List;

import gk.example.repository.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Частичная реализация интерфейса проверки авторизации
 */
public abstract class AbstractAccessControl implements AccessControl {

  private static final Logger log = LogManager.getLogger(AbstractAccessControl.class);

  private static final long serialVersionUID = 20170228L;

  private final List<LoginListener> loginListeners = new LinkedList<>();
  private final List<LogoutListener> logoutListeners = new LinkedList<>();

  @Override
  public void addLoginListener(LoginListener ll) {
    if (ll != null)
      loginListeners.add(ll);
  }

  @Override
  public void addLogoutListener(LogoutListener ll) {
    if (ll != null)
      logoutListeners.add(ll);
  }

  protected void fireLogin(User user) {
    for (LoginListener ll : loginListeners) {
      try {
        ll.onLogin(user);
      } catch (RuntimeException e) {
        log.error("failed to fire onLogin", e);
      }
    }
  }

  protected void fireLogout() {
    for (LogoutListener ll : logoutListeners) {
      try {
        ll.onLogout();
      } catch (RuntimeException e) {
        log.error("failed to fire onLogout", e);
      }
    }
  }

}

package gk.example.vaadin.auth;

import javax.inject.Inject;

import com.vaadin.guice.annotation.UIScope;
import com.vaadin.server.VaadinService;
import gk.example.repository.UserRepository;
import gk.example.repository.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.google.common.base.Strings.isNullOrEmpty;

/**
 * Авторизовывает пользователя, помещает в сессию имя пользователя.
 */
@UIScope
public class BasicAccessControl extends AbstractAccessControl {

  private static final long serialVersionUID = 201702027L;

  private static final Logger log = LogManager.getLogger(BasicAccessControl.class);

  private static final String USER = "user";
  private static final String TOKEN = "token";

  @SuppressWarnings("unchecked")
  private static <T> T getSessAttr(String key) {
    return (T) VaadinService.getCurrentRequest().getWrappedSession().getAttribute(key);
  }

  private static <T> void setSessAttr(String key, T value) {
    VaadinService.getCurrentRequest().getWrappedSession().setAttribute(key, value);
  }

  private final UserRepository userRepo;

  @Inject
  public BasicAccessControl(UserRepository userRepo) {
    this.userRepo = userRepo;
  }

  @Override
  public boolean signIn(String username, String password) {
    if (isNullOrEmpty(username) || isNullOrEmpty(password)) {
      return false;
    }

    User user = userRepo.find(username);
    if (user == null) {
      log.error("failed to authorize user='{}' - not found", username);
      return false;
    }

    if (!user.verifyPassword(password)) {
      log.error("failed to authorize user='{}' - password mismatch", username);
      return false;
    }

    setSessAttr(USER, user);

    fireLogin(user);

    return true;
  }

  @Override
  public void signOut() {
    setSessAttr(USER, null);
    setSessAttr(TOKEN, null);

    fireLogout();
  }

  @Override
  public boolean isUserSignedIn() {
    User user = getSessAttr(USER);
    return user != null;
  }

  @Override
  public User getUser() {
    return getSessAttr(USER);
  }

}

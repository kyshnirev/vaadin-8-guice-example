package gk.example.repository;

import javax.inject.Singleton;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import gk.example.repository.model.User;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toMap;

@Singleton
public class UserRepository {

  // login -> User
  private final Map<String, User> users;
  {
    int id = 0;
    List<User> ulist = asList(
        new User(++ id, "admin",  "password", asList(User.Role.ADMIN)),
        new User(++ id, "ivan",   "password", asList(User.Role.REGULAR)),
        new User(++ id, "lera",   "password", asList(User.Role.REGULAR)),
        new User(++ id, "olesa",  "password", asList(User.Role.REGULAR)),
        new User(++ id, "ignite", "password", asList(User.Role.REGULAR))
      );

    this.users = ulist.stream().collect(toMap(User::getLogin, u -> u));

    if (this.users.size() != ulist.size())
      throw new IllegalStateException("duplicate login!");
  }

  /**
   * Ищет пользователя по логину
   *
   * @return null если пользователь не найден
   */
  public User find(String login) {
    return users.get(login);
  }

  public Collection<User> getAllUsers() {
    return users.values();
  }

}

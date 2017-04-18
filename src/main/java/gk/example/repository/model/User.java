package gk.example.repository.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Random;

import static com.google.common.base.Preconditions.checkArgument;

public class User {

  public enum Role {
    ADMIN,
    REGULAR,
  } // :~

  private final int id;

  private final String login;

  private final String passHash;
  private final String passSalt;

  private Collection<Role> roles;

  public User(int id, String login, String pass, Collection<Role> roles) {
    checkArgument(id > 0, "id=%s, must be more than 0", id);
    checkArgument(login != null && login.length() > 3, "login=%s, must be more than 3", login);
    checkArgument(pass != null && pass.length() > 5, "pass=%s, must be more than 5", pass);
    checkArgument(roles != null && !roles.isEmpty(), "roles=%s, must be", roles);
    this.id = id;
    this.login = login;
    this.roles = roles;

    this.passSalt = String.valueOf(rand.nextLong() & Long.MAX_VALUE);
    this.passHash = sha1(pass + passSalt);
  }

  public int getId() {
    return id;
  }

  public String getLogin() {
    return login;
  }

  public Collection<Role> getRoles() {
    return roles;
  }

  public boolean verifyPassword(String password) {
    return passHash.equals(sha1(password + passSalt));
  }

  // --- util ---

  private static final Random rand;
  static {
    long seed = System.currentTimeMillis();
    seed = Long.reverseBytes(seed) ^ Long.rotateLeft(seed, Long.SIZE / 2);
    rand = new Random(seed);
  }

  private String sha1(String str) {
    final MessageDigest md;
    try {
      md = MessageDigest.getInstance("SHA-1");
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
    md.update(str.getBytes());
    return new String(md.digest());
  }

  @Override
  public String toString() {
    return String.format("%s{id: %s, login: %s, roles: %s}", getClass().getSimpleName(),
        id, login, roles);
  }

}

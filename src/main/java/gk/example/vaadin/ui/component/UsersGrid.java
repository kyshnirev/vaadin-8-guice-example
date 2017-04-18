package gk.example.vaadin.ui.component;

import javax.inject.Inject;

import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.vaadin.data.provider.CallbackDataProvider;
import com.vaadin.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.guice.annotation.UIScope;
import com.vaadin.ui.Grid;
import gk.example.repository.UserRepository;
import gk.example.repository.model.User;

@UIScope
public class UsersGrid extends Grid<User> {

  private static final long serialVersionUID = 20170228L;

  @Inject
  UserRepository userRepo;

  private final ConfigurableFilterDataProvider<User, Void, Predicate<User>> wrappedProvider;

  public UsersGrid() {

    addColumn(User::getId)
      .setCaption("ID");

    addColumn(User::getLogin)
      .setCaption("Login");

    addColumn(u -> u.getRoles().stream().map(String::valueOf).collect(Collectors.joining(", ", "[", "]")))
      .setCaption("Roles");

    DataProvider<User, Predicate<User>> dataProvider = new CallbackDataProvider<>(
        query -> {
            return userRepo.getAllUsers().stream();
        },
        query -> {
            return userRepo.getAllUsers().size();
        },
        user -> { // item id provider
            return user.getId();
        }
    );

    this.wrappedProvider = dataProvider.withConfigurableFilter();

    setDataProvider(wrappedProvider);

  }

}

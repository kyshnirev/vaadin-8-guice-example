package gk.example.vaadin.ui.component;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import com.vaadin.data.provider.CallbackDataProvider;
import com.vaadin.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.guice.annotation.UIScope;
import com.vaadin.ui.Grid;
import gk.example.vaadin.service.User;

import static com.google.common.base.Strings.nullToEmpty;

@UIScope
public class UsersGrid extends Grid<User> {

  private static final long serialVersionUID = 20170228L;

  private static final List<User> users = Arrays.asList(
      new User("101", "ivan92", "Ivan"),
      new User("102", "lena", "Lenok"),
      new User("103", "ibragim500", "Ibg"),
      new User("104", "iblan", "Not"),
      new User("105", "olesia", "Olga")
      );

  private final ConfigurableFilterDataProvider<User, Void, Predicate<User>> wrappedProvider;

  public UsersGrid() {

    addColumn(User::getId)
      .setCaption("ID");

    addColumn(User::getLogin)
      .setCaption("Login");

    addColumn(u -> String.format("%s", nullToEmpty(u.getName())))
      .setCaption("Name");

    DataProvider<User, Predicate<User>> dataProvider = new CallbackDataProvider<>(
        query -> {
            return users.stream();
        },
        query -> {
            return users.size();
        },
        user -> { // item id provider
            return user.getId();
        }
    );

    this.wrappedProvider = dataProvider.withConfigurableFilter();

    setDataProvider(wrappedProvider);

  }

}

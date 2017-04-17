package gk.example.vaadin.ui.view;

import javax.inject.Inject;

import com.vaadin.guice.annotation.GuiceView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.VerticalLayout;
import gk.example.vaadin.ui.component.UsersGrid;
import gk.example.vaadin.ui.layout.Header;

@GuiceView(name = UsersView.NAME)
public class UsersView extends VerticalLayout implements View {

  private static final long serialVersionUID = 20170324L;

  public static final String NAME = "users";

  @Inject
  Header header;

  @Inject
  UsersView(UsersGrid grid) {
    grid.setSizeFull();
    addComponent(grid);
    setSizeFull();
  }

  @Override
  public void enter(ViewChangeEvent event) {
    header.setTitle("Users list");
  }

}

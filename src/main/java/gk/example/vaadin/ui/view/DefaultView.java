package gk.example.vaadin.ui.view;

import javax.inject.Inject;

import com.vaadin.guice.annotation.GuiceView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.VerticalLayout;
import gk.example.vaadin.ui.component.UsersGrid;

@GuiceView(name = "") // default view
public class DefaultView extends VerticalLayout implements View {

  private static final long serialVersionUID = 20170324L;

  @Inject
  DefaultView(UsersGrid grid) {
    grid.setSizeFull();
    addComponent(grid);
    setSizeFull();
  }

  @Override
  public void enter(ViewChangeEvent event) {
  }

}

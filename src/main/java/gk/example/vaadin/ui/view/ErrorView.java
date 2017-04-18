package gk.example.vaadin.ui.view;

import com.vaadin.guice.annotation.GuiceView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@GuiceView(name = "error", isErrorView = true)
public class ErrorView extends VerticalLayout implements View {

  private static final long serialVersionUID = 20170324L;

  ErrorView() {
    addComponent(new Label("error view"));
  }

  @Override
  public void enter(ViewChangeEvent event) {
  }

}

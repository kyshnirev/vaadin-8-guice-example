package gk.example.vaadin.ui.layout;

import com.vaadin.guice.annotation.UIScope;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

@UIScope
public class Header extends HorizontalLayout {

  private static final long serialVersionUID = 20170324L;

  private final Label title;;

  Header() {
    this.title = new Label("Header");

    addComponent(title);

    setWidth("100%");

    setExpandRatio(title, 1.0f);
  }

  public void setTitle(String text) {
    this.title.setValue(text);
  }

}

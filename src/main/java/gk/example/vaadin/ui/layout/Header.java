package gk.example.vaadin.ui.layout;

import com.vaadin.guice.annotation.UIScope;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

@UIScope
public class Header extends HorizontalLayout {

  private static final long serialVersionUID = 20170324L;

  Header() {
    final Label l1 = new Label("Header");

    addComponent(l1);

    setWidth("100%");

    setExpandRatio(l1, 1.0f);
  }

}

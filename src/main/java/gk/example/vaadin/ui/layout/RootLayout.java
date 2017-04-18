package gk.example.vaadin.ui.layout;

import javax.inject.Inject;

import com.vaadin.guice.annotation.UIScope;
import com.vaadin.ui.VerticalLayout;

@UIScope
public class RootLayout extends VerticalLayout {

  private static final long serialVersionUID = 20170324L;

  @Inject
  RootLayout(Header header, ViewContainer view) {
    setSizeFull();
    setMargin(true);
    setSpacing(true);

    addComponents(header, view);
    setExpandRatio(view, 1.f); // view is 100% height
  }

}

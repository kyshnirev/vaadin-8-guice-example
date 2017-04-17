package gk.example.vaadin.di;

import com.google.inject.AbstractModule;
import gk.example.vaadin.auth.AccessControl;
import gk.example.vaadin.auth.BasicAccessControl;

public class Module extends AbstractModule {

  @Override
  protected void configure() {

    bind(AccessControl.class)
        .to(BasicAccessControl.class);

  }

}

package gk.example.vaadin.di;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class Module extends AbstractModule {

  @Override
  protected void configure() {

    bind(String.class)
        .annotatedWith(Names.named("config.host"))
        .toInstance("localhost");

    bind(Integer.class)
        .annotatedWith(Names.named("config.port"))
        .toInstance(9092);

  }

}

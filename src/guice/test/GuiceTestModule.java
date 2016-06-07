package guice.test;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

import guice.impl.ClientImpl;
import guice.impl.ServiceImpl;
import guice.template.Client;
import guice.template.Service;

public class GuiceTestModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Service.class).to(ServiceImpl.class).in(Scopes.SINGLETON);
        bind(Client.class).to(ClientImpl.class).in(Scopes.SINGLETON);
    }
}

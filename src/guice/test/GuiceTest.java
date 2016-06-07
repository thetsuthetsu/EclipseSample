package guice.test;

import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

import guice.template.Client;

public class GuiceTest {
    @Test
    public void test1() {
        Module module = new GuiceTestModule();
        Injector injector = Guice.createInjector(module);
        Client client = injector.getInstance(Client.class);
        client.executeService();
    }
}

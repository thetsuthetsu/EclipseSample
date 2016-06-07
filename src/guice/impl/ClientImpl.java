package guice.impl;

import com.google.inject.Inject;

import guice.template.Client;
import guice.template.Service;

public class ClientImpl implements Client {
    private Service service_;

    @Inject
    public void setService(Service service) {
        service_ = service;
    }

    @Override
    public void executeService() {
        System.out.println("Client is executed.");
        service_.execute();
    }
}

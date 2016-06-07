package guice.impl;

import guice.template.Service;

public class ServiceImpl implements Service {

    @Override
    public void execute() {
        System.out.println("Service is executed.");
    }
}

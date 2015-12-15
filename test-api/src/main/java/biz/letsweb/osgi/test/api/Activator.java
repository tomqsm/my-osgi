package biz.letsweb.osgi.test.api;

import biz.letsweb.osgi.test.impl.ExampleServiceImpl;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

    private ServiceRegistration registration;

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("test-api started ...");
        registerService(context);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("test-api stopped ...");
    }

    private void registerService(BundleContext context) {
        System.out.println("test-api registered service ...");
        registration = context.registerService(ExampleService.class.getName(), new ExampleServiceImpl(), null);
    }
}

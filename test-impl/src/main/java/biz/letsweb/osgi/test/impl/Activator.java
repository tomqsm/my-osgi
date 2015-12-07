package biz.letsweb.osgi.test.impl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

public class Activator implements BundleActivator {

    private ServiceTracker serviceTracker;

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Activated test-impl");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Stopped test-impl");
    }

}

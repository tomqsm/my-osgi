package biz.letsweb.osgi.test.client;

import biz.letsweb.osgi.test.api.ExampleService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {

//    private static final Logger logger = LoggerFactory.getLogger(Activator.class);
    private ExampleService exampleService;

    @Override
    public void start(BundleContext context) throws Exception {
        ServiceReference reference = context
                .getServiceReference(ExampleService.class.getName());
        exampleService = (ExampleService) context.getService(reference);
        exampleService.doService();
    }

    @Override
    public void stop(BundleContext context) throws Exception {
    }

}

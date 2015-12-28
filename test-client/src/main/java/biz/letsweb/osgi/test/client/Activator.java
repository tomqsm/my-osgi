package biz.letsweb.osgi.test.client;

import biz.letsweb.osgi.test.api.ExampleService;
import java.util.LinkedList;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;
import org.osgi.service.log.LogListener;
import org.osgi.service.log.LogReaderService;
import org.osgi.util.tracker.ServiceTracker;

/**
 http://www.knopflerfish.org/osgi_service_tutorial.html#best
 see also example wit hservice tracker http://www.eclipsezone.com/eclipse/forums/t90796.html
 @author toks
 */
public class Activator implements BundleActivator {

    private final LinkedList<LogReaderService> logReaderServices = new LinkedList<>();
    private LogListener logConsoleWriter = new LogWriter();
    private ServiceTracker exampleServiceTracker;
    private ServiceReference exampleServiceRef;

    private final ServiceListener exampleServiceListener = new ServiceListener() {
        @Override
        public void serviceChanged(ServiceEvent event) {
            final ServiceReference<?> serviceReference = event.getServiceReference();
            final BundleContext bundleContext = serviceReference.getBundle().getBundleContext();
            if (event.getType() == ServiceEvent.REGISTERED) {
                System.out.println("[test-client] registered " + serviceReference.toString());
                exampleServiceRef = serviceReference;
                if (exampleServiceRef != null) {
                    try {
                        ExampleService exampleService = (ExampleService) bundleContext.getService(exampleServiceRef);
                        if (exampleService != null) {
                            System.out.println("[test-client] on 'service registered' event calls service");
                            exampleService.doService();
                        } else {
                            System.out.println("[test-client] Example service unavailable, alternative service.");
                        }
                    } catch (RuntimeException re) {
                        System.out.println("[test-client] Runtime exception! " + re);
                    } finally {
                        // Note that service should only be released when really unused.
//                        bundleContext.ungetService(exampleServiceRef);
                    }
                }
            } else if (event.getType() == ServiceEvent.UNREGISTERING) {
                System.out.println("[test-client] un-registered" + serviceReference.toString());
                bundleContext.ungetService(exampleServiceRef);
                //As soon as the service isn't needed anymore, the client should release the service by calling the BundleContext.ungetService() method.
//                bundleContext.removeServiceListener(exampleServiceListener);
            }
        }
    };

    @Override
    public void start(BundleContext context) {
        System.out.println("[test-client] starts");
        // Get a list of all the registered LogReaderService, and add the console listener
//        exampleServiceTracker = new ServiceTracker(context, ExampleService.class.getName(), null);
//        exampleServiceTracker.open();
//        exampleService = (ExampleService) exampleServiceTracker.getService();

//        ServiceTracker logReaderTracker = new ServiceTracker(context, LogReaderService.class.getName(), null);
//        logReaderTracker.open();
//        Object[] logReaders = logReaderTracker.getServices();
//        if (logReaders != null) {
//            System.out.println("#readers: " + logReaders.length);
//            for (Object logReader : logReaders) {
//                LogReaderService logReaderService = (LogReaderService) logReader;
//                logReaderService.addLogListener(logConsoleWriter);
//                logReaderServices.add(logReaderService);
//                System.out.println("#logReaderServices: " + logReaderServices.size());
//            }
//        }
//        // Add the ServiceListener, but with a filter so that we only receive events related to LogReaderService
        String logReaderServiceFilter = "(objectclass=" + LogReaderService.class.getName() + ")";
        String exampleServiceFilter = "(objectclass=" + ExampleService.class.getName() + ")";

        try {

            context.addServiceListener(exampleServiceListener, exampleServiceFilter);
            System.out.println("[test-client] added exampleServiceListener");
            /*
             if all ExampleService already had been registered, the listener above would not be called until the ExampleService were restarted. A small trick solves this: Manually construct REGISTERED ServiceEvents and call the listener:
             */
            ServiceReference[] serviceReferences = context.getServiceReferences(ExampleService.class.getName(), exampleServiceFilter);
            if (serviceReferences != null) {
                for (ServiceReference sf : serviceReferences) {
                    exampleServiceListener.serviceChanged(new ServiceEvent(ServiceEvent.REGISTERED, sf));
                }
            } else {
                System.out.println("[test-client] no exampleService available");
            }
        } catch (InvalidSyntaxException e) {
            e.printStackTrace();
        }
//        ServiceTracker logServiceTracker = new ServiceTracker(context, org.osgi.service.log.LogService.class.getName(), null);
//        logServiceTracker.open();
//        LogService log = (LogService) logServiceTracker.getService();
//        log.log(LogService.LOG_INFO, "... started ... :)");
//        logServiceTracker.close();
//        ServiceReference logServiceRef = context.getServiceReference(LogService.class.getName());
//        ServiceReference logReaderServiceRef = context.getServiceReference(LogReaderService.class.getName());
//        if (logServiceRef != null) {
//            LogReaderService reader = (LogReaderService) context.getService(logReaderServiceRef);
//            reader.addLogListener(logConsoleWriter);
//            LogService log = (LogService) context.getService(logServiceRef);
//            log.log(LogService.LOG_INFO, "... started ... :)");
//        }
//        logReaderTracker.close();

    }

//  We use a ServiceListener to dynamically keep track of all the LogReaderService service being
    //  registered or unregistered
    private final ServiceListener serviceListener = new ServiceListener() {
        @Override
        public void serviceChanged(ServiceEvent event) {
            System.out.println("picked up event: " + event.getType());
            final ServiceReference<?> serviceReference = event.getServiceReference();
            final Bundle bundle = serviceReference.getBundle();
            final BundleContext context = bundle.getBundleContext();
            LogReaderService logReaderService = (LogReaderService) context.getService(serviceReference);
            if (logReaderService != null) {
                if (event.getType() == ServiceEvent.REGISTERED) {
                    logReaderServices.add(logReaderService);
                    logReaderService.addLogListener(logConsoleWriter);
                } else if (event.getType() == ServiceEvent.UNREGISTERING) {
                    logReaderService.removeLogListener(logConsoleWriter);
                    logReaderServices.remove(logReaderService);
                }
            }
        }
    };

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("[test-client] stops");
        context.removeServiceListener(exampleServiceListener);
//                context.ungetService(serviceReference);
        System.out.println("[test-client] removed exampleServiceListener");
//        context.ungetService(exampleServiceRef);
//        for (Iterator<LogReaderService> i = logReaderServices.iterator(); i.hasNext();) {
//            LogReaderService lrs = i.next();
//            lrs.removeLogListener(logConsoleWriter);
//            i.remove();
//        }
    }

}

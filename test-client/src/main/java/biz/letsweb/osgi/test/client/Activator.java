package biz.letsweb.osgi.test.client;

import biz.letsweb.osgi.test.api.ExampleService;
import java.util.LinkedList;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;
import org.osgi.service.log.LogListener;
import org.osgi.service.log.LogReaderService;
import org.osgi.util.tracker.ServiceTracker;

public class Activator implements BundleActivator {

    private final LinkedList<LogReaderService> logReaderServices = new LinkedList<>();
    private LogListener logConsoleWriter = new LogWriter();
    private ServiceTracker exampleServiceTracker;
    private ServiceReference exampleServiceRef;

    @Override
    public void start(BundleContext context) throws Exception {
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
//        String filter = "(objectclass=" + LogReaderService.class.getName() + ")";
//        try {
//        context.addServiceListener(serviceListener, filter);
        context.addServiceListener(exampleServiceListener);
//        System.out.println("added #serviceListener");
//        } catch (InvalidSyntaxException e) {
//            e.printStackTrace();
//        }
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
        exampleServiceRef = context.getServiceReference(ExampleService.class.getName());
        if (exampleServiceRef != null) {
            try {
                ExampleService exampleService = (ExampleService) context.getService(exampleServiceRef);
                if (exampleService != null) {
                    exampleService.doService();
                } else {
                    System.out.println("Example service unavailable, alternative service.");
                }
            } catch (RuntimeException re) {
                System.out.println("Runtime exception! " + re.getMessage());
            } finally {
                context.ungetService(exampleServiceRef);
            }
        }
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
    private final ServiceListener exampleServiceListener = new ServiceListener() {
        @Override
        public void serviceChanged(ServiceEvent event) {
            if (event.getType() == ServiceEvent.REGISTERED) {
                System.out.println("registered");
            } else if (event.getType() == ServiceEvent.UNREGISTERING) {
                System.out.println("un-registered");
            }
        }
    };

    @Override
    public void stop(BundleContext context) throws Exception {
        context.removeServiceListener(exampleServiceListener);
//        context.ungetService(exampleServiceRef);
//        for (Iterator<LogReaderService> i = logReaderServices.iterator(); i.hasNext();) {
//            LogReaderService lrs = i.next();
//            lrs.removeLogListener(logConsoleWriter);
//            i.remove();
//        }
    }

}

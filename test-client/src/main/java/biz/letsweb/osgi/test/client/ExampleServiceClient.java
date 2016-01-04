package biz.letsweb.osgi.test.client;

import biz.letsweb.osgi.test.api.ExampleService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.prefs.PreferencesService;

/**
 *
 * @author toks
 */
@Component
public class ExampleServiceClient {

//    private static final Logger logger = LoggerFactory.getLogger(ExampleServiceClient.class);
    private ExampleService exampleService;

    public void doExampleService() {
        exampleService.doService();
    }

    public void bindPreferencesService(PreferencesService preferencesService) {
        System.out.println("PreferencesService is linked");
    }

//    public void unbindPreferencesService(PreferencesService preferencesService) {
//        System.out.println("PreferencesService is unlinked");
//    }
    protected void unsetExampleService(ExampleService exampleService) {
        System.out.println("unsetExampleService called");
    }

    @Reference(
            name = "ExampleServiceImpl",
            service = ExampleService.class,
            cardinality = ReferenceCardinality.MANDATORY,
            policy = ReferencePolicy.STATIC,
            unbind = "unsetExampleService"
    )
    protected void setExampleService(ExampleService exampleService) {
        System.out.println("setExampleService " + exampleService);
        this.exampleService = exampleService;
    }

}

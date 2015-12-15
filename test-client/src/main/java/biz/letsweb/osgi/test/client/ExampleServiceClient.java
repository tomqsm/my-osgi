package biz.letsweb.osgi.test.client;

import biz.letsweb.osgi.test.api.ExampleService;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.ReferenceCardinality;
import org.apache.felix.scr.annotations.ReferencePolicy;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.prefs.PreferencesService;

/**
 *
 * @author toks
 */
@Component(
        name = "biz.letsweb.osgi.test.client.ExampleServiceClient",
        immediate = true
)
public class ExampleServiceClient {

//    private static final Logger logger = LoggerFactory.getLogger(ExampleServiceClient.class);
    @Reference(name = "Echo",
               cardinality = ReferenceCardinality.OPTIONAL_UNARY,
               policy = ReferencePolicy.DYNAMIC,
               bind = "bindService",
               unbind = "unbindService")
    private ExampleService exampleService;

    public void doExampleService() {
        exampleService.doService();
    }

    public void bindPreferencesService(PreferencesService preferencesService) {
        System.out.println("PreferencesService is linked");
    }

    public void unbindPreferencesService(PreferencesService preferencesService) {
        System.out.println("PreferencesService is unlinked");
    }

    @Activate
    protected void activate(ComponentContext context) {
        System.out.println("Example activated");
    }

    @Deactivate
    protected void deactivate(ComponentContext context) {
        System.out.println("Example deactivated");
    }

}

package biz.letsweb.osgi.test.impl;

import biz.letsweb.osgi.test.api.ExampleService;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.prefs.PreferencesService;

/**
 *
 * @author toks
 */
@Component(name = "ExampleServiceImpl", immediate = true)
public class ExampleServiceImpl implements ExampleService {

    private String message = "hello from example service implementations";

    @Override
    public void doService() {
        System.out.println(message);
    }

    public void bindPreferencesService(PreferencesService preferencesService) {
        System.out.println("PreferencesService is linked");
    }

    public void unbindPreferencesService(PreferencesService preferencesService) {
        System.out.println("PreferencesService is unlinked");
    }

    /**
     Activate annotated method gets invoked when the service component becomes satisfied with all the service references and their requirements.
     @param context
     */
    @Activate
    public void activate(ComponentContext context) {
        System.out.println("Example activated");
    }

    @Modified
    public void modified(ComponentContext ctx) {
        System.out.println("Example modified");
    }

    @Deactivate
    public void deactivate(ComponentContext context) {
        System.out.println("Example deactivated");
    }
}

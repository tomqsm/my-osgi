package biz.letsweb.osgi.test.impl;

import biz.letsweb.osgi.test.api.ExampleService;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.prefs.PreferencesService;

/**
 *
 * @author toks
 */
@Component(name = "Echo", immediate = true)
@Service(value = ExampleService.class)
@Property(name = ExampleService.ECHO_TYPE_PROP, value = "Declarative Services")
public class ExampleServiceImpl implements ExampleService {

    @Override
    public void doService() {
        System.out.println("ExampleServiceImpl does service...");
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

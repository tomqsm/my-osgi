package biz.letsweb.osgi.test.gogo.command;

import biz.letsweb.osgi.test.api.ExampleService;
import java.io.IOException;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Map;
import javax.naming.NamingException;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Modified;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.osgi.service.component.ComponentContext;

/**
 *
 * @author toks
 */
@Component(name = "Test Gogo Command")
@Service(value = Object.class)
@Properties({
    @Property(name = Constants.SERVICE_PID, value = "TestGogoCommand"),
    @Property(name = "osgi.command.scope", value = "myosgi"),
    @Property(name = "osgi.command.function", value = "testGogo")
})
public class TestGogoCommand implements ManagedService {

    @Reference(referenceInterface = ExampleService.class, bind = "setService")
    private ExampleService exampleService;
    @Reference(referenceInterface = ConfigurationAdmin.class, bind = "setConfigAdmin")
    private ConfigurationAdmin configurationAdmin;

    public void testGogo() throws IOException {
        System.out.println("command working");
        exampleService.doService();
        final Configuration configuration = configurationAdmin.getConfiguration("TestGogoCommand");
        configuration.update();
    }

    @Activate
    public void onActivate(Map<String, Object> properties, ComponentContext componentContext) throws NamingException {
        final BundleContext bundleContext = componentContext.getBundleContext();
        final String bundleSymbolicName = bundleContext.getBundle().getSymbolicName();
        final String nameProperty = (String) properties.get("name");
//        final String nameProperty = (String) properties.get("TestGogoCommand.name");
        System.out.println(bundleSymbolicName + " activated ... nameProperty?: " + nameProperty);
    }

    @Modified
    public void onModify(ComponentContext componentContext) {
        final String bundleSymbolicName = componentContext.getBundleContext().getBundle().getSymbolicName();
        System.out.println(bundleSymbolicName + " modified ...");
    }

    public void setService(ExampleService exampleService) {
        System.out.println("setting ExampleService service");
        this.exampleService = exampleService;
    }

    /**
     This uses Apache Felix File Install to scan location which has TestGogoCommand.cfg
     i.e. Constants.SERVICE_PID.cfg felix.fileinstall.dir=/TestGogoCommand.cfg
     @param configurationAdmin
     @throws IOException
     */
    public void setConfigAdmin(ConfigurationAdmin configurationAdmin) throws IOException {
        System.out.println("setting ConfigurationAdmin service");
        this.configurationAdmin = configurationAdmin;
        final Configuration configuration = configurationAdmin.getConfiguration("TestGogoCommand");
        final Dictionary<String, Object> properties = configuration.getProperties();
        System.out.println("name: " + properties.get("name"));
    }

    @Override
    public void updated(Dictionary<String, ?> properties) throws ConfigurationException {
        System.out.println("XX updated properties: ");
        if (properties != null) {
            Enumeration<String> keys = properties.keys();

            while (keys.hasMoreElements()) {
                String key = keys.nextElement();
                System.out.println(key + " : " + properties.get(key));
            }
        }
    }
}

package biz.letsweb.osgi.test.gogo.command;

import biz.letsweb.osgi.test.api.ExampleService;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;

/**
 *
 * @author toks
 */
@Component(name = "Test Gogo Command")
@Service(value = Object.class)
@Properties({
    @Property(name = "osgi.command.scope", value = "myosgi"),
    @Property(name = "osgi.command.function", value = "testgogo")
})
public class TestGogoCommand {

    @Reference(referenceInterface = ExampleService.class, bind = "setService")
    private ExampleService exampleService;

    public void testgogo() {
        System.out.println("command working");
        exampleService.doService();
    }

    public void setService(ExampleService exampleService) {
        System.out.println("setting example service");
        this.exampleService = exampleService;
    }
}

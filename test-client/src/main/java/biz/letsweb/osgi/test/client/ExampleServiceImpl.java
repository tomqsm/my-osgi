package biz.letsweb.osgi.test.client;

import biz.letsweb.osgi.test.api.ExampleService;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;

/**
 *
 * @author toks
 */
@Component(name = "ExampleServiceImpl")
@Service(value = ExampleService.class)
public class ExampleServiceImpl implements ExampleService {

    private String message = "example service implementation executing well it changes";

    @Override
    public void doService() {
        System.out.println(message);
    }
    
}

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

    private String message = "hello from example service implementations";

    @Override
    public void doService() {
        System.out.println(message);
    }
    
}

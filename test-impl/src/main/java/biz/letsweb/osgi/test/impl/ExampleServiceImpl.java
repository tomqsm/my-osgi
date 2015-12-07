package biz.letsweb.osgi.test.impl;

import biz.letsweb.osgi.test.api.ExampleService;
import org.apache.aries.blueprint.annotation.Bean;
import org.apache.aries.blueprint.annotation.Service;
import org.apache.aries.blueprint.annotation.ServiceProperty;

/**
 *
 * @author toks
 */
@Bean(id = "exampleService")
@Service(interfaces = ExampleService.class,
         serviceProperties = @ServiceProperty(key = ExampleService.ECHO_TYPE_PROP, value = "Blueprint-Annotations"))
public class ExampleServiceImpl implements ExampleService {

    @Override
    public void doService() {
        System.out.println("ExampleServiceImpl does service...");
    }

}

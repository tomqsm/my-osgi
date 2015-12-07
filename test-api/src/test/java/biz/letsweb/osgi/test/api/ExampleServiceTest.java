package biz.letsweb.osgi.test.api;

//import javax.inject.Inject;
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.CoreMatchers.notNullValue;
//import static org.junit.Assert.assertThat;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.ops4j.pax.exam.Configuration;
//import static org.ops4j.pax.exam.CoreOptions.junitBundles;
//import static org.ops4j.pax.exam.CoreOptions.mavenBundle;
//import static org.ops4j.pax.exam.CoreOptions.options;
//import static org.ops4j.pax.exam.CoreOptions.provision;
//import static org.ops4j.pax.exam.CoreOptions.systemProperty;
//import org.ops4j.pax.exam.Option;
//import org.ops4j.pax.exam.junit.PaxExam;
//import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
//import org.ops4j.pax.exam.spi.reactors.PerClass;
import org.osgi.framework.BundleContext;

/**
 *
 * @author toks
 */
//@RunWith(PaxExam.class)
//@ExamReactorStrategy(PerClass.class)
public class ExampleServiceTest {

//    @Inject
    private BundleContext bc;

//    @Configuration
//    public Option[] config() {
//        return options(
//                systemProperty("logback.configurationFile").value(
//                        "file:src/test/resources/logback.xml"),
//                provision(mavenBundle("org.slf4j", "slf4j-api", "1.7.2"),
//                          mavenBundle("ch.qos.logback", "logback-core", "1.1.3"),
//                          mavenBundle("ch.qos.logback", "logback-classic", "1.1.3")),
//                junitBundles()
//        );
//    }

//    @Test
//    public void shouldHaveBundleContext() {
//        System.out.println("");
//        assertThat(bc, is(notNullValue()));
//    }
}

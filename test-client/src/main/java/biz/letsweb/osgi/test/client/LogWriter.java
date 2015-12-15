package biz.letsweb.osgi.test.client;

import org.osgi.service.log.LogEntry;
import org.osgi.service.log.LogListener;

/**
 *
 * @author toks
 */
public class LogWriter implements LogListener {

    @Override
    public void logged(LogEntry le) {
        if (le.getMessage() != null) {
            System.out.println(le.getTime() + " [" + le.getBundle().getSymbolicName() + "] " + le.getMessage());
        }
    }

}

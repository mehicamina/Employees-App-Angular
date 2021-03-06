package ba.com.zira.stc.test_project.application;

import org.apache.commons.daemon.Daemon;
import org.apache.commons.daemon.DaemonContext;
import org.apache.commons.daemon.DaemonInitException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import ba.com.zira.stc.test_project.configuration.ApplicationConfiguration;

public class TestProjectLauncher implements Daemon {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestProjectLauncher.class);

    private ConfigurableApplicationContext applicationContext;

    @Override
    public void init(final DaemonContext context) throws DaemonInitException {
        try {
            LOGGER.info("Initializing application");
            applicationContext = SpringApplication.run(ApplicationConfiguration.class, new String[] {});
        } catch (Exception e) {
            LOGGER.error("init => {}", e);
            if (context != null) {
                context.getController().shutdown();
            }
            return;
        }
    }

    @Override
    public void start() throws Exception {
        LOGGER.info("Application started successfully");
    }

    @Override
    public void stop() throws Exception {
        applicationContext.stop();
    }

    @Override
    public void destroy() {
        LOGGER.info("Destroying application");
    }

    public static void main(final String[] args) throws Exception {
        SpringApplication.run(ApplicationConfiguration.class, new String[] {});
    }

}

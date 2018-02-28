package sk.akademiasovy.library;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import sk.akademiasovy.library.resources.Book;
import sk.akademiasovy.library.resources.Login;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;


public class secretApplication extends Application<secretConfiguration> {

    public static void main(final String[] args) throws Exception {
        new secretApplication().run(args);
    }

    @Override
    public String getName() {
        return "secret";
    }

    @Override
    public void initialize(final Bootstrap<secretConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final secretConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
        environment.jersey().register(new Book());
        environment.jersey().register(new Login());
        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);
// Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");


    }

}

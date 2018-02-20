package sk.akademiasovy.library;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import sk.akademiasovy.library.resources.Book;


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
        environment.jersey().register(
                new Book()
        );
    }

}

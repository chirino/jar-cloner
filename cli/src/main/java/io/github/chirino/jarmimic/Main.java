package io.github.chirino.jarmimic;

import io.quarkus.picocli.runtime.annotations.TopCommand;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.inject.Inject;
import picocli.CommandLine;

@QuarkusMain
@TopCommand
@CommandLine.Command(name = "jar-mimic", mixinStandardHelpOptions = true, subcommands = {
        Extract.class,
        Create.class,
})
public class Main implements QuarkusApplication {

    @Inject
    CommandLine.IFactory factory;

    @Override
    public int run(String... args) throws Exception {
        return new CommandLine(this, factory).execute(args);
    }
}




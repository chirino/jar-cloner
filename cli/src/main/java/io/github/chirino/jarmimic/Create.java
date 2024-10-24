package io.github.chirino.jarmimic;

import io.github.chirino.jarmimic.lib.Tool;
import picocli.CommandLine;

@CommandLine.Command(name = "create", description = "creates the zip file with the structure of the meta file")
public class Create implements Runnable {

    @CommandLine.Option(names = {"-C", "--dir"}, description = "directory to change to before creating the archive", defaultValue = ".")
    private String directory;

    @CommandLine.Parameters(description = "the yaml file that holds the structure information")
    private String metaFile;

    @CommandLine.Parameters(description = "the zip/jar file to create")
    private String archiveFile;


    @Override
    public void run() {
        System.out.println("creating zip/jar structure from: " + metaFile + ", to: " + archiveFile);
        try {
            Tool.create(new String[]{directory}, metaFile, archiveFile);
        } catch (Exception e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
            System.exit(2);
        }

    }
}


package io.github.chirino.jarmimic;

import io.github.chirino.jarmimic.lib.Tool;
import picocli.CommandLine;

@CommandLine.Command(name = "extract", description = "Extracts the structure of a zip/jar file")
public class Extract implements Runnable {

    @CommandLine.Parameters(description = "the zip/jar file to extract the structure from")
    private String archiveFile;

    @CommandLine.Parameters(description = "the yaml file to hold the structure information")
    private String metaFile;

    @Override
    public void run() {
        System.out.println("extracting structure from: " + archiveFile + ", to: " + metaFile);
        try {
            Tool.extract(archiveFile, metaFile, null);
            System.out.println("wrote: " + metaFile);
        } catch (Throwable e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
            System.exit(2);
        }
    }
}


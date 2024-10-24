package io.github.chirino.jarmimic.maven;

import java.io.File;

import io.github.chirino.jarmimic.lib.Tool;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * A mojo to extract the jar structure yaml
 */
@Mojo(name = "extract")
public class ExtractMojo extends AbstractMojo {

    @Parameter(property = "jar-mimic.structure-yaml", defaultValue = "${basedir}/src/main/jar-mimic.yaml", required = true)
    private File yamlFile;

    @Parameter(property = "jar-mimic.jar", required = true)
    private File archiveFile;

    public void execute() throws MojoExecutionException, MojoFailureException {
        System.out.println("creating jar structure from: " + archiveFile + ", to: " + yamlFile);
        try {
            yamlFile.getParentFile().mkdirs();
            Tool.extract(archiveFile.getAbsolutePath(), yamlFile.getAbsolutePath(), null);
        } catch (Exception e) {
            throw new MojoExecutionException("Error creating yaml file", e);
        }
    }
}

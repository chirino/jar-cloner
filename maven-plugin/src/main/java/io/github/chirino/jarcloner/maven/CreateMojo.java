package io.github.chirino.jarcloner.maven;

import io.github.chirino.jarcloner.lib.Tool;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.apache.maven.project.MavenProjectHelper;

import java.io.File;

/**
 * A mojo to recreate a jar from the jar structure yaml
 */
@Mojo(name = "create")
public class CreateMojo extends AbstractMojo {

    @Parameter(property = "jar-cloner.dir", defaultValue = "${project.build.directory}/classes", required = true)
    private File directory;

    @Parameter(property = "jar-cloner.structure-yaml", defaultValue = "${basedir}/src/main/jar-cloner.yaml", required = true)
    private File metaFile;

    @Parameter(property = "jar-cloner.resources", defaultValue = "${basedir}/src/main/jar-cloner-resources", required = false)
    private File resources;

    @Parameter(property = "jar-cloner.to-jar", defaultValue = "${project.build.directory}/${project.build.finalName}.jar", required = true)
    private File toArchiveFile;

    @Parameter(property = "jar-cloner.attach", defaultValue = "true")
    private boolean attach;

    @Parameter(defaultValue = "${project}", readonly = true, required = true)
    private MavenProject project;

    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("creating jar structure from: " + metaFile + ", to: " + toArchiveFile);
        try {
            toArchiveFile.getParentFile().mkdirs();
            String[] directories = {resources.getAbsolutePath(), directory.getAbsolutePath()};
            Tool.create(directories, metaFile.getAbsolutePath(), toArchiveFile.getAbsolutePath());

            if (attach) {
                // Attach the artifact to the project
                project.getArtifact().setFile(toArchiveFile);
            }

        } catch (Exception e) {
            throw new MojoExecutionException("Error creating jar file", e);
        }
    }
}

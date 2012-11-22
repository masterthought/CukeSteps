package net.masterthought;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import java.io.IOException;

/**
 * creates html report for Cucumber Steps along with necessary assets
 * @goal cukesteps
 */
public class CukeStepsMojo extends AbstractMojo {

    /**
     * Directory to parse
     * @parameter expression="."
     */
    private String directory;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("Parsing .java and .feature files");
        ReportCreator reportCreator = new ReportCreator(directory);
        try {
            reportCreator.generateAll();
        } catch (IOException e) {
            throw new MojoExecutionException("Error creating the report");
        }
    }
}

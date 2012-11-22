package net.masterthought;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import java.io.IOException;

/**
 * creates gson report for Cucumber Steps
 * @goal cukesteps
 */
public class CukeStepsMojo extends AbstractMojo {


    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("Parsing .java and .feature files");
        ReportCreator reportCreator = new ReportCreator();
        try {
            reportCreator.generateAll();
        } catch (IOException e) {
            getLog().error("Error creating the report.\n" + e);
        }
    }
}

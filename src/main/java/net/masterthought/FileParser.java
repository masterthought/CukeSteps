package net.masterthought;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class FileParser {

    private String directory;
    private SortedSet<Step> steps = new TreeSet<Step>();

    public Set<Step> getSteps() {
        return steps;
    }



    private List<File> allJavaAndFeatureFiles() {
        List<File> desiredFiles = new ArrayList<File>();
        try {

            File dir = new File(directory);

            System.out.println("Getting all files in " + dir.getCanonicalPath() + " including those in subdirectories");
            List<File> files = (List<File>) FileUtils.listFiles(dir, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
            for (File file : files) {
                if (file.getCanonicalPath().contains(".java") || file.getCanonicalPath().contains(".feature") && !file.getCanonicalPath().contains("target/")) {
                    System.out.println("Found file: " + file.getCanonicalPath());
                    desiredFiles.add(file);
                }
            }
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
        return desiredFiles;
    }

    private Map<String, List<String>> allLinesToParse() {
        Map<String, List<String>> allLines = new HashMap<String, List<String>>();
        for (File file : allJavaAndFeatureFiles()) {
            try {
                allLines.put(file.getCanonicalPath(), FileUtils.readLines(file, "UTF-8"));
            } catch (IOException e) {
                throw new RuntimeException("error when trying to parse file: " + file);
            }
        }
        return allLines;
    }

    public FileParser(String directory){
        this.directory = directory == null ? "." : directory;
        Map<String, List<String>> allLines = allLinesToParse();
        for (String filename : allLines.keySet()) {
            for (String line : allLines.get(filename)) {
                line = line.trim();
                line = line.replaceAll("@", "");
                line = line.replace("^", "");
                line = line.replace("$", "");
                processAll(line, filename);
            }
        }
    }

    public FileParser() {
        this(".");
    }

    private void processAll(String line, String filename) {
        if (line.indexOf("Given") == 0 || line.indexOf("When") == 0 || line.indexOf("Then") == 0 || line.indexOf("And") == 0) {
            line = line.replace("(\"", " ");
            line = line.replace("\")", "");
            line = line.trim();
            Step step = new Step(getSteps().size() + 1, line, filename);
            boolean alreadyStored = false;
            for(Step existingStep : steps)  {
                if(existingStep.getText().equalsIgnoreCase(step.getText())) alreadyStored = true;
            }
            if(!alreadyStored) steps.add(step);
        }

    }


}

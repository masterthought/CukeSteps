package net.masterthought;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.FileUtils;
import org.apache.velocity.VelocityContext;
import org.codehaus.plexus.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Set;

public class ReportCreator {

    private static final String reportJs = "target/assets/report.js";
    private static final String cukesHtml = "target/cukes.html";
    private String directory;

    private enum Files {
        JQUERY_SUGGEST("target/assets/jquery.jsonSuggest.js", "velocity/jquery.jsonSuggest.js.vm"),
        JSON_SUGGEST_CSS("target/assets/jsonSuggest.css", "velocity/jsonSuggest.css.vm"),
        JQUERY_MIN("target/assets/jquery-1.3.2.min.js", "velocity/jquery-1.3.2.min.js.vm"),
        JSON2("target/assets/json2.js", "velocity/json2.js.vm");

        Files(String destination, String velocity){
            this.destination = destination;
            this.velocity = velocity;
        }

        public String destination() {
            return destination;
        }

        public String velocity() {
            return velocity;
        }
        private String destination;

        private String velocity;
    }

    public ReportCreator(String directory){
        this.directory = directory;
    }

    public String createNewMainJson(String directory) {
        Gson allJson = new Gson();
        Set<Step> steps = new FileParser(directory).getSteps();
        Type stepType = new TypeToken<Set<Step>>() {
        }.getType();
        String prefix = "var steps = {}; steps.list=";
        return prefix + allJson.toJson(steps, stepType);
    }

    public void generateAll() throws IOException {
        FileUtils.writeStringToFile(new File(reportJs), createNewMainJson(directory), "UTF-8");
        try {
            createMainPage();
            createAssets();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createMainPage() throws Exception {
        VelocityUtil velocityUtil = new VelocityUtil("velocity/cukes.vm");
        VelocityContext context = velocityUtil.getContext();
        FileUtils.writeStringToFile(new File(cukesHtml), velocityUtil.generateString(context));
    }

    private void createAssets(){
        VelocityUtil velocityUtil;
        try {
            for (Files files : Files.values()){
                velocityUtil = new VelocityUtil(files.velocity());
                VelocityContext context = velocityUtil.getContext();
                FileUtils.writeStringToFile(new File(files.destination()), restoreDollarSign(velocityUtil, context));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String restoreDollarSign(VelocityUtil velocityUtil, VelocityContext context) throws Exception {
        String[] lines = StringUtils.split(velocityUtil.generateString(context), "\n");
        String newContent = "";
        for(String line : lines){
           line = line.replace("dollario", "$");
           newContent += line + "\n";
        }
        return newContent;
    }
}

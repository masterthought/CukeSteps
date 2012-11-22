package net.masterthought;


import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.util.Properties;


public class VelocityUtil {

    private Template template;

    private VelocityContext context;

    public VelocityUtil(String template) throws Exception {
        VelocityEngine ve = new VelocityEngine();
        ve.init(getProperties());
        this.template = ve.getTemplate(template);
        this.context = new VelocityContext();
    }

    public VelocityContext getContext() {
        return context;
    }

    public String generateString(VelocityContext context) throws Exception {
        StringWriter stringWriter = new StringWriter();
        this.context = context;
        template.merge(this.context, stringWriter);
        stringWriter.close();
        stringWriter.flush();
        return stringWriter.toString();
    }

    private static Properties getProperties() {
        Properties props = new Properties();
        props.setProperty("resource.loader", "class");
        props.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        return props;
    }
}



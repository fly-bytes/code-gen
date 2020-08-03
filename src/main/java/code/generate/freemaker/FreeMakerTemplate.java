package code.generate.freemaker;

import code.generate.bean.CodeInfo;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.Data;

import java.io.*;

@Data
public class FreeMakerTemplate {
    private String TEMPLATE_PATH = "/templates";
    private String entity = "entity.ftl";
    private String service = "service.ftl";
    private String serviceImpl = "serviceImpl.ftl";
    private String repository = "repository.ftl";
    private String controller = "controller.ftl";
    private CodeInfo codeInfo;
    private Configuration config;

    public void generate(CodeInfo codeInfo) {
        this.codeInfo = codeInfo;
        setConfiguration();

        generateEntity();
        generateService();
        generateServiceImpl();
        generateRepository();
        generateController();
    }

    private void setConfiguration() {
        Configuration config = new Configuration();

        try {
            config.setClassLoaderForTemplateLoading(getClass().getClassLoader(), TEMPLATE_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.config = config;
    }

    private void generateEntity() {
        try {
            String path = getPath(codeInfo.getEntryPkg());
            checkFileDir(path);

            Template template = config.getTemplate("entity.ftl", "UTF-8");
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path + "/" + codeInfo.getEntry() + ".java"), "UTF-8"));
            template.process(codeInfo, out);

            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }

    }

    private void generateService() {
        try {
            String path = getPath(codeInfo.getServicePkg());
            checkFileDir(path);

            Template template = config.getTemplate("service.ftl", "UTF-8");
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path + "/" + codeInfo.getService() + ".java"), "UTF-8"));
            template.process(codeInfo, out);

            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }

    }

    private void generateServiceImpl() {
        try {
            String path = getPath(codeInfo.getServiceImplPkg());
            checkFileDir(path);

            Template template = config.getTemplate("serviceImpl.ftl", "UTF-8");
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path + "/" + codeInfo.getServiceImpl() + ".java"), "UTF-8"));
            template.process(codeInfo, out);

            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    private void generateRepository() {
        try {
            String path = getPath(codeInfo.getRepositoryPkg());
            checkFileDir(path);

            Template template = config.getTemplate("repository.ftl", "UTF-8");
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path + "/" + codeInfo.getRepository() + ".java"), "UTF-8"));
            template.process(codeInfo, out);

            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }


    private void generateController() {
        try {
            String path = getPath(codeInfo.getControllerPkg());
            checkFileDir(path);

            Template template = config.getTemplate("controller.ftl", "UTF-8");
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path + "/" + codeInfo.getController() + ".java"), "UTF-8"));
            template.process(codeInfo, out);

            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    private void checkFileDir(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    private String getPath(String path) {
        return "src/main/java/" + path.replace(".", "/");
    }
}

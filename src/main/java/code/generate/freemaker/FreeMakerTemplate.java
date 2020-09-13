package code.generate.freemaker;

import code.generate.bean.CodeInfo;
import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.utility.StringUtil;
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
    private Configuration currentConfig;

    // 自定义ftl
    private String entryFtl;
    private String controllerFtl;
    private String serviceFtl;
    private String serviceImplFtl;
    private String repositoryFtl;

    public FreeMakerTemplate(String entryFtl, String controllerFtl, String serviceFtl, String serviceImplFtl, String repositoryFtl) {
        this.entryFtl = entryFtl;
        this.controllerFtl = controllerFtl;
        this.serviceFtl = serviceFtl;
        this.serviceImplFtl = serviceImplFtl;
        this.repositoryFtl = repositoryFtl;
    }

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
        Configuration currentConfig = new Configuration();

        try {
            currentConfig.setClassForTemplateLoading(getClass(), TEMPLATE_PATH);
            config.setClassLoaderForTemplateLoading(getClass().getClassLoader(), TEMPLATE_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.config = config;
        this.currentConfig = currentConfig;
    }

    private void generateEntity() {
        try {
            String path = getPath(codeInfo.getEntryPkg());
            checkFileDir(path);

            Template template;

            if (entryFtl != null && entryFtl.length() > 0) {
                template = currentConfig.getTemplate(entryFtl, "UTF-8");
            } else {
                template = config.getTemplate("entity.ftl", "UTF-8");
            }

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

            Template template;

            if (serviceFtl != null && serviceFtl.length() > 0) {
                template = currentConfig.getTemplate(serviceFtl, "UTF-8");
            } else {
                template = config.getTemplate("service.ftl", "UTF-8");
            }

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

            Template template;

            if (serviceImplFtl != null && serviceImplFtl.length() > 0) {
                template = currentConfig.getTemplate(serviceImplFtl, "UTF-8");
            } else {
                template = config.getTemplate("serviceImpl.ftl", "UTF-8");
            }

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

            Template template;

            if (repositoryFtl != null && repositoryFtl.length() > 0) {
                template = currentConfig.getTemplate(repositoryFtl, "UTF-8");
            } else {
                template = config.getTemplate("repository.ftl", "UTF-8");
            }

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

            Template template;

            if (controllerFtl != null && controllerFtl.length() > 0) {
                template = currentConfig.getTemplate(controllerFtl, "UTF-8");
            } else {
                template = config.getTemplate("controller.ftl", "UTF-8");
            }

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

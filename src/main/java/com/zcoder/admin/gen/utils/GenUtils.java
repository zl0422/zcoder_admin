package com.zcoder.admin.gen.utils;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zcoder.admin.core.utils.FileUtils;
import com.zcoder.admin.core.utils.guava.MyStrings;
import com.zcoder.admin.core.utils.xml.JaxbMapper;
import com.zcoder.admin.gen.domain.Column;
import com.zcoder.admin.gen.domain.Gen;
import com.zcoder.admin.gen.domain.GenScheme;
import com.zcoder.admin.gen.domain.GenTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * @See {copy from jeesite}
 * Created by lin on 2016-06-13.
 */
@Slf4j
public class GenUtils {

    /**
     * jdbcType-->javaType
     *
     * @param jdbcType
     * @return
     */
    public static String toJavaType(String jdbcType) {

        String result = "";

        if (StringUtils.startsWithIgnoreCase(jdbcType, "CHAR")
                || StringUtils.startsWithIgnoreCase(jdbcType, "VARCHAR")
                || StringUtils.startsWithIgnoreCase(jdbcType, "NARCHAR")) {
            result = "String";
        } else if (StringUtils.startsWithIgnoreCase(jdbcType, "DATETIME")
                || StringUtils.startsWithIgnoreCase(jdbcType, "DATE")
                || StringUtils.startsWithIgnoreCase(jdbcType, "TIMESTAMP")) {
            result = "Date";
        } else if (StringUtils.startsWithIgnoreCase(jdbcType, "BIGINT")
                || StringUtils.startsWithIgnoreCase(jdbcType, "NUMBER")
                || StringUtils.startsWithIgnoreCase(jdbcType, "INT")
                || StringUtils.startsWithIgnoreCase(jdbcType, "SMALLINT")) {
            // 如果是浮点型
            String[] ss = StringUtils.split(StringUtils.substringBetween(jdbcType, "(", ")"), ",");
            if (ss != null && ss.length == 2 && Integer.parseInt(ss[1]) > 0) {
                result = "Double";
            } else if (ss != null && ss.length == 1 && Integer.parseInt(ss[0]) <= 10) {
                result = "Integer";
            } else {
                result = "Long";
            }
        }
        return result;

    }

    /**
     * XML文件转换为对象
     *
     * @param fileName
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T fileToObject(String fileName, Class<?> clazz) {
        try {
            String pathName = "/templates/gen/" + fileName;
            Resource resource = new ClassPathResource(pathName);
            InputStream is = resource.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                sb.append(line).append("\r\n");
            }
            if (is != null) {
                is.close();
            }
            if (br != null) {
                br.close();
            }
            return (T) JaxbMapper.fromXml(sb.toString(), clazz);
        } catch (Exception e) {
            log.error("Error file convert: {}", e.getMessage());
        }
        return null;
    }

    /**
     * 获取模板路径
     *
     * @return
     */
    public static String getTemplatePath() {
        try {
            File file = new DefaultResourceLoader().getResource("").getFile();
            if (file != null) {
                return file.getAbsolutePath() + File.separator + StringUtils.replaceEach(GenUtils.class.getName(),
                        new String[]{"util." + GenUtils.class.getSimpleName(), "."}, new String[]{"template", File.separator});
            }
        } catch (Exception e) {
            log.error("{}", e);
        }

        return "";
    }


    /**
     * 根据生成方案得到模板
     *
     * @param scheme 与GenScheme枚举对应
     * @return
     */
    public static List<GenTemplate> getTemplateList(String scheme) {
        List<GenTemplate> templates = Lists.newArrayList();
        log.info("scheme:" + scheme);
        if (Strings.isNullOrEmpty(scheme)) {
            for (GenScheme genScheme : GenScheme.values()) {
                log.info("template:" + genScheme.getValue());
                GenTemplate template = fileToObject(genScheme.getValue(), GenTemplate.class);
                templates.add(template);
            }
        } else {
            for (GenScheme genScheme : GenScheme.values()) {
                if (genScheme.getLabel().equalsIgnoreCase(scheme)) {
                    log.info("template:" + genScheme.getValue());
                    GenTemplate template = fileToObject(genScheme.getValue(), GenTemplate.class);
                    templates.add(template);
                    break;
                }
            }
        }
        return templates;
    }


    /**
     * 构建数据模型
     *
     * @param gen
     * @return
     */
    public static Map<String, Object> getDataModel(Gen gen,List<Column> columns) {
        Map<String, Object> model = Maps.newHashMap();
        model.put("packageName", MyStrings.lowerCase(gen.getPackageName()));
        model.put("lastPackageName", MyStrings.substringAfterLast((String) model.get("packageName"), "."));
        model.put("moduleName", MyStrings.lowerCase(gen.getModuleName()));
        model.put("subModuleName", MyStrings.lowerCase(gen.getSubModuleName()));
        model.put("className", MyStrings.uncapitalize(gen.getClassName()));
        model.put("ClassName", MyStrings.capitalize(gen.getClassName()));
        model.put("funcName", MyStrings.capitalize(gen.getFuncName()));
        model.put("urlPrefix", model.get("moduleName") + (MyStrings.isNotBlank(gen.getSubModuleName())
                ? "/" + MyStrings.lowerCase(gen.getSubModuleName()) : "") + "/" + model.get("className"));
        model.put("viewPrefix", //StringUtils.substringAfterLast(model.get("packageName"),".")+"/"+
                model.get("urlPrefix"));
        model.put("permissionPrefix", model.get("moduleName") + (MyStrings.isNotBlank(gen.getSubModuleName())
                ? ":" + MyStrings.lowerCase(gen.getSubModuleName()) : "") + ":" + model.get("className"));
        model.put("table", gen.getTableName());
        return model;
    }

    /**
     * 生成文件
     *
     * @param tpl           模板
     * @param model         数据模型
     * @param isReplaceFile 是否替换文件
     */
    public static void generate(GenTemplate tpl, Map<String, Object> model, boolean isReplaceFile) throws Exception {
        // 获取生成文件
        String fileName = getProjectPath() + File.separator
                + MyStrings.replaceEach(FreeMarkerUtils.renderString(tpl.getFilePath() + "/", model),
                new String[]{"//", "/", "."}, new String[]{File.separator, File.separator, File.separator})
                + FreeMarkerUtils.renderString(tpl.getFileName(), model);
        // 获取生成文件内容
        String content = FreeMarkerUtils.renderString(StringUtils.trimToEmpty(tpl.getContent()), model);

        if (log.isDebugEnabled()){
            log.debug("target fileName === " + fileName);
            log.debug("=========before ==========");
            log.debug(tpl.getContent());
            log.debug("========= after ==========");
            log.debug(content);
        }

        if (isReplaceFile){
            FileUtils.deleteFile(fileName);
        }
        // 创建并写入文件
        if (FileUtils.createFile(fileName)){
            FileUtils.writeToFile(fileName, content, true);
            log.debug(" file create === " + fileName);
        }else{
            log.debug(" file extents === " + fileName);
        }

    }

    /**
     * 获取项目路径
     *
     * @return
     */
    private static String getProjectPath() {
        String projectPath = "";
        try {
            File file = new DefaultResourceLoader().getResource("").getFile();
            if (file != null) {
                while (true) {
                    File f = new File(file.getPath() + File.separator + "src" + File.separator + "main");
                    if (f == null || f.exists()) {
                        break;
                    }
                    if (file.getParentFile() != null) {
                        file = file.getParentFile();
                    } else {
                        break;
                    }
                }
                projectPath = file.toString();
            }
        } catch (IOException e) {
            log.info("get project path error", e);
        }
        return projectPath;
    }

}

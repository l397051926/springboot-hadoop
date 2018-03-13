package com.springboot.poi.until;


import org.springframework.util.ResourceUtils;


import java.io.FileInputStream;

import java.io.FileNotFoundException;


/**
 * @author liumingxin
 * @create 2018/2/24 9:42
 * @desc
 */
public class TemplateFileUtil {


    public static FileInputStream getTemplates(String tempName) throws FileNotFoundException {

        return new FileInputStream(ResourceUtils.getFile("classpath:excel-templates/" + tempName));

    }
}
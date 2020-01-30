package com.example.project.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Paths;

import com.example.project.exception.BusinessRuleException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

/**
 * FileUtil
 */
public class FileUtil {

    public static InputStream multiPartFileToInputStream(MultipartFile file) {

        try {
            return file.getInputStream();
        } 
        catch (IOException e) {
            throw new BusinessRuleException("Erro ao transferir arquivo para stream!");
        }
    }

    public static void fechaWorkBook(Workbook workbook){
        try {
            workbook.close();
        } catch (IOException e) {
            throw new BusinessRuleException("Erro ao fechar o workbook!");
        }
    }

    public static Resource stringToResource(String path) {
        try {
            return new UrlResource((Paths.get(path)).toUri());
        } catch (MalformedURLException e) {
            throw new BusinessRuleException("Not found");
        }
    }

    public static Workbook abreWorkbook(InputStream inputStream){
        try {
            InputStream excelFile = inputStream;
            return new XSSFWorkbook(excelFile);
        } catch (IOException e) {
            throw new BusinessRuleException("Problema ao ler o arquivo! Certifique-se que se trata de um arquivo do excel.");
        }
    }

}
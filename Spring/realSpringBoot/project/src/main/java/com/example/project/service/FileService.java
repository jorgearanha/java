package com.example.project.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Time;
import java.util.Iterator;
import java.util.List;

import com.example.project.domain.Product;
import com.example.project.domain.Supplier;
import com.example.project.exception.BusinessRuleException;
import com.example.project.exception.DataNotFoundException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.util.StringUtils;

@Service
public class FileService {

    @Autowired
    ProductService productService;

    public String saveImage(MultipartFile file) {

        String path = "C:\\Users\\jorge.aranha\\Pictures\\";

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        String image = path + fileName;

        if (file.isEmpty())
            throw new BusinessRuleException("Failed to store empty file");

        try {
            Files.copy(file.getInputStream(), Paths.get(image), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new BusinessRuleException("Failed to store");
        }

        return image;

    }

    public Resource stringToResource(String path) {
        try {
            return new UrlResource((Paths.get(path)).toUri());
        } catch (MalformedURLException e) {
            throw new BusinessRuleException("Not found");
        }
    }

    public MediaType tipoResource(Resource resource) {
        String type = "";

        try {
            type = Files.probeContentType(resource.getFile().toPath());
        } catch (IOException e) {
            throw new BusinessRuleException("Erro na identificação do MediaType");
        }

        return MediaType.parseMediaType(type);
    }

    public void tableToProduct() {
        Iterator<Row> iterator = iteratorTable();
        iterator.next();

        while (iterator.hasNext()) {

            Row currentRow = iterator.next();

            Product entity = Product.builder() //
                    .productName(currentRow.getCell(1).getStringCellValue()) //
                    .supplier(Supplier.builder() //
                            .id((int) currentRow.getCell(2).getNumericCellValue()) //
                            .build()) //
                    .unitPrice(currentRow.getCell(3).getNumericCellValue()) //
                    .packageName(currentRow.getCell(4).getStringCellValue()) //
                    .isDiscontinued(((int) (currentRow.getCell(5).getNumericCellValue())) == 1) //
                    .build();

            productService.createProduct(entity);
        }

    }

    private Iterator<Row> iteratorTable() {
        String arq = "C:\\Users\\jorge.aranha\\Documents\\Git\\java\\Spring\\decola.xlsx";
 
        Workbook workbook = null;

        try {
            FileInputStream excelFile = new FileInputStream(new File(arq));
            workbook = new XSSFWorkbook(excelFile);
        } catch (IOException e) {
            throw new DataNotFoundException("Problema ao ler o arquivo");
        }
        Sheet datatypeSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = datatypeSheet.iterator();

        try {
            workbook.close();
        } catch (IOException e) {
            throw new BusinessRuleException("Erro ao fechar o workbook");
        }

        return iterator;
    }

}
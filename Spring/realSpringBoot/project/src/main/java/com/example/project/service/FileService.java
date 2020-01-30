package com.example.project.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Iterator;

import static com.example.project.util.FileUtil.fechaWorkBook;
import static com.example.project.util.FileUtil.multiPartFileToInputStream;
import static com.example.project.util.FileUtil.abreWorkbook;

import com.example.project.domain.entities.Product;
import com.example.project.domain.entities.Supplier;
import com.example.project.exception.BusinessRuleException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
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

    public MediaType tipoResource(Resource resource) {
        String type = "";

        try {
            type = Files.probeContentType(resource.getFile().toPath());
        } catch (IOException e) {
            throw new BusinessRuleException("Erro na identificação do MediaType");
        }

        return MediaType.parseMediaType(type);
    }

    public Integer tableToProduct(MultipartFile file) {
        Integer count = 0;
        
        Iterator<Row> iterator = iteratorTable(multiPartFileToInputStream(file));
        iterator.next();

        while (iterator.hasNext()) {

            count++;
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

        return count;

    }

    private Iterator<Row> iteratorTable(InputStream inputStream) {
        Workbook workbook = abreWorkbook(inputStream);
        
        Sheet datatypeSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = datatypeSheet.iterator();

        fechaWorkBook(workbook);

        return iterator;
    }

    
}
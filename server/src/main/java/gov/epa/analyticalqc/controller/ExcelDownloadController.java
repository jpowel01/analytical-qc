package gov.epa.analyticalqc.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.opencsv.exceptions.CsvValidationException;
import gov.epa.analyticalqc.dto.BatchSearchRequestDto;
import gov.epa.analyticalqc.dto.ExcelDownloadDto;
import gov.epa.analyticalqc.entity.Substance;
import gov.epa.analyticalqc.entity.SubstanceAnnotation;
import gov.epa.analyticalqc.repository.SubstanceRepository;
import gov.epa.analyticalqc.service.ExcelDownloadService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.Tuple;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/excel-download")

public class ExcelDownloadController {
    @Autowired
    ExcelDownloadService excelDownloadService;


    @GetMapping("/{id}")
    public ResponseEntity<List<ExcelDownloadDto>> getExcelDownload(@PathVariable("id") String id,
                                                                   @RequestParam(name="pageNo", defaultValue="0") Integer pageNo,
                                                                   @RequestParam(name="pageSize", defaultValue="100") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("id").ascending());

        List<ExcelDownloadDto> findExcelTuple = excelDownloadService.getSingleResult(id); // add pageable here
        if (findExcelTuple == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "stuff not found");
        } else
            return new ResponseEntity<>(findExcelTuple, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ByteArrayResource> getExcelDownload(@RequestBody BatchSearchRequestDto request) {
        byte[] bytes = null;
        HttpHeaders headers = new HttpHeaders();
        HashMap<String, Integer> order = new HashMap<String, Integer>();
        for (int i = 0; i < request.dtxsids.size(); i++) {
            order.put(request.dtxsids.get(i), i);
        }
        try {
            List<ExcelDownloadDto> excelDownloadDtoList = excelDownloadService.getAllResults(request.dtxsids);
            List<ExcelDownloadDto> originalOrder = new ArrayList<ExcelDownloadDto>();
            for (String dtxsid:request.dtxsids) {
                    Optional<ExcelDownloadDto> excelDownloadDto = excelDownloadDtoList.stream().filter(ExcelDownloadDto -> ExcelDownloadDto.getDtxsid().equals(dtxsid)).findFirst();
                    if (excelDownloadDto.isPresent()) {
                        originalOrder.add(order.get(dtxsid), excelDownloadDto.get());
                    } else {
                    ExcelDownloadDto excelDownloadDto2 = new ExcelDownloadDto();
                    excelDownloadDto2.setDtxsid(dtxsid);
                    originalOrder.add(order.get(dtxsid), excelDownloadDto2);
                }
            }
            Gson gson = new Gson();
            JsonArray json = gson.toJsonTree(originalOrder).getAsJsonArray();
            String[] fields = new String[] {"id","dtxsid","preferredName","casrn","T0","T4","call"};
            bytes = createExcel2(json,fields);
            String timeStamp = new SimpleDateFormat("yyyy_MM_dd").format(Calendar.getInstance().getTime());
            ContentDisposition contentDisposition = ContentDisposition.builder("inline")
                    .filename("substances_batchquery_" + timeStamp +  "_.xlsx")
                    .build();

            headers.setContentDisposition(contentDisposition);
        } catch ( Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid input");
        }
        return new ResponseEntity<>(new ByteArrayResource(bytes), headers, HttpStatus.OK);
    }

    public byte[] createExcel2(JsonArray ja,String [] fields) {
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        Workbook wb = new XSSFWorkbook();

        // CellStyle styleURL=createStyleURL(wb);
        Sheet sheet = wb.createSheet("Annotations");

        Row row0 = sheet.createRow(0);
        for (int i = 0; i < fields.length; i++) {
            Cell cell = row0.createCell(i);
            cell.setCellValue(fields[i]);
        }
        int rowNum = 1;
        for (int i = 0; i < ja.size(); i++) {
            JsonObject jo=ja.get(i).getAsJsonObject();
            Row row=sheet.createRow(rowNum++);
            for (int k=0;k<fields.length;k++) {
                Cell cell = row.createCell(k);

                if (jo.get(fields[k]) == null) {cell.setCellValue(""); continue;} ;
                if (jo.get(fields[k]).isJsonNull()) {cell.setCellValue(""); continue;};
                if (jo.get(fields[k]).getAsString().isBlank()) {cell.setCellValue(""); continue;}


                String value = jo.get(fields[k]).getAsString();
                try {
                    cell.setCellValue(Double.parseDouble(value));
                } catch (Exception ex) {
                    //						System.out.println("Error setting cell value = "+value+" for "+fields[k]);
                    cell.setCellValue(value);
                }
            }

            }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        OutputStream os = baos;
        try {
            autoSizeColumns(wb);
            wb.write(os);
            wb.close();
        } catch ( IOException ex) {
            ex.printStackTrace();
        }
        byte[] bytes2 = baos.toByteArray();

        return bytes2;
    }

    public void autoSizeColumns(Workbook workbook) {
        int numberOfSheets = workbook.getNumberOfSheets();
        for (int i = 0; i < numberOfSheets; i++) {
            XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(i);
            if (sheet.getPhysicalNumberOfRows() > 0) {
                XSSFRow row = sheet.getRow(sheet.getFirstRowNum());
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    int columnIndex = cell.getColumnIndex();
                    sheet.autoSizeColumn(columnIndex);
                    int currentColumnWidth = sheet.getColumnWidth(columnIndex);
//					sheet.setColumnWidth(columnIndex, (currentColumnWidth + 200));
                }
            }
        }
    }



}



package com.example.demo2.controller;

import com.example.demo2.entity.dto.RevenueDto;
import com.example.demo2.repository.CategoryRepository;
import com.example.demo2.repository.CusRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/admin/statitis")
public class RevenueController {
    @Autowired
    CusRepository cusRepository;
    @Autowired
    CategoryRepository cateRepository;

    @GetMapping("/list")
    public String chart(Model model){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        int y = LocalDate.now().getYear();
        String n = String.valueOf(y) + "-01-01";
        String x = String.valueOf(y) + "-12-31";
        LocalDate t = LocalDate.parse(n, formatter);
        LocalDate m = LocalDate.parse(x, formatter);
        List<Object[]> result = cusRepository.doanhthu(t,m);
        Map<Integer, Long> data = new LinkedHashMap<Integer, Long>();
        for(Object[] i:result){
            data.put((int)i[0],(long)i[1]);
        }
        model.addAttribute("keySet",data.keySet());
        model.addAttribute("values",data.values());
        return "admin/statitis/index";
    }

    @GetMapping("/tron")
    public String chartp(Model model){
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        int y = LocalDate.now().getYear();
//        String n = String.valueOf(y) + "-01-01";
//        String x = String.valueOf(y) + "-12-31";
//        LocalDate t = LocalDate.parse(n, formatter);
//        LocalDate m = LocalDate.parse(x, formatter);
        List<Object[]> result = cateRepository.doanhthu();
        Map<String,Long> data = new LinkedHashMap<String, Long>();
        for(Object[] i:result){
            String k = (String) i[0];
            data.put(k,(long)i[1]);
        }
        model.addAttribute("key",data.keySet());
        model.addAttribute("val",data.values());
        //model.addAttribute("chartData",data);
        return "admin/statitis/index";
    }

    @GetMapping("/tim")
    public String list(Model model, @RequestParam("ngaybd") String nbd,@RequestParam("ngaykt") String nkt){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate t = LocalDate.parse(nbd, formatter);
        LocalDate m = LocalDate.parse(nkt, formatter);
        List<Object[]> result = cusRepository.doanhthu(t,m);
        Map<Integer, Long> data = new LinkedHashMap<Integer, Long>();
        for(Object[] i:result){
            data.put((int)i[0],(long)i[1]);
        }
        model.addAttribute("keySet",data.keySet());
        model.addAttribute("values",data.values());
        return "admin/statitis/index";
    }

    @GetMapping("/export")
    public void exportToExcel (HttpServletResponse response) throws IOException {
        // Tạo workbook và sheet
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        int y = LocalDate.now().getYear();
        String n = String.valueOf(y) + "-01-01";
        String x = String.valueOf(y) + "-12-31";
        LocalDate t = LocalDate.parse(n, formatter);
        LocalDate m = LocalDate.parse(x, formatter);
        List<Object[]> result = cusRepository.doanhthu(t,m);
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Data");
        String[] headers = {"Tháng", "Tổng doanh thu"};

        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        int rowNum = 1;
        for (Object[] rowData : result) {
            Row row = sheet.createRow(rowNum++);
            for (int i = 0; i < rowData.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(rowData[i].toString());
            }
        }

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=doanhthu.xlsx");

        // Ghi workbook vào response stream
        workbook.write(response.getOutputStream());

        // Đóng workbook
        workbook.close();
    }
}

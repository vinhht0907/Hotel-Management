package com.example.demo2.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.YearMonth;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RevenueDto {
    private YearMonth year;
    private long roomprice,serprice,totalprice;
}

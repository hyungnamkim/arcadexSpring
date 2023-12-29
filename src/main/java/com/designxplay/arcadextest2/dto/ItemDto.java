package com.designxplay.arcadextest2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemDto {
    private String name;
    private String description;
    private int count; // Player가 가진 해당 아이템의 수량
}

package com.designxplay.arcadextest2.dto;

import com.designxplay.arcadextest2.entity.Item;
import lombok.Data;

@Data
public class ItemForm {
    private Long id;
    private String name;
    private String description;

    public Item toEntity(){
        return new Item(null, name, description);
    }
}

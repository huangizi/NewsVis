package com.freya.onlinetech.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class School {
    private int school_id;
    private String school_name;
    private String school_introduction;
    private String school_img_name_href;
    private String school_logo_href;
}

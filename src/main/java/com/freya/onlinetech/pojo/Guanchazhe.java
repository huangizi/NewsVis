package com.freya.onlinetech.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Guanchazhe {
    int id;
    String title;
    String author;
    Date  publish_time;
    String content;
    String url;
    String key_word;
}

package com.freya.onlinetech.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentInfo {
    int id;
    String username;
    String password;
    String email;
    String phoneNumber;
    String schoolName;
    String major;
}

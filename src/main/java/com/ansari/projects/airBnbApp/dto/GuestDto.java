package com.ansari.projects.airBnbApp.dto;

import com.ansari.projects.airBnbApp.entity.User;
import lombok.Data;

@Data
public class GuestDto {

    private Long id;
    private User user;
    private String name;
    private String gender;
    private Integer age;

}

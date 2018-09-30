package com.example.demo.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Document(collection = "user")
@Data //getter setter 构造器 tostring hashcode等注解集合
public class User {

    @Id
    private String id;
    @NotBlank
    private String name;
    @Max(150)@Min(1)
    private Integer age;
    @JSONField(format = "yyyy-MM-dd")
    private LocalDate birthday;

}

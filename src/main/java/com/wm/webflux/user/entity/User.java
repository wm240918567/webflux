package com.wm.webflux.user.entity;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

/**
 * user实体bean
 * @author wm
 * @since 2018/11/9 16:15
 * @version 1.0
 */

//映射到mongodb表名
@Document(collection = "user")
@Data

public class User {

    /**
     * 无意义自增主键
     */
    @Id
    private String id;

    /**
     * 姓名
     * 不能为空
     */
    @NotBlank
    private String name;

    /**
     * 年龄
     * between 0 and 150
     */
    @Max(150)@Min(0)
    private Integer age;

    /**
     * 生日
     * JDK8 提供java.time.LocalDate;非旧java.util.Date
     */
    @JSONField(format = "yyyy-MM-dd")
    private LocalDate birthday;

    /**
     * fastjson 打印
     * @return json格式字符串
     */
    @Override
    public String toString() {
        return JSONObject.toJSONString(this, true);
    }
}

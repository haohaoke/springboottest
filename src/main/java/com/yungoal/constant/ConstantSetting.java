package com.yungoal.constant;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "constant")
public class ConstantSetting {
    private String company;
    private String address;

}
package com.edu.batteryservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BatteryQuery {
    private  String entry_person_name;//录入人姓名
    @ApiModelProperty(value = "电池运行状态 1运行 0不运行")
    private String operation_status;
//    @ApiModelProperty(value = "最后操作时间", example = "2019-01-01 10:10:10")
//    private String  last_exchange_time;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换

}

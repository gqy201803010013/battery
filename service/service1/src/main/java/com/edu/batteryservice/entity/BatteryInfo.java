package com.edu.batteryservice.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author testjava
 * @since 2024-08-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="BatteryInfo对象", description="")
public class BatteryInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "battery_code", type = IdType.ID_WORKER_STR)
    private String batteryCode;

    private String onlineStatus;

    private BigDecimal remainingPower;

    private String operationStatus;

    private String operationSubStatus;

    private String lastBatteryCabinet;

    private String lastWarehouse;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date lastExchangeTime;

    private String supplierName;

    private String warehouseName;

    private String statusChangeReason;

    private Integer repairCount;

    private String repairContent;

    private String scrapReason;

    private String suspectedLossReason;

    private String faultReason;

    private String entryPersonName;

    private String lastCabinet;

    private Integer lastWarehouseId;

    private String batteryVersion;


}

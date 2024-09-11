package com.edu.batteryservice.service;

import com.edu.batteryservice.entity.BatteryInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2024-08-14
 */
public interface BatteryInfoService extends IService<BatteryInfo> {

    void saveInfo(MultipartFile file, BatteryInfoService batteryInfoService);
}

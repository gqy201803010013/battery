package com.edu.batteryservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.batteryservice.entity.BatteryInfo;
import com.edu.batteryservice.entity.vo.BatteryQuery;
import com.edu.batteryservice.service.BatteryInfoService;
import com.edu.commonutils.R;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2024-08-14
 */
@RestController
@RequestMapping("/batteryservice/battery-info")
@CrossOrigin(origins = "http://localhost:9525", allowCredentials = "true")
public class BatteryInfoController {
    @Autowired
    private  BatteryInfoService batteryInfoService;

    @ApiOperation(value = "查询所有数据")
    @GetMapping("findAll")
    public R findAllTeacher(){
        List<BatteryInfo> list = batteryInfoService.list(null);
        return R.ok().data("items",list);
    }
    @PostMapping("addBatteryInfo")
    public R addBatteryInfo(MultipartFile file){
        batteryInfoService.saveInfo(file,batteryInfoService);
        return R.ok();
    }
//    //分页查询
//    @ApiOperation(value = "分页讲师列表")
//    @GetMapping("pageList/{page}/{limit}")
//    public R pageList(
//            @ApiParam(name = "page", value = "当前页码", required = true)
//            @PathVariable Long page,
//            @ApiParam(name = "limit", value = "每页记录数", required = true)
//            @PathVariable Long limit){
//        Page<BatteryInfo> pageParam = new Page<>(page, limit);
//        batteryInfoService.page(pageParam, null);
//        List<BatteryInfo> records = pageParam.getRecords(); //数据list集合
//        long total = pageParam.getTotal(); //总记录数
//        return R.ok().data("total", total).data("rows", records);
//    }

    //分页条件查询
    @PostMapping("pageBatteryCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable long current, @PathVariable long limit, @RequestBody(required = false) BatteryQuery batteryQuery){
        Page<BatteryInfo> pageBattery = new Page<>(current,limit);
        QueryWrapper<BatteryInfo> wrapper = new QueryWrapper<>();

        String  entry_person_name = batteryQuery.getEntry_person_name();
        String operation_status = batteryQuery.getOperation_status();

        if (!StringUtils.isEmpty(entry_person_name)) {
            wrapper.like("entry_person_name", entry_person_name);
        }
        if (!StringUtils.isEmpty(operation_status) ) {
            wrapper.eq("operation_status", operation_status);
        }

        //调用方法实现条件查询分页
        batteryInfoService.page(pageBattery,wrapper);

        List<BatteryInfo> records = pageBattery.getRecords(); //数据list集合
        long total = pageBattery.getTotal(); //总记录数
        return R.ok().data("total", total).data("rows", records);

    }

    @ApiOperation(value = "根据BatteryCode修改讲师")
    @PutMapping("{battery_code}")
    public R updateById(
            @ApiParam(name = "battery_code", value = "电池编码", required = true)
            @PathVariable String battery_code,
            @ApiParam(name = "batteryInfo", value = "电池信息", required = true)
            @RequestBody BatteryInfo batteryInfo){
               batteryInfo.setBatteryCode(battery_code);
               batteryInfoService.updateById(batteryInfo);
               return R.ok();
         }


}


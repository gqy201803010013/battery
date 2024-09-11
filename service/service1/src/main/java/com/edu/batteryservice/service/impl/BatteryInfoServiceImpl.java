package com.edu.batteryservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.batteryservice.entity.BatteryInfo;
import com.edu.batteryservice.mapper.BatteryInfoMapper;
import com.edu.batteryservice.service.BatteryInfoService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BatteryInfoServiceImpl extends ServiceImpl<BatteryInfoMapper, BatteryInfo> implements BatteryInfoService {

    @Override
    public void saveInfo(MultipartFile file, BatteryInfoService batteryInfoService) {
        List<BatteryInfo> batteryInfoList = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0);
            int numberOfRows = sheet.getPhysicalNumberOfRows();

            for (int i = 1; i < numberOfRows; i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                BatteryInfo batteryInfo = new BatteryInfo();
                batteryInfo.setBatteryCode(getCellValue(row.getCell(0)));
                batteryInfo.setOnlineStatus(getCellValue(row.getCell(1)));
                batteryInfo.setRemainingPower(parseBigDecimal(getCellValue(row.getCell(2))));
                batteryInfo.setOperationStatus(getCellValue(row.getCell(3)));
                batteryInfo.setOperationSubStatus(getCellValue(row.getCell(4)));
                batteryInfo.setLastBatteryCabinet(getCellValue(row.getCell(5)));
                batteryInfo.setLastWarehouse(getCellValue(row.getCell(6)));
                batteryInfo.setLastExchangeTime(parseDate(getCellValue(row.getCell(7)), dateFormat));
                batteryInfo.setSupplierName(getCellValue(row.getCell(8)));
                batteryInfo.setWarehouseName(getCellValue(row.getCell(9)));
                batteryInfo.setStatusChangeReason(getCellValue(row.getCell(10)));
                batteryInfo.setRepairCount(parseInteger(getCellValue(row.getCell(11))));
                batteryInfo.setRepairContent(getCellValue(row.getCell(12)));
                batteryInfo.setScrapReason(getCellValue(row.getCell(13)));
                batteryInfo.setSuspectedLossReason(getCellValue(row.getCell(14)));
                batteryInfo.setFaultReason(getCellValue(row.getCell(15)));
                batteryInfo.setEntryPersonName(getCellValue(row.getCell(16)));
                batteryInfo.setLastCabinet(getCellValue(row.getCell(17)));
                batteryInfo.setLastWarehouseId(parseInteger(getCellValue(row.getCell(18))));
                batteryInfo.setBatteryVersion(getCellValue(row.getCell(19)));

                batteryInfoList.add(batteryInfo);
            }

            // Save the list of BatteryInfo objects to the database
            if (!batteryInfoList.isEmpty()) {
                this.saveBatch(batteryInfoList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getCellValue(Cell cell) {
        if (cell == null) return null;
        if (cell.getCellType() == CellType.NUMERIC) {
            if (DateUtil.isCellDateFormatted(cell)) {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cell.getDateCellValue());
            } else {
                return String.valueOf(cell.getNumericCellValue());
            }
        } else if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue();
        }
        return null;
    }

    private BigDecimal parseBigDecimal(String value) {
        try {
            return value != null ? new BigDecimal(value) : null;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Integer parseInteger(String value) {
        try {
            return value != null ? Integer.parseInt(value) : null;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Date parseDate(String value, SimpleDateFormat dateFormat) {
        try {
            return value != null ? dateFormat.parse(value) : null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

package com.xulihuazj.pms.utils.excel;

import com.xulihuazj.pms.utils.log.LogHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;


/**
 * excel操作工具类
 */
public class ExcelUtil {

    private static final Logger logger = LogManager.getLogger(ExcelUtil.class);

    private static final String xls = "xls";

    private static final String xlsx = "xlsx";

    public static Workbook getWorkbook(MultipartFile file) throws IOException {
        // 获取excel文件名
        String fileName = file.getOriginalFilename();
        Workbook workbook = null;
        InputStream inputStream = null;
        try {
            // 获取excel文件的IO流
            inputStream = file.getInputStream();
            // 如果 文件后缀名 不同，获得不同的Workbook实现类对象
            if (fileName.endsWith(xls)) {
                //2003
                workbook = new HSSFWorkbook(inputStream);
            } else if (fileName.endsWith(xlsx)) {
                // 2007
                workbook = new XSSFWorkbook(inputStream);
            }
        } catch (Exception ex) {
            LogHelper.exception(ex, logger, "获取Workbook对象异常！");
        } finally {
            if (null != inputStream) {
                inputStream.close();
            }
        }
        return workbook;
    }

    /**
     * 获取Excel单元格
     *
     * @param cell
     * @return
     */
    public static String getCellValue(Cell cell) {
        String cellValue = "";
        if (null == cell) {
            return cellValue;
        }
        // 将数字转换成String处理，避免出现1读成1.0的情况
        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            cell.setCellType(Cell.CELL_TYPE_STRING);
        }
        // 具体类型
        switch (cell.getCellType()) {
            //数字
            case Cell.CELL_TYPE_NUMERIC:
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            // 字符串
            case Cell.CELL_TYPE_STRING:
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            // Boolean值
            case Cell.CELL_TYPE_BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            // 公式
            case Cell.CELL_TYPE_FORMULA:
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            // 空值
            case Cell.CELL_TYPE_BLANK:
                cellValue = "";
                break;
            case Cell.CELL_TYPE_ERROR:
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知字符";
                break;
        }
        return cellValue;
    }

}

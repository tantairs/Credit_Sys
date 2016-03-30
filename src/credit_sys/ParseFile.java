/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package credit_sys;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Melons
 */
public class ParseFile {

    public static boolean importData(String file_path, short sign) {
        String type = getType(file_path);
        switch (type.toLowerCase()) {
            case "xls":{
                if(sign == 0 || sign ==2) return readXLSPop(file_path, sign);
                else return readXLS(file_path, sign);
            }
            case "xlsx":{
                if(sign == 0 || sign ==2) return readXLSXPop(file_path, sign);
                else return readXLSX(file_path, sign);
            }
        }
        return false;
    }

    private static boolean readXLSX(String file_path, short sign) {
        int rows = 0;
        Calendar cal = Calendar.getInstance();
        java.util.Date date = cal.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        String myTime = timeFormat.format(date);
        String myDate = dateFormat.format(date);
        try {
            InputStream is = new FileInputStream(file_path);
            XSSFWorkbook wb = new XSSFWorkbook(is);
            XSSFSheet sheet = wb.getSheetAt(0);
            ArrayList<String> tmp_info = new ArrayList<>();
            int rowNum = sheet.getLastRowNum();
            int colNum = sheet.getRow(0).getPhysicalNumberOfCells();
            rows = rowNum - 1;
            XSSFRow row;

            for (int i = 1; i <= rowNum; i++) {
                row = sheet.getRow(i);
                for (int j = 0; j < colNum; j++) {
                    XSSFCell cell = row.getCell(j);
                    double num;
                    long tp;
                    if (XSSFCell.CELL_TYPE_NUMERIC == cell.getCellType()) {
                        num = cell.getNumericCellValue();
                        tp = (long) num;
                        tmp_info.add(String.valueOf(tp));
                    } else {
                        tmp_info.add(cell.toString());
                    }
                }
                ImportToDB.importData(tmp_info, sign, myDate, myTime);
                tmp_info.clear();
            }

        } catch (FileNotFoundException ex) {
            return false;
        } catch (IOException ex) {
            return false;
        }
        Log.importLog(sign, rows, myDate, myTime);
        return true;
    }
    
    private static boolean readXLSXPop(String file_path, short sign) {
        int rows = 0;
        Calendar cal = Calendar.getInstance();
        java.util.Date date = cal.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        String myTime = timeFormat.format(date);
        String myDate = dateFormat.format(date);
        try {
            InputStream is = new FileInputStream(file_path);
            XSSFWorkbook wb = new XSSFWorkbook(is);
            XSSFSheet sheet = wb.getSheetAt(0);
            ArrayList<String> tmp_info = new ArrayList<>();
            int rowNum = sheet.getLastRowNum();
            int colNum = sheet.getRow(0).getPhysicalNumberOfCells();
            rows = rowNum - 1;
            XSSFRow row;

            for (int i = 2; i <= rowNum; i++) {
                row = sheet.getRow(i);
                for (int j = 0; j < colNum; j++) {
                    XSSFCell cell = row.getCell(j);
                    double num;
                    long tp;
                    if (XSSFCell.CELL_TYPE_NUMERIC == cell.getCellType()) {
                        num = cell.getNumericCellValue();
                        tp = (long) num;
                        tmp_info.add(String.valueOf(tp));
                    } else {
                        tmp_info.add(cell.toString());
                    }
                }
                ImportToDB.importData(tmp_info, sign, myDate, myTime);
                tmp_info.clear();
            }

        } catch (FileNotFoundException ex) {
            return false;
        } catch (IOException ex) {
            return false;
        }
        Log.importLog(sign, rows, myDate, myTime);
        return true;
    }

    private static boolean readXLS(String file_path, short sign) {
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        String myTime = timeFormat.format(date);
        String myDate = dateFormat.format(date);
        int totalRows = 0;
        try {
            File file = new File(file_path);
            Workbook wb = Workbook.getWorkbook(file);
            Sheet sheet = wb.getSheet(0);
            ArrayList<String> tmp_info = new ArrayList<>();

            totalRows = sheet.getRows() - 1;
            for (int i = 1; i < sheet.getRows(); i++) {
                for (int j = 0; j < sheet.getColumns(); j++) {
                    Cell cell = sheet.getCell(j, i);
                    tmp_info.add(cell.getContents());
                }
                ImportToDB.importData(tmp_info, sign, myDate, myTime);
                tmp_info.clear();
            }
        } catch (IOException | BiffException ex) {
            return false;
        }
        Log.importLog(sign, totalRows, myDate, myTime);
        return true;
    }
    private static boolean readXLSPop(String file_path, short sign) {
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        String myTime = timeFormat.format(date);
        String myDate = dateFormat.format(date);
        int totalRows = 0;
        try {
            File file = new File(file_path);
            Workbook wb = Workbook.getWorkbook(file);
            Sheet sheet = wb.getSheet(0);
            ArrayList<String> tmp_info = new ArrayList<>();

            totalRows = sheet.getRows() - 1;
            for (int i = 2; i < sheet.getRows(); i++) {
                for (int j = 0; j < sheet.getColumns(); j++) {
                    Cell cell = sheet.getCell(j, i);
                    tmp_info.add(cell.getContents());
                }
                ImportToDB.importData(tmp_info, sign, myDate, myTime);
                tmp_info.clear();
            }
        } catch (IOException | BiffException ex) {
            return false;
        }
        Log.importLog(sign, totalRows, myDate, myTime);
        return true;
    }

    private static String getType(String file_path) {
        String file_name;
        String type = null;
        if ((file_path != null) && (file_path.length() > 0)) {
            int dot = file_path.lastIndexOf('/');
            file_name = file_path.substring(dot + 1);
            dot = file_name.lastIndexOf('.');
            type = file_name.substring(dot + 1);
        }
        return type;
    }
}

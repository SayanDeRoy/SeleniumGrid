package utilities;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtilities {

    private String path;
    private String sheetName;
    private String testCaseName;
    private int testDataRowCount = 0;
    private int testDataColumnCount = 0;
    public FileInputStream fis = null;
    public FileOutputStream fos = null;
    public XSSFWorkbook workbook = null;
    public XSSFSheet sheet = null;
    public XSSFRow row = null;
    private XSSFCell cell = null;

    public ExcelUtilities(String path, String sheetName, String testCaseName) {
        this.path = path;
        this.sheetName = sheetName;
        this.testCaseName = testCaseName;

        try {
            fis = new FileInputStream(this.path);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(this.sheetName);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getTestDataRowCount() {
        int totalRowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
        for (int i = 1; i <= totalRowCount; i++) {
            String testCaseName = sheet.getRow(i).getCell(0).getStringCellValue();
            if (this.testCaseName.equals(testCaseName)) {
                row = sheet.getRow(i);
                testDataRowCount = 1;

                while (i != totalRowCount && sheet.getRow(i + 1).getCell(0).getStringCellValue().equals("")) {
                    testDataRowCount++;
                    i++;
                }
                break;

            }
        }
        return testDataRowCount;
    }

    public int getTestDataColumnCount() {
        testDataColumnCount = row.getLastCellNum() - row.getFirstCellNum();
        return testDataColumnCount;
    }

    public String getCellData(int rowNum, int colNum) {

        CellType cellType = sheet.getRow(rowNum).getCell(colNum).getCellType();

        if (cellType.name().equals("STRING")) {
            return sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
        } else if (cellType.name().equals("NUMERIC")) {
            return sheet.getRow(rowNum).getCell(colNum).getNumericCellValue() + "";
        }
        return "";
    }

    public String getRunMode(int rowNum, int colNum) {
        return sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
    }

    public synchronized void updateStatusInExcelSheet(int rowNum, String value) {
        int lastCellNumber = sheet.getRow(rowNum).getLastCellNum();
        sheet.getRow(rowNum).getCell(lastCellNumber-1).setCellValue(value);
        try {
            fos = new FileOutputStream(Constants.excelPath);
            workbook.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

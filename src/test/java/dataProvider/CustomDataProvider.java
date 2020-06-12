package dataProvider;

import org.testng.annotations.DataProvider;
import utilities.Constants;
import utilities.ExcelUtilities;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Hashtable;

public class CustomDataProvider {

    @DataProvider(name = "Generic", parallel = true)
    public synchronized Object[][] getData(Class c, Method m) {
        String testCaseName = m.getName();
        String[] sheetNameArray = c.getName().split("[.]");
        String sheetName = sheetNameArray[sheetNameArray.length - 1];
        ExcelUtilities eu = new ExcelUtilities(Constants.excelPath, sheetName, testCaseName);

        int totalRowData = eu.getTestDataRowCount();
        int totalColumnData = eu.getTestDataColumnCount();
        int presentTestCaseRowNumber = eu.row.getRowNum();
        /*int totalExecutableData = 0;

        for (int i = 0; i < totalRowData; i++) {
            for (int j = 1; j < totalColumnData; j++) {
                String columnName = eu.sheet.getRow(0).getCell(j).getStringCellValue();
                if (columnName.equalsIgnoreCase("RunMode")) {
                    String runMode = eu.getCellData(presentTestCaseRowNumber, j);
                    if (runMode.equalsIgnoreCase("Yes")) {
                        totalExecutableData++;
                        break;
                    }
                }
            }
            presentTestCaseRowNumber++;
        }

        presentTestCaseRowNumber = eu.row.getRowNum();*/

        Object[][] data = new Object[totalRowData][1];


        for (int i = 0; i < totalRowData; i++) {
            Hashtable<String, String> table = new Hashtable<>();
            for (int j = 1; j < totalColumnData; j++) {
                String key = eu.getCellData(0, j);
                /*if(key.equals("Status")) {
                    eu.updateExcelSheet(presentTestCaseRowNumber, j, "");
                }*/
                String value = eu.getCellData(presentTestCaseRowNumber, j);
                if (!value.equals("")) {
                    table.put(key, value);
                }
            }
            table.put("TestCaseRowNumber", Integer.toString(presentTestCaseRowNumber));
            data[i][0] = table;
            /*if (table.get("RunMode").equalsIgnoreCase("Yes")) {
                data[i][0] = table;
            }*/

            presentTestCaseRowNumber++;
        }
        try {
            //eu.fis.close();
            eu.workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}

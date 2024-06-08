package simtran.core.utils;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;

/**
 * This class reads data from an Excel spreadsheet (.xlsx format) and provides methods
 * to retrieve specific cell values or a two-dimensional array representing the entire table.
 *
 * @author simtran
 */
public class ExcelReader {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private XSSFRow row = null;

    /**
     * Constructs a new ExcelReader instance by loading the Excel file located at the specified resource path.
     */
    public ExcelReader(String filePath) {
        InputStream ins = getClass().getResourceAsStream(filePath);
        try {

            workbook = new XSSFWorkbook(ins);
            sheet = workbook.getSheetAt(0);
            ins.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the formatted value of a cell from the specified sheet, row, and column.
     *
     * @param sheetName The name of the Excel sheet containing the cell.
     * @param rowNum    The row number (zero-based) of the cell.
     * @param colNum    The column number (zero-based) of the cell.
     * @return The formatted value of the cell as an Object.
     */
    public Object getCellData(String sheetName, int rowNum, int colNum) {
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rowNum);
        XSSFCell cell = row.getCell(colNum);

        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell);
    }

    /**
     * Reads an entire table from the specified sheet and returns a two-dimensional array
     * representing the table data. The first row is considered the header row.
     *
     * @param sheetName The name of the Excel sheet containing the table.
     * @return A two-dimensional array of Objects representing the table data.
     */
    public Object[][] getTableArray(String sheetName) {
        sheet = workbook.getSheet(sheetName);
        int firstRow = sheet.getFirstRowNum() + 1;
        int lastRow = sheet.getLastRowNum();
        int totalRows = lastRow - firstRow + 1;

        row = sheet.getRow(sheet.getFirstRowNum());
        int firstColumn = row.getFirstCellNum();
        int lastColumn = row.getLastCellNum();
        int totalCols = lastColumn - firstColumn;

        int ci, cj;
        Object[][] tabArray = new Object[totalRows][totalCols];
        ci = 0;
        for (int i = firstRow; i <= lastRow; i++, ci++) {
            cj = 0;
            for (int j = firstColumn; j < lastColumn; j++, cj++) {
                tabArray[ci][cj] = getCellData(sheetName, i, j);
            }
        }
        return (tabArray);
    }
}

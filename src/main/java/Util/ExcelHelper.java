package Util;

import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ExcelHelper {

    private int intRowNum;
    private int intColNum;

    private Object[][] arrExcelData;
    private Object[] arrCellData;

    private Workbook objWorkbook;
    private Sheet objWorkSheet;
    private Row objRow;
    private Cell objCell;

    private DefaultTableModel dtTableModel;
    private JTable jTable;

    private synchronized Sheet getExcelWorkBookSheet(String strFilePath, String strSheetName) {

        try (FileInputStream objFile = new FileInputStream(strFilePath)) {

            objWorkbook = null;
            objWorkSheet = null;

            if (strFilePath.lastIndexOf(".") != -1) {

                if (strFilePath.substring(strFilePath.lastIndexOf(".")).equals(".xls")) {

                    objWorkbook = new HSSFWorkbook(objFile);

                } else if (strFilePath.substring(strFilePath.lastIndexOf(".")).equals(".xlsx")) {

                    objWorkbook = new XSSFWorkbook(objFile);
                }
            }

            if (objWorkbook != null) {

                objWorkSheet = objWorkbook.getSheet(strSheetName);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return objWorkSheet;
    }

    public Object[][] readDataFromExcel(String strFilePath, String strSheetName) {

        try {

            objWorkSheet = getExcelWorkBookSheet(strFilePath, strSheetName);

            if (objWorkSheet != null) {

                objRow = objWorkSheet.getRow(0);

                intRowNum = objWorkSheet.getPhysicalNumberOfRows() - 1;
                intColNum = objRow.getLastCellNum();

                arrExcelData = new Object[intRowNum][intColNum];

                for (int rowCnt = 0; rowCnt < intRowNum; rowCnt++) {
                    objRow = objWorkSheet.getRow(rowCnt + 1);

                    for (int colCnt = 0; colCnt < intColNum; colCnt++) {
                        if (objRow == null) {
                            arrExcelData[rowCnt][colCnt] = "";
                        } else {

                            objCell = objRow.getCell(colCnt);
                            objCell.setCellType(CellType.STRING);

                            if (objCell == null) {
                                arrExcelData[rowCnt][colCnt] = "";
                            } else {
                                arrExcelData[rowCnt][colCnt] = objCell.getStringCellValue();
                            }
                        }
                    }
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return arrExcelData;


    }

    public JTable convertExcelToDataTable(String strFilePath, String strSheetName) {

        try {

            jTable = null;

            objWorkSheet = getExcelWorkBookSheet(strFilePath, strSheetName);

            if (objWorkSheet != null) {

                objRow = objWorkSheet.getRow(0);

                intRowNum = objWorkSheet.getPhysicalNumberOfRows();
                intColNum = objRow.getLastCellNum();

                arrCellData = new Object[intColNum];

                dtTableModel = new DefaultTableModel();
                jTable = new JTable(dtTableModel);

                for (int rowCnt = 0; rowCnt < intRowNum; rowCnt++) {

                    objRow = objWorkSheet.getRow(rowCnt);

                    for (int colCnt = 0; colCnt < intColNum; colCnt++) {

                        if (objRow != null) {

                            objCell = objRow.getCell(colCnt);
                            objCell.setCellType(CellType.STRING);

                            if (objCell != null) {

                                if (rowCnt == 0) {

                                    dtTableModel.addColumn(objCell.getStringCellValue());

                                } else {

                                    arrCellData[colCnt] = objCell.getStringCellValue();
                                }
                            }
                        }
                    }

                    if (rowCnt > 0)

                        dtTableModel.addRow(arrCellData);
                }

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return jTable;
    }
}



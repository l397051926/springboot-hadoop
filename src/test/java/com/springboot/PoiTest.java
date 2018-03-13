package com.springboot;

import com.microsoft.schemas.office.visio.x2012.main.CellType;
import org.apache.jasper.tagplugins.jstl.core.If;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.NPOIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.*;
import java.util.Calendar;
import java.util.Date;

/**
 * @author liumingxin
 * @create 2018 24 10:21
 * @desc  study poi test
 **/
public class PoiTest {
    @Test
    public void startTest(){
        System.out.println("study start");
    }

    @Test//创建一个xls的工作Excel
    public void creatDemo1() throws IOException {
        //创建一个工作博
        Workbook wb = new HSSFWorkbook();
        FileOutputStream fileOut = new FileOutputStream("workbook.xls");
        wb.write(fileOut);
        fileOut.close();
    }

    @Test//创建一个xlsx的Excel
    public void creatDemo2() throws IOException {

        //创建一个工作博
        Workbook wb = new XSSFWorkbook();
        FileOutputStream fileOut = new FileOutputStream("workbook.xlsx");
        wb.write(fileOut);
        fileOut.close();
    }

    /**
     * 创建一个表格  注意表格内容不能超过31个字符 内容 部分标点符号也不可以使用
     * @throws IOException
     */
    @Test
    public void creatSheel() throws IOException {
        Workbook wb =new HSSFWorkbook();
        Sheet sheet1=wb.createSheet("new sheet");
        Sheet sheet2 = wb.createSheet("second sheet");

        String safeName= WorkbookUtil.createSafeSheetName("[O'Brien's sales*?]");
        Sheet sheet3 = wb.createSheet(safeName);

        FileOutputStream fileOutputStream=new FileOutputStream("workbook.xls");
        wb.write(fileOutputStream);
        fileOutputStream.close();
    }

    /**
     * 创建单元格？
     * @throws IOException
     */
    @Test
    public void creatCell() throws IOException {
        Workbook wb = new HSSFWorkbook();
        CreationHelper createHelper = wb.getCreationHelper();
        Sheet sheet = wb.createSheet("new sheet");
        //定义竖为0
        Row row = sheet.createRow(0);
        //当前行第一个单元格位置
        Cell cell = row.createCell(0);
        cell.setCellValue(1);

        // 当前单元格 第二个单元位置
//        row.createCell(1).setCellValue(1.2);
        row.createCell(2).setCellValue(
                //插入字符串
                createHelper.createRichTextString("This is a string"));
        row.createCell(3).setCellValue(true);

        // 写出到文件
        FileOutputStream fileOut = new FileOutputStream("workbook.xls");
        wb.write(fileOut);
        fileOut.close();
    }

    @Test
    /**
     * 测试 输出内容 有些麻烦 得用循环或者迭代操作吧  CreationHelper 没看懂干嘛  不需要他也可以插入字符串
     */
    public void creatCell2() throws IOException {
        Workbook wb = new HSSFWorkbook();
        CreationHelper creationHelper=wb.getCreationHelper();
        Sheet sheet1=wb.createSheet("new sheet");

        //定义第一行
        Row row = sheet1.createRow(0);
        row.createCell(0).setCellValue("姓名");
        row.createCell(1).setCellValue("年龄");
        row.createCell(2).setCellValue("地址");

        Row row1 = sheet1.createRow(1);
        row1.createCell(0).setCellValue("zs");
        row1.createCell(1).setCellValue(12);
        row1.createCell(2).setCellValue("bj");

        Row row2 = sheet1.createRow(2);
        row2.createCell(0).setCellValue("ls");
        row2.createCell(1).setCellValue(13);
        row2.createCell(2).setCellValue("sh");


        FileOutputStream fileOutputStream=new FileOutputStream("workbook.xls");
        wb.write(fileOutputStream);
        fileOutputStream.close();
    }

    @Test
    /**
     * 创建日期格式的 单元格
     */
    public void createDateCell() throws IOException {
        Workbook wb = new HSSFWorkbook();

        CreationHelper createHelper = wb.getCreationHelper();
        Sheet sheet = wb.createSheet("new sheet");

        // Create a row and put some cells in it. Rows are 0 based.
        Row row = sheet.createRow(0);

        // Create a cell and put a date value in it.  The first cell is not styled
        // as a date.
        Cell cell = row.createCell(0);
        cell.setCellValue(new Date());

        // we style the second cell as a date (and time).  It is important to
        // create a new cell style from the workbook otherwise you can end up
        // modifying the built in style and effecting not only this cell but other cells.
        //定义一个 单元格样式
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setDataFormat(
                //使用日期格式化
                createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
        cell = row.createCell(1);
        cell.setCellValue(new Date());
        cell.setCellStyle(cellStyle);

        //you can also set date as java.util.Calendar  也可以直接使用 Calendar格式
        cell = row.createCell(2);
        cell.setCellValue(Calendar.getInstance());
        cell.setCellStyle(cellStyle);

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("workbook.xls");
        wb.write(fileOut);
        fileOut.close();
    }

    /**
     * 创建不同个格式的单元格
     * @throws IOException
     */
    @Test
    public void creatDifferentTypes() throws IOException {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("new sheet");
        Row row = sheet.createRow(2);
        row.createCell(0).setCellValue(1.1);
        row.createCell(1).setCellValue(new Date());
        row.createCell(2).setCellValue(Calendar.getInstance());
        row.createCell(3).setCellValue("a string");
        row.createCell(4).setCellValue(true);
        row.createCell(5).setCellType(CellType.EQUAL);

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("differentType.xls");
        wb.write(fileOut);
        fileOut.close();
    }

    /**
     * 读取 Excel 表格内容
     * @throws IOException
     * @throws InvalidFormatException
     */
    @Test
    public void openExcel() throws IOException, InvalidFormatException {
        // Use a file
//        Workbook wb = WorkbookFactory.create(new File("workbook.xls"));
//
//        // Use an InputStream, needs more memory
//        Workbook wb = WorkbookFactory.create(new FileInputStream("workbook.xls"));
//
//        // HSSFWorkbook, File
//        NPOIFSFileSystem fs = new NPOIFSFileSystem(new File("file.xls"));
//        HSSFWorkbook wb = new HSSFWorkbook(fs.getRoot(), true);
//
//        fs.close();
//
//        // HSSFWorkbook, InputStream, needs more memory
//        NPOIFSFileSystem fs = new NPOIFSFileSystem(myInputStream);
//        HSSFWorkbook wb = new HSSFWorkbook(fs.getRoot(), true);
//
//        // XSSFWorkbook, File
//        OPCPackage pkg = OPCPackage.open(new File("file.xlsx"));
//        XSSFWorkbook wb = new XSSFWorkbook(pkg);
//
//        pkg.close();
//
//        // XSSFWorkbook, InputStream, needs more memory
//        OPCPackage pkg = OPCPackage.open(myInputStream);
//        XSSFWorkbook wb = new XSSFWorkbook(pkg);
//
//        pkg.close();
    }


    /**
     * 演示各种对其功能
     * @param args
     * @throws Exception
     */
    public static void main(String[] args)  throws Exception {
        Workbook wb = new XSSFWorkbook(); //or new HSSFWorkbook();

        Sheet sheet = wb.createSheet();
        Row row = sheet.createRow(2);
        row.setHeightInPoints(30);

        createCell(wb, row, 0, HorizontalAlignment.CENTER, VerticalAlignment.BOTTOM);
        createCell(wb, row, 1, HorizontalAlignment.CENTER_SELECTION, VerticalAlignment.BOTTOM);
        createCell(wb, row, 2, HorizontalAlignment.FILL, VerticalAlignment.CENTER);
        createCell(wb, row, 3, HorizontalAlignment.GENERAL, VerticalAlignment.CENTER);
        createCell(wb, row, 4, HorizontalAlignment.JUSTIFY, VerticalAlignment.JUSTIFY);
        createCell(wb, row, 5, HorizontalAlignment.LEFT, VerticalAlignment.TOP);
        createCell(wb, row, 6, HorizontalAlignment.RIGHT, VerticalAlignment.TOP);

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("xssf-align.xlsx");
        wb.write(fileOut);
        fileOut.close();

    }

    /**
     * Creates a cell and aligns it a certain way.
     *
     * @param wb     the workbook
     * @param row    the row to create the cell in
     * @param column the column number to create the cell in
     * @param halign the horizontal alignment for the cell.
     * @param valign the vertical alignment for the cell.
     */
    private static void createCell(Workbook wb, Row row, int column, HorizontalAlignment halign, VerticalAlignment valign) {
        Cell cell = row.createCell(column);
        cell.setCellValue("Align It");
        CellStyle cellStyle = wb.createCellStyle();
        //水平对其
//        cellStyle.setAlignment(halign);
        //垂直对齐
//        cellStyle.setVerticalAlignment(valign);
        cell.setCellStyle(cellStyle);
    }

    /**
     * 边框？
     * @throws IOException
     */
    @Test
    public void creatborders() throws IOException {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("new sheet");

        // Create a row and put some cells in it. Rows are 0 based.
        Row row = sheet.createRow(1);

        // Create a cell and put a value in it.
        Cell cell = row.createCell(1);
        cell.setCellValue(4);

        // Style the cell with borders all around.
        CellStyle style = wb.createCellStyle();
        //数字可能和类型有关
        style.setBorderBottom((short) 10);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft((short) 10);
        style.setLeftBorderColor(IndexedColors.GREEN.getIndex());
        style.setBorderRight((short) 10);
        style.setRightBorderColor(IndexedColors.BLUE.getIndex());
        style.setBorderTop((short) 10);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        cell.setCellStyle(style);

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("borders.xls");
        wb.write(fileOut);
        fileOut.close();
    }

    /**
     * 读取表格中的内容
     * @throws IOException
     * @throws InvalidFormatException
     */
    @Test
    public void readCell() throws IOException, InvalidFormatException {
        Workbook wb = WorkbookFactory.create(new File("workbook.xls"));

        DataFormatter formatter = new DataFormatter();
        Sheet sheet1 = wb.getSheetAt(0);
        for (Row row : sheet1) {
            for (Cell cell : row) {
                CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
                //单元格个编号
//                System.out.print(cellRef.formatAsString());
//                System.out.print(" - ");

                // get the text that appears in the cell by getting the cell value and applying any data formats (Date, 0.00, 1.23e9, $1.23, etc)
                String text = formatter.formatCellValue(cell);
//                单元格个内容
                System.out.print(text);
            }
            System.out.println();
        }

    }






}

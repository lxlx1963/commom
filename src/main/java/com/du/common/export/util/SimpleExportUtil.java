package com.du.common.export.util;

import com.du.common.constant.JavaTypeConstant;
import com.du.common.export.entity.Entity;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 导出Excel工具类
 */
public class SimpleExportUtil {

	private static SimpleExportUtil tool = new SimpleExportUtil();
	private static final int PRECISION = 3;
	private static final String DEFAULT_COLUMN_WIDTH = "3200";

	/**
	 * 创建标题行
	 *
	 * @param workbook Workbook
	 * @param row      Row
	 * @param titles   List<Entity>
	 * @param sheet    Sheet
	 */
	public void createTitileRow(Workbook workbook, Row row, String[] titles, Sheet sheet) {
		int length = titles.length;
		CellStyle style = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setColor((short) 12);
		font.setBoldweight((short) 14);
		style.setFont(font);
		style.setFillBackgroundColor((short) 13);
		for (int i = 0; i < length; ++i) {
			sheet.setColumnWidth(i, 2560);
			Cell cell = row.createCell(i);
			cell.setCellValue(titles[i]);
			cell.setCellStyle(style);
		}
	}

	/**
	 * 创建标题行
	 *
	 * @param workbook   Workbook
	 * @param row        Row
	 * @param entityList List<Entity>
	 * @param sheet      Sheet
	 */
	private void createTitleRow(Workbook workbook, Row row, List<Entity> entityList, Sheet sheet) {
		CellStyle style = createHeadStyle(workbook);
		int i = 0;
		for (Entity e : entityList) {
			String columnWidth = e.getWidth();
			sheet.setColumnWidth(i, Integer.valueOf(columnWidth));
			Cell cell = row.createCell(i);
			cell.setCellValue(e.getText());
			cell.setCellStyle(style);
			++i;
		}
	}

	/**
	 * 创建标题行
	 *
	 * @param workbook   Workbook
	 * @param entityList List<Entity>
	 * @param sheet      Sheet
	 * @param startRow  开始行（0开始）
	 * @param startCol  开始列（0开始）
	 */
	private void createTitleRowByPosition(Workbook workbook, Sheet sheet, List<Entity> entityList, int startRow, int startCol) {
		CellStyle style = createHeadStyle(workbook);
		int i = startCol;
        Row titleRow = sheet.createRow(startRow);
        titleRow.setHeightInPoints(25.0F);
		for (Entity e : entityList) {
			String columnWidth = e.getWidth();
			sheet.setColumnWidth(i, Integer.valueOf(columnWidth));
			Cell cell = titleRow.createCell(i);
			cell.setCellValue(e.getText());
			cell.setCellStyle(style);
			++i;
		}
	}

	/**
	 * 创建标题样式
	 *
	 * @param workbook Workbook
	 * @return CellStyle
	 */
	private CellStyle createHeadStyle(Workbook workbook) {
		CellStyle style = workbook.createCellStyle();
		style.setAlignment((short) 2);
		style.setVerticalAlignment((short) 1);
		style.setFillForegroundColor((short) 55);
		style.setFillPattern((short) 1);
		style.setBorderBottom((short) 1);
		style.setBorderLeft((short) 1);
		style.setBorderRight((short) 1);
		style.setBorderTop((short) 1);
		style.setWrapText(true);
		return style;
	}

	/**
	 * 创建单元格样式
	 *
	 * @param workbook Workbook
	 * @return CellStyle
	 */
	public CellStyle createCellStyle(Workbook workbook) {
		CellStyle style = workbook.createCellStyle();
		style.setAlignment((short) 2);
		style.setVerticalAlignment((short) 1);
		style.setFillForegroundColor((short) 9);
		Font font = workbook.createFont();
		font.setColor((short) 8);
		font.setFontHeightInPoints((short) 12);
		style.setWrapText(true);
		return style;
	}

	/**
	 * 解析xml文件
	 *
	 * @param clazz 封装导出到excel的POJO类
	 * @return List<Entity>
	 * @throws Exception
	 */
	private List<Entity> parseXML(Class<? extends Object> clazz) throws Exception {
		List list = new ArrayList();
		String clazzSimpleName = clazz.getSimpleName();
		//从classpath目录下加载xml文件，xml文件配置了具体要导出的列
		ClassPathResource classPathResource = new ClassPathResource("export/" + clazzSimpleName + ".ete.xml");
		InputStream inputStream = classPathResource.getInputStream();
		Document doc = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder()
				.parse(inputStream);
		NodeList nodeList = doc.getElementsByTagName("property");
		for (int i = 0; i < nodeList.getLength(); ++i) {
			Element element = (Element) nodeList.item(i);
			Entity e = new Entity();
			e.setNeme(element.getAttribute("name"));
			e.setText(element.getAttribute("text"));
			e.setType(element.getAttribute("type"));
			String width = element.getAttribute("width");
			if (StringUtils.isBlank(width)) {
				//设置column默认宽度
				e.setWidth(DEFAULT_COLUMN_WIDTH);
			} else {
				e.setWidth(width);
			}
			list.add(e);
		}
		return list;
	}

	/**
	 * 解析注解
	 *
	 * @param clazz Class<? extends Object>
	 * @return List<Entity>
	 * @throws ClassNotFoundException
	 */
	private List<Entity> parseAnnotation(Class<? extends Object> clazz) {
		List entities = new ArrayList();
		Field[] fields = clazz.getDeclaredFields();

		Field[] arrayOfField1 = fields;
		int j = fields.length;
		for (int i = 0; i < j; ++i) {
			Field field = arrayOfField1[i];
			Entity e = new Entity();
			boolean hasAnnotation = field.isAnnotationPresent(com.du.common.export.annotation.Cell.class);
			if (hasAnnotation) {
				com.du.common.export.annotation.Cell annotation = field.getAnnotation(com.du.common.export.annotation.Cell.class);
				e.setText(annotation.title());
				e.setType(field.getType().getName());
				e.setNeme(field.getName());
				entities.add(e);
			}
		}
		return entities;
	}

	/**
	 * 导出数据到excel
	 *
	 * @param list      List<? extends Object>
	 * @param sheetName sheet名称
	 * @return Workbook
	 * @throws Exception
	 */
	public static Workbook exportToExcel(List<? extends Object> list, String sheetName) throws Exception {
		if (CollectionUtils.isEmpty(list)) {
			//提示暂无内容
			Workbook workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet(sheetName);
			// 设置字体
			Font headfont = workbook.createFont();
			headfont.setFontName("黑体");
			// 字体大小
			headfont.setFontHeightInPoints((short) 22);
			// 加粗
			headfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			CellStyle headstyle = workbook.createCellStyle();
			headstyle.setFont(headfont);
			// 左右居中
			headstyle.setAlignment(CellStyle.ALIGN_CENTER);
			// 上下居中
			headstyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			// 创建第一行
			Row row0 = sheet.createRow(0);
			// 设置行高
			row0.setHeight((short) 900);
			// 创建第一列
			Cell cell0 = row0.createCell(0);
			cell0.setCellValue(new HSSFRichTextString("暂无数据！！！"));
			cell0.setCellStyle(headstyle);
			/**
			 * 合并单元格
			 *    第一个参数：第一个单元格的行数（从0开始）
			 *    第二个参数：第二个单元格的行数（从0开始）
			 *    第三个参数：第一个单元格的列数（从0开始）
			 *    第四个参数：第二个单元格的列数（从0开始）
			 */
			CellRangeAddress range = new CellRangeAddress(0, 0, 0, 7);
			sheet.addMergedRegion(range);
			return workbook;
		}
		MathContext mathContext = new MathContext(PRECISION, RoundingMode.HALF_UP);
		Class clazz = list.get(0).getClass();
		//解析xml文件，解析出导出列
		List entities = tool.parseAnnotation(clazz);
		if (CollectionUtils.isEmpty(entities)) {
			entities = tool.parseXML(clazz);
		}
		//创建Workbook
		Workbook workbook = new XSSFWorkbook();
		//创建Sheet
		Sheet sheet = workbook.createSheet(sheetName);
		//创建Row
		Row titleRow = sheet.createRow(0);
		titleRow.setHeightInPoints(25.0F);
		//创建标题行
		tool.createTitleRow(workbook, titleRow, entities, sheet);

		int rowNum = 1;
        createSheetRowData(sheet, rowNum, 0, entities, list, mathContext);
		return workbook;
	}


    /**
     * 导出数据到excel
     *
     * @param mainTitleData 统计数据
     * @param mainTitleStartRow 主标题开始行（0开始）
     * @param mainTitleStartCol 主标题开始列（0开始）
     * @param detailData 具体数据对象集合
     * @param detailDataStartRow 具体数据开始行（0开始）
     * @param detailDataStartCol 具体数据开始列（0开始）
     * @param sheetName sheet名称
     * @param workbook excel工作簿
     * @return
     * @throws Exception
     */
	public static Workbook createSheet(List<? extends Object> mainTitleData, int mainTitleStartRow, int mainTitleStartCol,
	                                   List<? extends Object> detailData, int detailDataStartRow, int detailDataStartCol,
	                                   String sheetName, Workbook workbook) throws Exception {
		//创建Sheet
		Sheet sheet = workbook.createSheet(sheetName);
	    if (CollectionUtils.isEmpty(detailData)) {
		    //创建无数据workbook
			return createNoDataWorkbook(sheetName, workbook);
		}

		MathContext mathContext = new MathContext(PRECISION, RoundingMode.HALF_UP);

		//创建统计数据
		if(CollectionUtils.isNotEmpty(detailData)){
            Row titleRow = sheet.createRow(mainTitleStartRow);
            titleRow.setHeightInPoints(25.0F);
            Class mainTitleClazz = mainTitleData.get(0).getClass();
            //解析xml文件，解析出导出列
            List mainTitleEntities = tool.parseAnnotation(mainTitleClazz);
            if (CollectionUtils.isEmpty(mainTitleEntities)) {
                mainTitleEntities = tool.parseXML(mainTitleClazz);
            }
            tool.createTitleRowByPosition(workbook, sheet, mainTitleEntities, mainTitleStartRow, mainTitleStartCol);
			//创建详情数据
			createSheetRowData(sheet, (mainTitleStartRow + 1), mainTitleStartCol, mainTitleEntities, mainTitleData, mathContext);
		}

		//创建详情
		Class clazz = detailData.get(0).getClass();
		//解析xml文件，解析出导出列
		List detailEntities = tool.parseAnnotation(clazz);
		if (CollectionUtils.isEmpty(detailEntities)) {
            detailEntities = tool.parseXML(clazz);
		}
        //创建详情title
		tool.createTitleRowByPosition(workbook, sheet, detailEntities, detailDataStartRow, detailDataStartCol);
		//创建详情数据
        createSheetRowData(sheet, (detailDataStartRow + 1), detailDataStartCol, detailEntities, detailData, mathContext);
		return workbook;
	}

	/**
     * 创建无数据sheet
     * @param sheetName sheet名称
     * @param workbook Workbook
     * @return Workbook
     */
	private static Workbook createNoDataWorkbook(String sheetName, Workbook workbook){
        //提示暂无内容
        Sheet sheet = workbook.createSheet(sheetName);
        // 设置字体
        Font headfont = workbook.createFont();
        headfont.setFontName("黑体");
        // 字体大小
        headfont.setFontHeightInPoints((short) 22);
        // 加粗
        headfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        CellStyle headstyle = workbook.createCellStyle();
        headstyle.setFont(headfont);
        // 左右居中
        headstyle.setAlignment(CellStyle.ALIGN_CENTER);
        // 上下居中
        headstyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        // 创建第一行
        Row row0 = sheet.createRow(0);
        // 设置行高
        row0.setHeight((short) 900);
        // 创建第一列
        Cell cell0 = row0.createCell(0);
        cell0.setCellValue(new HSSFRichTextString("暂无数据！！！"));
        cell0.setCellStyle(headstyle);
        /**
         * 合并单元格
         *    第一个参数：第一个单元格的行数（从0开始）
         *    第二个参数：第二个单元格的行数（从0开始）
         *    第三个参数：第一个单元格的列数（从0开始）
         *    第四个参数：第二个单元格的列数（从0开始）
         */
        CellRangeAddress range = new CellRangeAddress(0, 0, 0, 7);
        sheet.addMergedRegion(range);
        return workbook;
    }

    /**
     * 设置cell值
     * @param cell Cell
     * @param obj Object
     * @param type String
     * @param mathContext MathContext
     * @throws Exception
     */
    private static void setColValue(Cell cell, Object obj, String type, MathContext mathContext) throws Exception {
        //设置数据到列
        if (obj != null) {
            if (JavaTypeConstant.PRIMITIVE_TYPE_STRING.equals(type) || JavaTypeConstant.JAVA_LANG_STRING.equals(type)) {
                cell.setCellValue((String) obj);
            } else if ((JavaTypeConstant.PRIMITIVE_TYPE_INT.equalsIgnoreCase(type)) || (JavaTypeConstant.JAVA_LANG_INTEGER.equals(type))
		            ||  (JavaTypeConstant.PRIMITIVE_TYPE_INTEGER.equals(type)) ) {
                cell.setCellValue((Integer) obj);
            } else if ((JavaTypeConstant.PRIMITIVE_TYPE_DOUBLE.equalsIgnoreCase(type)) || (JavaTypeConstant.JAVA_LANG_DOUBLE.equals(type))) {
	            BigDecimal bigDecimal = new BigDecimal((Double) obj, mathContext);
                cell.setCellValue(bigDecimal.doubleValue());
            } else if ((JavaTypeConstant.PRIMITIVE_TYPE_BOOLEAN.equalsIgnoreCase(type)) || (JavaTypeConstant.JAVA_LANG_BOOLEAN.equals(type))) {
                cell.setCellValue((Boolean) obj);
            } else if ((JavaTypeConstant.PRIMITIVE_TYPE_FLOAT.equalsIgnoreCase(type)) || (JavaTypeConstant.JAVA_LANG_FLOAT.equals(type))) {
	            BigDecimal bigDecimal = new BigDecimal((Float) obj, mathContext);
                cell.setCellValue(bigDecimal.doubleValue());
            } else if (JavaTypeConstant.PRIMITIVE_TYPE_DATE.equalsIgnoreCase(type) || JavaTypeConstant.JAVA_UTIL_DATE.equals(type)) {
                cell.setCellValue((Date) obj);
            } else if (JavaTypeConstant.PRIMITIVE_TYPE_CALENDAR.equalsIgnoreCase(type) || JavaTypeConstant.JAVA_UTIL_CALENDAR.equals(type)) {
                cell.setCellValue((Calendar) obj);
            } else if ((JavaTypeConstant.PRIMITIVE_TYPE_CHAR.equalsIgnoreCase(type)) || (JavaTypeConstant.JAVA_LANG_CHARACTER.equals(type))) {
                cell.setCellValue(obj.toString());
            } else if ((JavaTypeConstant.PRIMITIVE_TYPE_LONG.equalsIgnoreCase(type)) || (JavaTypeConstant.JAVA_LANG_LONG.equals(type))) {
                cell.setCellValue((Long) obj);
            } else if ((JavaTypeConstant.PRIMITIVE_TYPE_SHORT.equalsIgnoreCase(type)) || (JavaTypeConstant.JAVA_LANG_SHORT.equals(type))) {
                cell.setCellValue((Short) obj);
            } else if (JavaTypeConstant.PRIMITIVE_TYPE_BIGDECIMAL.equals(type) || JavaTypeConstant.JAVA_MATH_BIGDECIMAL.equals(type)) {
	            BigDecimal bigDecimal = new BigDecimal(Double.valueOf(obj.toString()), mathContext);
                cell.setCellValue(bigDecimal.doubleValue());
            } else {
                throw new ExportException("data type error!");
            }
        }
    }

    private static void createSheetRowData(Sheet sheet, int startRow, int startCol, List entities, List<? extends Object> data, MathContext mathContext) throws Exception {
        int rowNum = startRow;
        for (Object aData : data) {
            //创建行
            Row row = sheet.createRow(rowNum++);
            row.setHeightInPoints(23.0F);
            int colNum = startCol;
            for (Object entity1 : entities) {
                Entity entity = (Entity) entity1;
                String type = entity.getType();
                String name = entity.getNeme();
                //创建列
                Cell cell = row.createCell(colNum);
                Object obj = null;
                if ((JavaTypeConstant.PRIMITIVE_TYPE_BOOLEAN.equalsIgnoreCase(type)) || (JavaTypeConstant.JAVA_LANG_BOOLEAN.equals(type))) {
                    obj = MethodToolUtil.executeMethod(aData, MethodToolUtil.returnIsBooleanMethodName(name));
                } else {
                    obj = MethodToolUtil.executeMethod(aData, MethodToolUtil.returnGetMethodName(name));
                }
                setColValue(cell, obj, type, mathContext);
                ++colNum;
            }
        }
    }
}

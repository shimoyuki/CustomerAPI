package com.capi.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

public class ExcelUtil {
	private File file;
	private OutputStream os;
	private XSSFWorkbook book = null;
	private Sheet sheet = null;
	private int[] width;

	public ExcelUtil() {
		super();
	}

	public ExcelUtil(File file) throws IOException, InvalidFormatException {
		super();
		this.file = file;
		if (!file.exists()) {
			file.createNewFile();
		}
		os = new FileOutputStream(file);
		book = new XSSFWorkbook();
	}

	public ExcelUtil(OutputStream os) {
		super();
		this.os = os;
		book = new XSSFWorkbook();
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public OutputStream getOs() {
		return os;
	}

	public void setOs(OutputStream os) {
		this.os = os;
	}

	public XSSFWorkbook getBook() {
		return book;
	}

	public void setBook(XSSFWorkbook book) {
		this.book = book;
	}

	public Sheet getSheet() {
		return sheet;
	}

	public void setSheet(Sheet sheet) {
		this.sheet = sheet;
	}

	public void nextSheet() {
		this.sheet = book.createSheet();
	}

	public void nextSheet(String sheet) {
		this.sheet = book.createSheet(sheet);
	}

	/**
	 * 在excel表第一行写入标题，，用于write()方法之前
	 * 
	 * @param title
	 *            所有项标题组成的字符串数组
	 */
	@SuppressWarnings("deprecation")
	public void writeTitle(String[] title) {
		XSSFCellStyle cellStyle = book.createCellStyle();
		cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		cellStyle.setWrapText(true);
		Row titleRow = sheet.createRow(0);
		width = new int[title.length];
		for (int i = 0; i < title.length; i++) {
			Cell cell = titleRow.createCell(i);
			cell.setCellValue(title[i]);
			cell.setCellStyle(cellStyle);
			width[i] = title[i].length() * 2 * 256;
			sheet.setColumnWidth(i, width[i]);
		}
	}

	/**
	 * 将指定对象object写入excel表中新添的一行
	 * 
	 * @param object
	 * @throws IOException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("deprecation")
	public void write(Object object) throws IOException, IllegalArgumentException, IllegalAccessException {
		XSSFCellStyle cellStyle = book.createCellStyle();
		cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		cellStyle.setWrapText(true);
		int lastRowNum = sheet.getLastRowNum();
		Row curColRow = sheet.createRow(lastRowNum + 1);
		Cell cell = null;
		int curCol = 0, length = 0;

		//System.out.println("Writing class " + object.getClass().getName() + "……");
		Field[] fields = object.getClass().getDeclaredFields();
		if (width == null) {
			width = new int[fields.length - 1];
		}
		for (int i = 0; i < fields.length; i++) {
			// System.out.println(fields[i].getName()+";"+fields[i].getType());
			if (!fields[i].getName().equals("id")) {
				fields[i].setAccessible(true);
				cell = curColRow.createCell(curCol);
				if (fields[i].getType().toString().equals("class java.lang.String")) {
					String value = (String) fields[i].get(object);
					cell.setCellValue(value);
					cell.setCellStyle(cellStyle);
					if (value != null) {
						length = value.length();
					}
				} else if (fields[i].getType().toString().equals("class java.lang.Integer")
						|| fields[i].getType().toString().equals("int")) {
					Integer value = (Integer) fields[i].get(object);
					cell.setCellValue(value);
					cell.setCellStyle(cellStyle);
					if (value != null) {
						length = value.toString().length();
					}	
				} else if (fields[i].getType().toString().equals("class java.lang.Short")
						|| fields[i].getType().toString().equals("short")) {
					Short value = (Short) fields[i].get(object);
					cell.setCellValue(value);
					cell.setCellStyle(cellStyle);
					if (value != null) {
						length = value.toString().length();
					}	
				} else {
					BigDecimal value = (BigDecimal) fields[i].get(object);
					cell.setCellValue(value.floatValue());
					cell.setCellStyle(cellStyle);
					if (value != null) {
						length = value.toString().length();
					}	
				}
				length = length * 2 * 256;
				// System.out.println(length+" ; "+width[curCol]);
				if (length > width[curCol]) {
					width[curCol] = length;
					//System.out.println("Length:" +length);
					sheet.setColumnWidth(curCol, width[curCol]<50*256?width[curCol]:50*256);
				}
				curCol++;
			}
		}
	}
	
	public void writeList(List<Object> list) throws IllegalArgumentException, IllegalAccessException, IOException {
		Iterator<Object> iterator = list.iterator();
		while (iterator.hasNext()) {
			Object object = (Object) iterator.next();
			this.write(object);
		}
	}

	/**
	 * 从excel工作表中指定行row读取类型为cls的对象
	 * 
	 * @param row
	 * @param cls
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static Object read(Row row, Class<?> cls, String except) throws InstantiationException, IllegalAccessException {
		if (row == null)
			return null;
		Object object = cls.newInstance();
		Field[] fields = cls.getDeclaredFields();
		int curCol = row.getFirstCellNum();

		for (int i = 0; i < fields.length; i++) {
			// System.out.println(fields[i].getName()+";"+fields[i].getType());
			if (!fields[i].getName().equals("id") && !except.contains(fields[i].getName())) {
				Cell cell = row.getCell(curCol);
				if (null != cell) {
					fields[i].setAccessible(true);
					if (fields[i].getType().toString().equals("class java.lang.String")) {
						if (cell.getCellTypeEnum() == CellType.BLANK) {
							fields[i].set(object, "");
						} else if (cell.getCellTypeEnum() == CellType.STRING) {
							fields[i].set(object, cell.getStringCellValue());
						} else if (HSSFDateUtil.isCellDateFormatted(cell)) {
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							fields[i].set(object, sdf.format(cell.getDateCellValue()));
						} else {
							if (cell.getNumericCellValue() != 0) {
								fields[i].set(object, (long) cell.getNumericCellValue() + "");
							}
						}
					} else if (fields[i].getType().toString().equals("class java.lang.Integer")
							|| fields[i].getType().toString().equals("int")) {
						if (cell.getCellTypeEnum() == CellType.BLANK) {
							fields[i].set(object, 0);
						} else if (cell.getCellTypeEnum() == CellType.STRING) {
							if (!"".equals(cell.getStringCellValue())) {
								try {
									fields[i].set(object, Integer.parseInt(cell.getStringCellValue()));
								} catch (Exception e) {
									fields[i].set(object, 0);
								}
							}
						} else {
							fields[i].set(object, (int) cell.getNumericCellValue());
						}
					} else if (fields[i].getType().toString().equals("class java.lang.Short")
							|| fields[i].getType().toString().equals("short")) {
						if (cell.getCellTypeEnum() == CellType.BLANK) {
							fields[i].set(object, (short)0);
						} else if (cell.getCellTypeEnum() == CellType.STRING) {
							if (!"".equals(cell.getStringCellValue())) {
								try {
									fields[i].set(object, Short.parseShort(cell.getStringCellValue()));
								} catch (Exception e) {
									fields[i].set(object, (short)0);
								}
							}
						} else {
							fields[i].set(object, (short) cell.getNumericCellValue());
						}
					} else if (fields[i].getType().toString().equals("class java.util.Date")) {
						if (HSSFDateUtil.isCellDateFormatted(cell)) {
							fields[i].set(object, cell.getDateCellValue());
						} else if (cell.getCellTypeEnum() == CellType.STRING) {
							if (!"".equals(cell.getStringCellValue())) {
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
								try {
									fields[i].set(object, sdf.parse(cell.getStringCellValue()));
								} catch (IllegalArgumentException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							} else {
								fields[i].set(object, null);
							}
						} else {
							fields[i].set(object, null);
						}
					} else {
						if (cell.getCellTypeEnum() == CellType.BLANK) {
							fields[i].set(object, new BigDecimal(50000));
						} else if (cell.getCellTypeEnum() == CellType.STRING) {
							if (!"".equals(cell.getStringCellValue())) {
								try {
									fields[i].set(object, new BigDecimal(Double.parseDouble(cell.getStringCellValue())));
								} catch (Exception e) {
									fields[i].set(object, new BigDecimal(50000));
								}
								
							}
						} else {
							fields[i].set(object, new BigDecimal(cell.getNumericCellValue()));
						}
					}
				}
				curCol++;
			}
		}
		return object;
	}

	/**
	 * 从excel工作表sheet中读取类型为cls的对象
	 * 
	 * @param sheet
	 * @param cls
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static List<Object> read(Sheet sheet, Class<?> cls, String except) throws InstantiationException, IllegalAccessException {
		List<Object> result = new ArrayList<Object>();
		int rowStart = sheet.getFirstRowNum() + 1;
		int rowEnd = sheet.getLastRowNum();

		for (int i = rowStart; i <= rowEnd; i++) {
			Row row = sheet.getRow(i);
			Object object = ExcelUtil.read(row, cls, except);
			if (object != null) {
				result.add(object);
			}
		}
		return result;
	}

	/**
	 * 从excel文件file读取所有类型为cls的对象
	 * 
	 * @param file
	 * @param cls
	 * @return
	 * @throws InvalidFormatException
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static List<Object> read(String file, Class<?> cls, String except)
			throws InvalidFormatException, IOException, InstantiationException, IllegalAccessException {
		List<Object> result = new ArrayList<Object>();

		Workbook book;
		Sheet sheet;
		if (file.indexOf(".xlsx") > -1) {
			book = new XSSFWorkbook(new FileInputStream(file));
		} else {
			book = new HSSFWorkbook(new FileInputStream(file));
		}
		for (int i = 0; i < book.getNumberOfSheets(); i++) {
			sheet = book.getSheetAt(i);
			result.addAll(ExcelUtil.read(sheet, cls, except));
		}
		book.close();
		return result;
	}

	public static List<Object> read(MultipartFile file, Class<?> cls, String except)
			throws InvalidFormatException, IOException, InstantiationException, IllegalAccessException {
		List<Object> result = new ArrayList<Object>();

		Workbook book;
		Sheet sheet;
		if (file.getOriginalFilename().indexOf(".xlsx") > -1) {
			book = new XSSFWorkbook(file.getInputStream());
		} else {
			book = new HSSFWorkbook(file.getInputStream());
		}
		for (int i = 0; i < book.getNumberOfSheets(); i++) {
			sheet = book.getSheetAt(i);
			result.addAll(ExcelUtil.read(sheet, cls, except));
		}
		book.close();
		return result;
	}

	/**
	 * 关闭输出流
	 * 
	 * @throws IOException
	 */
	public void close() throws IOException {
		book.write(os);
		book.close();
	}
}

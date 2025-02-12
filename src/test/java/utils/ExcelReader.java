package utils;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.Date;

import models.User;
import models.Address;
import config.ConfigReader;

public class ExcelReader {

	private static Map<Integer, Map<String, String>> sheetData = new HashMap<>();

	static {
		try {
			loadSheetData("Users");
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}
	}

	// Load the entire sheet data
	private static void loadSheetData(String sheetName) throws InvalidFormatException {
		try (FileInputStream file = new FileInputStream(new File(ConfigReader.getExcelPath()));
				Workbook workbook = new XSSFWorkbook(file)) {
			Sheet sheet = workbook.getSheet(sheetName);

			// Assuming the first row contains headers
			Row headerRow = sheet.getRow(0);
			for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
				Row row = sheet.getRow(rowNum);
				if (row != null) {
					Map<String, String> rowData = new HashMap<>();
					for (int cellNum = 0; cellNum < row.getPhysicalNumberOfCells(); cellNum++) {
						String header = headerRow.getCell(cellNum).toString();
						String cellValue = row.getCell(cellNum) != null ? row.getCell(cellNum).toString() : "";
						rowData.put(header, cellValue);
					}
					sheetData.put(rowNum, rowData); // Store row data with row number as key
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Retrieve data by row number
	public static Map<String, String> getRowData(int rowNumber) {
		return sheetData.get(rowNumber);
	}

	public static User getUserByRow(int rowNumber) {
		Map<String, String> rowData = getRowData(rowNumber);

		User user = new User();

		// Handle null or empty values for non-string fields
		// user.setId(parseIntSafely(rowData.get("user_id"), -1)); // -1 as default for
		// invalid or missing ID
		user.setFirstName(rowData.getOrDefault("user_first_name", ""));
		user.setLastName(rowData.getOrDefault("user_last_name", ""));
		user.setEmail(modifyEmail(rowData.getOrDefault("user_email_id", "")));
		user.setContact(getNewContact(rowData.getOrDefault("user_contact_number","")));

		// user.setEmail(rowData.getOrDefault("user_email_id", ""))
		// user.setContact(parseLongSafely(rowData.get("user_contact_number"), 0L)); //
		// Default to 0 if invalid

		// Parse and assign the Address
		user.setAddress(parseAddress(rowData));

		return user;
	}

	public static int getExpectedStatus(int rowNumber) {
		return parseIntSafely(getRowData(rowNumber).get("ExpectedStatus"), 0); // Default to 0 for invalid status
	}

	public static String getExpectedMessage(int rowNumber) {
		return getRowData(rowNumber).getOrDefault("ExpectedMessage", "");
	}

	// Utility method to safely parse integers, returning a default value if the
	// cell is empty or invalid
	private static int parseIntSafely(String value, int defaultValue) {
		try {
			return value.isEmpty() ? defaultValue : Integer.parseInt(value);
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

	// Utility method to safely parse long, returning a default value if the cell is
	// empty or invalid
	private static long parseLongSafely(String value, long defaultValue) {
		if (value == null || value.isEmpty()) {
			return defaultValue;
		}
		try {
			return Long.parseLong(value);
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

	// Utility method to safely parse Address fields (checks for missing or empty
	// values)
	private static Address parseAddress(Map<String, String> rowData) {
		Address address = new Address();
		address.setPlotNumber(rowData.getOrDefault("plotNumber", null));
		address.setStreet(rowData.getOrDefault("street", ""));
		address.setState(rowData.getOrDefault("state", ""));
		address.setCountry(rowData.getOrDefault("country", ""));
		address.setZip(rowData.getOrDefault("zipCode", ""));
		return address;
	}

	public static String modifyEmail(String originalEmail) {
		if (originalEmail != "") {
			String timestamp = new SimpleDateFormat("YYddHHmmss").format(new Date());
			// Append the time stamp to the email address
			return originalEmail.replaceAll("@", timestamp + "@");
		} else
			return originalEmail;
	}

	public static String getNewContact(String contactNumber) {
		if (contactNumber != "") {
			String timestamp = new SimpleDateFormat("YYddHHmmss").format(new Date());
			return timestamp;
		} else
			return "";
	}
	
	
	
}

package utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import models.Address;
import models.User;

public class UserMapper {
	
	public static User mapRowToUser(Map<String, String> row) {
	    User user = new User();
	    user.setFirstName(row.get("user_first_name"));
	    user.setLastName(row.get("user_last_name"));
	    user.setEmail(modifyEmail(row.get("user_email_id")));
	    user.setContact(getNewContact(row.get("user_contact_number")));

	    Address address = new Address();
	    address.setPlotNumber(row.get("plotNumber"));
	    address.setStreet(row.get("street"));
	    address.setState(row.get("state"));
	    address.setCountry(row.get("country"));
	    address.setZip(row.get("zipCode"));

	    user.setAddress(address);
	    return user;
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

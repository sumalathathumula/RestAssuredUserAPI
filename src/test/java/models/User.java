package models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(value = { "user_id" })
public class User {	
	private int user_id;
	private String user_first_name;
    private String user_last_name;
    private String user_email_id;
    private long user_contact_number;
    private Address userAddress;

    // Getters and Setters
    
    @JsonProperty("user_id")
   	public int getUser_id() {
		return user_id;
	}

	@JsonProperty("user_id")
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
    @JsonProperty("user_first_name")  // Specify the JSON field name
    public String getFirstName() {
        return user_first_name;
    }

    @JsonProperty("user_first_name")  // Specify the JSON field name
    public void setFirstName(String user_first_name) {
        this.user_first_name = user_first_name;
    }

    @JsonProperty("user_last_name")  // Specify the JSON field name
    public String getLastName() {
        return user_last_name;
    }

    @JsonProperty("user_last_name")  // Specify the JSON field name
    public void setLastName(String user_last_name) {
        this.user_last_name = user_last_name;
    }

    @JsonProperty("user_email_id")  // Specify the JSON field name
    public String getEmail() {
        return user_email_id;
    }

    @JsonProperty("user_email_id")  // Specify the JSON field name
    public void setEmail(String user_email_id) {
        this.user_email_id = user_email_id;
    }

    @JsonProperty("user_contact_number")  // Specify the JSON field name
    public long getContact() {
        return user_contact_number;
    }

    @JsonProperty("user_contact_number")  // Specify the JSON field name
    public void setContact(long user_contact_number) {
        this.user_contact_number = user_contact_number;
    }

    @JsonProperty("userAddress")  // Specify the JSON field name
    public Address getAddress() {
        return userAddress;
    }

    @JsonProperty("userAddress")  // Specify the JSON field name
    public void setAddress(Address userAddress) {
        this.userAddress = userAddress;
    }

}

package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(value = { "addressId" })
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Address {
	
	private int addressId;	
	private String plotNumber;
	private String street;
    private String state;
    private String country;   
	private long zipCode;

    // Getters and Setters
    
	@JsonProperty("addressId")  // Map to 'address_id' in JSON
    public int getAddressId() {
        return addressId;
    }

    @JsonProperty("addressId")  // Map to 'address_id' in JSON
    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    @JsonProperty("plotNumber")  // Map to 'plot_number' in JSON
    public String getPlotNumber() {
        return plotNumber;
    }

    @JsonProperty("plotNumber")  // Map to 'plot_number' in JSON
    public void setPlotNumber(String plotNumber) {
        this.plotNumber = plotNumber;
    }

    @JsonProperty("street")  // Map to 'street' in JSON
    public String getStreet() {
        return street;
    }

    @JsonProperty("street")  // Map to 'street' in JSON
    public void setStreet(String street) {
        this.street = street;
    }

    @JsonProperty("state")  // Map to 'state' in JSON
    public String getState() {
        return state;
    }

    @JsonProperty("state")  // Map to 'state' in JSON
    public void setState(String state) {
        this.state = state;
    }

    @JsonProperty("country")  // Map to 'country' in JSON
    public String getCountry() {
        return country;
    }

    @JsonProperty("country")  // Map to 'country' in JSON
    public void setCountry(String country) {
        this.country = country;
    }

    @JsonProperty("zipCode")  // Map to 'zip_code' in JSON
    public long getZip() {
        return zipCode;
    }

    @JsonProperty("zipCode")  // Map to 'zip_code' in JSON
    public void setZip(long zipCode) {
        this.zipCode = zipCode;
    }  


}

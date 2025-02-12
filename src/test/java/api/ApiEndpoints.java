package api;

public enum ApiEndpoints {
	
	CREATE_USER("/uap/createusers"),
	GET_ALL_USERS("/uap/users"),
    GET_USER_BY_ID("/uap/user/{userId}"),
    GET_USER_BY_FIRSTNAME("/uap/users/username/{firstName}"),
    UPDATE_USER("/uap/updateuser/{userId}"),
    DELETE_USER_ID("/uap/deleteuser/{userId}"),
	DELETE_USER_BY_FIRSTNAME("/uap/deleteuser/username/{userfirstname}");
	

    private final String endpoint;

    ApiEndpoints(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }

}

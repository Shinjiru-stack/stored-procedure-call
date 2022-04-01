package org.springframework.guru.response;

public class AddUserResponse {
	
	String status;
	String message;
	

	

	public AddUserResponse(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}




	public void setData(AddUserSPResponse addUserSPResponse) {
		// TODO Auto-generated method stub
		
	}

}

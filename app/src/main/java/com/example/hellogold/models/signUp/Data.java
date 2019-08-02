package com.example.hellogold.models.signUp;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("public_key")
	private String publicKey;

	@SerializedName("account_number")
	private String accountNumber;

	@SerializedName("api_key")
	private String apiKey;

	@SerializedName("api_token")
	private String apiToken;

	public void setPublicKey(String publicKey){
		this.publicKey = publicKey;
	}

	public String getPublicKey(){
		return publicKey;
	}

	public void setAccountNumber(String accountNumber){
		this.accountNumber = accountNumber;
	}

	public String getAccountNumber(){
		return accountNumber;
	}

	public void setApiKey(String apiKey){
		this.apiKey = apiKey;
	}

	public String getApiKey(){
		return apiKey;
	}

	public void setApiToken(String apiToken){
		this.apiToken = apiToken;
	}

	public String getApiToken(){
		return apiToken;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"public_key = '" + publicKey + '\'' + 
			",account_number = '" + accountNumber + '\'' + 
			",api_key = '" + apiKey + '\'' + 
			",api_token = '" + apiToken + '\'' + 
			"}";
		}
}
package com.example.hellogold.models.signUp;

import com.example.hellogold.models.signUp.Data;
import com.google.gson.annotations.SerializedName;

public class SignUpResponse{

	@SerializedName("result")
	private String result;

	@SerializedName("code")
	private String code;

	@SerializedName("data")
	private Data data;


	public void setResult(String result){
		this.result = result;
	}

	public String getResult(){
		return result;
	}

	public void setCode(String code){
		this.code = code;
	}

	public String getCode(){
		return code;
	}

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
	}

	@Override
 	public String toString(){
		return 
			"SignUpResponse{" + 
			"result = '" + result + '\'' + 
			",code = '" + code + '\'' + 
			",data = '" + data + '\'' + 
			"}";
		}
}
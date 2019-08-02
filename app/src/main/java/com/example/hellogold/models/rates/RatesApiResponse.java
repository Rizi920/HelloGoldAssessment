package com.example.hellogold.models.rates;

import com.google.gson.annotations.SerializedName;

public class RatesApiResponse{

	@SerializedName("result")
	private String result;

	@SerializedName("data")
	private Data data;

	public void setResult(String result){
		this.result = result;
	}

	public String getResult(){
		return result;
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
			"RatesApiResponse{" + 
			"result = '" + result + '\'' + 
			",data = '" + data + '\'' + 
			"}";
		}
}
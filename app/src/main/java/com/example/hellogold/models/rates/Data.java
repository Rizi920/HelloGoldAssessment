package com.example.hellogold.models.rates;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("spot_price")
	private double spotPrice;

	@SerializedName("buy")
	private double buy;

	@SerializedName("sell")
	private double sell;

	@SerializedName("timestamp")
	private String timestamp;

	public void setSpotPrice(double spotPrice){
		this.spotPrice = spotPrice;
	}

	public double getSpotPrice(){
		return spotPrice;
	}

	public void setBuy(double buy){
		this.buy = buy;
	}

	public double getBuy(){
		return buy;
	}

	public void setSell(double sell){
		this.sell = sell;
	}

	public double getSell(){
		return sell;
	}

	public void setTimestamp(String timestamp){
		this.timestamp = timestamp;
	}

	public String getTimestamp(){
		return timestamp;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"spot_price = '" + spotPrice + '\'' + 
			",buy = '" + buy + '\'' + 
			",sell = '" + sell + '\'' + 
			",timestamp = '" + timestamp + '\'' + 
			"}";
		}
}
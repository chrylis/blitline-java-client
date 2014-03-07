package com.blitline.image;

public class AzureLocation {

	public final String accountName, sharedAccessSignature;
	
	public AzureLocation(String accountName, String sharedAccessSignature) {
		this.accountName = accountName;
		this.sharedAccessSignature = sharedAccessSignature;
	}
	
	public static AzureLocation of(String accountName, String sharedAccessSignature) {
		return new AzureLocation(accountName, sharedAccessSignature);
	}

	public String getAccountName() {
		return accountName;
	}
	
	public String getSharedAccessSignature() {
		return sharedAccessSignature;
	}
}

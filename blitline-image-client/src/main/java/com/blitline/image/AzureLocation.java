package com.blitline.image;

import java.io.Serializable;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
public class AzureLocation implements Serializable {

	private static final long serialVersionUID = 1L;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountName == null) ? 0 : accountName.hashCode());
		result = prime * result + ((sharedAccessSignature == null) ? 0 : sharedAccessSignature.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof AzureLocation))
			return false;
		AzureLocation other = (AzureLocation) obj;
		if (accountName == null) {
			if (other.accountName != null)
				return false;
		} else if (!accountName.equals(other.accountName))
			return false;
		if (sharedAccessSignature == null) {
			if (other.sharedAccessSignature != null)
				return false;
		} else if (!sharedAccessSignature.equals(other.sharedAccessSignature))
			return false;
		return true;
	}
}

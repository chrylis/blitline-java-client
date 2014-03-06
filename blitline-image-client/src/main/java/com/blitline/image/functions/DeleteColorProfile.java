package com.blitline.image.functions;

public class DeleteColorProfile extends AbstractFunction {
	@Override
	public String getName() {
		return "delete_profile";
	}
	
	DeleteColorProfile(String profileName) {
		params.put("name", profileName);
	}
}

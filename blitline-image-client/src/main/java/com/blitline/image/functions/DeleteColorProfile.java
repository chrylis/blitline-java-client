package com.blitline.image.functions;

public class DeleteColorProfile extends AbstractFunction {
	@Override
	public String getName() {
		return "delete_profile";
	}
	
	public DeleteColorProfile(String profileName) {
		params.put("name", profileName);
	}
}

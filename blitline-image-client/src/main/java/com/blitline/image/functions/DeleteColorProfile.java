package com.blitline.image.functions;

public class DeleteColorProfile extends AbstractFunction {

	private static final long serialVersionUID = 1L;

	@Override
	public String getName() {
		return "delete_profile";
	}

	public DeleteColorProfile(String profileName) {
		params.put("name", profileName);
	}
}

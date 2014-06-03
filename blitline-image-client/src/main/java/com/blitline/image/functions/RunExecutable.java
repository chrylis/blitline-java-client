package com.blitline.image.functions;

import java.net.URI;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

/**
 * Execute an arbitrary command inside the Blitline job container.
 *
 * @author Christopher Smith
 *
 */
public class RunExecutable extends AbstractScriptFunction {

	private static final long serialVersionUID = 1L;

	/**
	 * @param executableCommand
	 *            the command to be run, as if using {@code bash -c}
	 */
	public RunExecutable(String executableCommand) {
		params.put("executable", executableCommand);
	}

	public RunExecutable withFiles(String... urls) {
		Validate.validState(!params.containsKey("files"), "only one set of files may be specified");
		params.put("files", StringUtils.join(urls, ','));
		return this;
	}

	public RunExecutable withFiles(URI... urls) {
		Validate.validState(!params.containsKey("files"), "only one set of files may be specified");
		params.put("files", StringUtils.join(urls, ','));
		return this;
	}
}

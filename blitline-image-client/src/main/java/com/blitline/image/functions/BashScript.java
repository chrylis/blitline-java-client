package com.blitline.image.functions;

/**
 * Execute an arbitrary Bash script inside the Blitline job container.
 * 
 * @author Christopher Smith
 *
 */
public class BashScript extends AbstractScriptFunction {
	
	/**
	 * @param script the Bash script to be run, as if using {@code bash -c}
	 */
	public BashScript(String script) {
		params.put("bash_string", script);
	}
}

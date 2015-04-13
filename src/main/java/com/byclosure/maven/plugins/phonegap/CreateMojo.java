package com.byclosure.maven.plugins.phonegap;

import org.apache.commons.exec.CommandLine;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * @goal create
 * @requiresProject true
 */
public class CreateMojo extends AbstractPhoneGapMojo {

	/**
	 * @parameter default-value="com.easyeasyapps.framework" expression="${phonegap.package}"
	 */
	protected String package_;

	/**
	 * @parameter default-value="Hello World" expression="${phonegap.name}"
	 */
	protected String name;


	@Override
	protected void executeComand() throws IOException {
		final String phonegapBinPath;

		if (phonegapBin != null) {
			phonegapBinPath = new File(phonegapBin, "phonegap").getPath();
		} else {
			phonegapBinPath = "phonegap";
		}

		final String fixedName = name.replaceAll("-", "").replaceAll(" ", "");
		final String fixedPackage = package_.replaceAll("-", "").replaceAll(" ", "");

		final CommandLine cmd = getCrossPlatformCommandLine();
		cmd.addArgument(phonegapBinPath + " create " + projectPath + " \"" + fixedPackage + "\" \"" + fixedName + "\"", false);

		final Map<String, String> env = getEnv();

		System.out.println("Executing: " + cmd.toString());
		executeCommandLine(cmd, env, workingDir);
	}
}
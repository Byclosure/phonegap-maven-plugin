package com.byclosure.maven.plugins.phonegap;

import org.apache.commons.exec.CommandLine;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * @goal platform-remove
 * @requiresProject true
 */
public class PlatformRemoveMojo extends AbstractPhoneGapMojo {

	/**
	 * @parameter expression="${phonegap.platform}"
	 */
	protected String platform;

	@Override
	protected void executeComand() throws IOException {
		final String phonegapBinPath;

		if (phonegapBin != null) {
			phonegapBinPath = new File(phonegapBin, "phonegap").getPath();
		} else {
			phonegapBinPath = "phonegap";
		}

		final CommandLine cmd = getCrossPlatformCommandLine();
		cmd.addArgument(phonegapBinPath + " platform remove \"" + platform + "\"", false);

		final Map<String, String> env = getEnv();
		final File phonegapDir = new File(workingDir, projectPath);

		executeCommandLine(cmd, env, phonegapDir);
	}
}
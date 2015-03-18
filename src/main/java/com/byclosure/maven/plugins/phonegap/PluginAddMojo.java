package com.byclosure.maven.plugins.phonegap;

import org.apache.commons.exec.CommandLine;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * @goal plugin-add
 * @requiresProject true
 */
public class PluginAddMojo extends AbstractPhoneGapMojo {

	/**
	 * @parameter expression="${phonegap.plugin}"
	 */
	protected String plugin;

	@Override
	protected void executeComand() throws IOException {
		final String phonegapBinPath;

		if (phonegapBin != null) {
			phonegapBinPath = new File(phonegapBin, "phonegap").getPath();
		} else {
			phonegapBinPath = "phonegap";
		}

		final CommandLine cmd = getCrossPlatformCommandLine();
		cmd.addArgument(phonegapBinPath + " plugin add \"" + plugin + "\"", false);

		final Map<String, String> env = getEnv();
		final File phonegapDir = new File(workingDir, projectPath);

		executeCommandLine(cmd, env, phonegapDir);
	}
}
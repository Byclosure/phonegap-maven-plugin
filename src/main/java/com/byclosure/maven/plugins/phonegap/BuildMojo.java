package com.byclosure.maven.plugins.phonegap;

import org.apache.commons.exec.CommandLine;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * @goal build
 * @requiresProject true
 */
public class BuildMojo extends AbstractPhoneGapMojo {

	/**
	 * @parameter default-value="false" expression="${phonegap.verbose}"
	 */
	protected boolean verbose;

	/**
	 * @parameter default-value="false" expression="${phonegap.release}"
	 */
	protected boolean release;

	@Override
	protected void executeComand() throws IOException {
		final String phonegapBinPath;

		if (phonegapBin != null) {
			phonegapBinPath = new File(phonegapBin, "phonegap").getPath();
		} else {
			phonegapBinPath = "phonegap";
		}

		final StringBuilder sb = new StringBuilder();
		sb.append(phonegapBinPath);
		sb.append(" build");

		if (verbose) {
			sb.append(" --verbose");
		}

		if (release) {
			sb.append(" --release");
		}

		final CommandLine cmd = getCrossPlatformCommandLine();
		cmd.addArgument(sb.toString() , false);

		final Map<String, String> env = getEnv();
		final File phonegapDir = new File(workingDir, projectPath);

		executeCommandLine(cmd, env, phonegapDir);
	}
}
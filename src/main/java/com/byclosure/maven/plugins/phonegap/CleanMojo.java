package com.byclosure.maven.plugins.phonegap;

import org.codehaus.plexus.util.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * @goal clean
 * @requiresProject true
 */
public class CleanMojo extends AbstractPhoneGapMojo {

	@Override
	protected void executeComand() throws IOException {
		final File projectPathFile = new File(workingDir, projectPath);

		if (projectPathFile.exists()) {
			FileUtils.deleteDirectory(projectPathFile);
		}
	}
}
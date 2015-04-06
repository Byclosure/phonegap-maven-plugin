package com.byclosure.maven.plugins.phonegap;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractPhoneGapMojo extends AbstractMojo {

	/**
	 * @parameter default-value="${project.basedir}" expression="${phonegap.base_dir}"
	 */
	protected File projectBaseDir;

	/**
	 * @parameter default-value="${project.build.directory}" expression="${phonegap.working_dir}
	 */
	protected File workingDir;

	/**
	 * @parameter default-value="phonegap" expression="${phonegap.project_path}
	 */
	protected String projectPath;

	/**
	 * @parameter expression="${phonegap.phonegap_bin}"
	 */
	protected File phonegapBin;


	protected abstract void executeComand() throws IOException;

	@Override
	public void execute() throws MojoExecutionException {
		try {
			executeComand();
		} catch (IOException e) {
			throw new MojoExecutionException(e.getMessage());
		}
	}

	protected boolean isWindows() {
		return System.getProperty("os.name").indexOf("Win") >= 0;
	}
	protected boolean isMac() {
		return System.getProperty("os.name").indexOf("Mac") >= 0;
	}

	protected CommandLine getCrossPlatformCommandLine() {
		final CommandLine cmdLine;

		if (isWindows()) {
			cmdLine = new CommandLine("cmd");
			cmdLine.addArgument("/c");
		} else if (isMac()) {
			cmdLine = new CommandLine("/bin/sh");
			cmdLine.addArgument("-c");
		} else {
			cmdLine = new CommandLine("/bin/sh");
			cmdLine.addArgument("-c");
		}

		return cmdLine;
	}

	protected void executeCommandLine(CommandLine cmdLine, Map<String, String> env, File workingDir) throws IOException {
		DefaultExecutor executor = new DefaultExecutor();
		executor.setWorkingDirectory(workingDir);

		PumpStreamHandler pump = new PumpStreamHandler(System.out, System.err, System.in);
		executor.setStreamHandler(pump);

		executor.execute(cmdLine, env);
	}

	protected Map<String, String> getEnv() {
		final Map<String, String> env = new HashMap<String, String>(System.getenv());
		return env;
	}

}

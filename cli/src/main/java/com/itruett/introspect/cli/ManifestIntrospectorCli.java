package com.itruett.introspect.cli;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.jar.Manifest;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.itruett.introspect.ManifestIntrospector;

public class ManifestIntrospectorCli {

	public static void main(String[] args) throws ParseException, IOException {
		final CommandLineParser parser = new DefaultParser();

		final Options options = new Options();
		options.addOption("o", "output", false, "path to output file");

		final CommandLine commandLine = parser.parse(options, args);
		final List<String> arguments = commandLine.getArgList();

		if (arguments.isEmpty()) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp(String.format("java %s {options} {attribute}", ManifestIntrospectorCli.class.getSimpleName()), options);
		} else {
			Manifest manifest = new ManifestIntrospector(ManifestIntrospectorCli.class.getClassLoader()).getManifest();

			try (final PrintStream out = commandLine.hasOption("o")
					? new PrintStream(new FileOutputStream(commandLine.getOptionValue("o"))) : System.out) {
				out.println(manifest.getMainAttributes().getValue(arguments.get(0)));
			}
		}
	}
}

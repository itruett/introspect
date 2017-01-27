package com.itruett.introspect;

import java.io.IOException;
import java.io.InputStream;
import java.util.jar.Manifest;

/**
 * Utility for obtaining a Manifest from a {@link ClassLoader}.
 */
public class ManifestIntrospector {

	private ClassLoader classLoader;

	/**
	 * Creates a new ManifestIntrospector for the given {@link ClassLoader}.
	 *
	 * @param classLoader
	 */
	public ManifestIntrospector(final ClassLoader classLoader) {
		this.classLoader = classLoader;
	}

	/**
	 * No-arg constructor that uses this classes's {@link ClassLoader}.
	 */
	public ManifestIntrospector() {
		this(ManifestIntrospector.class.getClassLoader());
	}

	/**
	 * Tries to get the {@link Manifest} from this ManifestIntrospector's {@link ClassLoader}. Returns {@code null} if
	 * the {@code MANIFEST.MF} file is not found.
	 *
	 * @return the {@link Manifest} from this ManifestIntrospector's {@link ClassLoader}
	 * @throws IOException
	 *             thrown by the {@link ClassLoader} if an I/O error occurs
	 */
	public Manifest getManifest() throws IOException {
		try (InputStream stream = classLoader.getResourceAsStream("META-INF/MANIFEST.MF")) {
			if (stream == null) {
				return null;
			} else {
				return new Manifest(stream);
			}
		}
	}
}

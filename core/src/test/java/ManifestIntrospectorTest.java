import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.jar.Manifest;

import org.junit.Test;

import com.itruett.introspect.ManifestIntrospector;

public class ManifestIntrospectorTest {

	@Test
	public void testGetManifestVersion() throws IOException {
		final Manifest manifest = new ManifestIntrospector(this.getClass().getClassLoader()).getManifest();
		assertNotNull(manifest);
		assertEquals("1.0", manifest.getMainAttributes().getValue("Manifest-Version"));
	}

}

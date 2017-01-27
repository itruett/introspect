package com.itruett.introspect.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import javax.ws.rs.core.Response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.itruett.introspect.ManifestIntrospector;

@RunWith(MockitoJUnitRunner.class)
public class ManifestResourceTest {

	@Mock
	private Manifest manifest;

	@Mock
	private ManifestIntrospector introspector;

	@InjectMocks
	private ManifestResource resource;

	@Mock
	private Attributes attributes;

	@Test
	public void testGetNonExistantManifestValueReturns404() throws IOException {
		final Response response = resource.getManifestValue("Manifest-Destiny");

		assertEquals(404, response.getStatus());
		assertNull(response.getEntity());
	}

	@Test
	public void testGetExistingManifestValueReturns200() throws IOException {
		when(introspector.getManifest()).thenReturn(manifest);
		when(manifest.getMainAttributes()).thenReturn(attributes);
		when(attributes.getValue("Manifest-Destiny")).thenReturn("1.0");

		final Response response = resource.getManifestValue("Manifest-Destiny");

		assertEquals(200, response.getStatus());
		assertEquals("1.0", response.getEntity());
	}

}

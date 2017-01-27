package com.itruett.introspect.api;

import java.io.IOException;
import java.util.jar.Manifest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.itruett.introspect.ManifestIntrospector;

@Path("/manifest")
public class ManifestResource {
	private ManifestIntrospector introspector = new ManifestIntrospector();

	@GET
	@Path("{key}")
	@Produces("application/json")
	public Response getManifestValue(@PathParam("key") final String key) throws IOException {
		final Manifest manifest = introspector.getManifest();

		if (manifest == null) {
			return Response.status(404).build();
		} else {
			return Response.ok(manifest.getMainAttributes().getValue(key)).build();
		}
	}

	@GET
	@Path("/")
	@Produces("application/json")
	public Response getManifestAttributes() throws IOException {
		final Manifest manifest = introspector.getManifest();

		if (manifest == null) {
			return Response.status(404).build();
		} else {
			return Response.ok(manifest.getMainAttributes()).build();
		}
	}
}

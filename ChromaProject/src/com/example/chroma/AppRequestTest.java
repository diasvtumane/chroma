/**
 * 
 */
package com.example.chroma;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Mark
 *
 */
class AppRequestTest {
	/**
	 * Test method for {@link com.example.chroma.AppRequest#AppRequest(java.lang.String, java.lang.String, java.util.List)}.
	 */
	@Test
	void testAppRequest() {
		List<String> platforms = new ArrayList<>();
		platforms.add("ios");
		AppRequest app = new AppRequest("Testapp","1.0", platforms);
		assertNotNull(app);
	}

	/**
	 * Test method for {@link com.example.chroma.AppRequest#getName()}.
	 */
	@Test
	void testGetName() {
		List<String> platforms = new ArrayList<>();
		platforms.add("ios");
		AppRequest app = new AppRequest("Testapp","1.0", platforms);
		String expected = app.getName();
		assertEquals(expected,"Testapp");
	}

	/**
	 * Test method for {@link com.example.chroma.AppRequest#setName(java.lang.String)}.
	 */
	@Test
	void testSetName() {
		List<String> platforms = new ArrayList<>();
		platforms.add("ios");
		AppRequest app = new AppRequest("Testapp","1.0", platforms);
		app.setName("Testapp1.1");
		String expected = app.getName();
		assertEquals(expected,"Testapp1.1");
	}

	/**
	 * Test method for {@link com.example.chroma.AppRequest#getVersion()}.
	 */
	@Test
	void testGetVersion() {
		List<String> platforms = new ArrayList<>();
		platforms.add("ios");
		AppRequest app = new AppRequest("Testapp","1.0", platforms);
		String expected = app.getVersion();
		assertEquals(expected,"1.0");
	}

	/**
	 * Test method for {@link com.example.chroma.AppRequest#setVersion(java.lang.String)}.
	 */
	@Test
	void testSetVersion() {
		List<String> platforms = new ArrayList<>();
		platforms.add("ios");
		AppRequest app = new AppRequest("Testapp","1.0", platforms);
		app.setVersion("1.1");
		String expected = app.getVersion();
		assertEquals(expected,"1.1");
	}

	/**
	 * Test method for {@link com.example.chroma.AppRequest#getPlatforms()}.
	 */
	@Test
	void testGetPlatforms() {
		List<String> platforms = new ArrayList<>();
		platforms.add("ios");
		AppRequest app = new AppRequest("Testapp","1.0", platforms);
		List<String> expected = app.getPlatforms();
		assertEquals(expected.get(0),"ios");
	}

	/**
	 * Test method for {@link com.example.chroma.AppRequest#setPlatforms(java.util.List)}.
	 */
	@Test
	void testSetPlatforms() {
		List<String> platforms = new ArrayList<>();
		List<String> platforms2 = new ArrayList<>();
		platforms.add("ios");
		AppRequest app = new AppRequest("Testapp","1.0", platforms);
		app.setPlatforms(platforms2);
		platforms2.add("ios2");
		List<String> expected = app.getPlatforms();
		assertEquals(expected.get(0),"ios2");
	}

	/**
	 * Test method for {@link com.example.chroma.AppRequest#toString()}.
	 */
	@Test
	void testToString() {
		List<String> platforms = new ArrayList<>();
		platforms.add("ios");
		AppRequest app = new AppRequest("Testapp","1.0", platforms);
		assertEquals(app.toString(), "Testapp 1.0 [ios]");	}

}

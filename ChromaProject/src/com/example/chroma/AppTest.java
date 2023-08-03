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
class AppTest {
	/**
	 * Test method for {@link com.example.chroma.App#App(java.lang.String, java.lang.String, java.util.List)}.
	 */
	
	@Test
	void testApp() {
		List<String> platforms = new ArrayList<>();
		platforms.add("game");
		App app = new App("Testapp","1.0", platforms);
		assertNotNull(app);
	}

	/**
	 * Test method for {@link com.example.chroma.App#getName()}.
	 */
	@Test
	void testGetName() {
		List<String> platforms = new ArrayList<>();
		App app = new App("Testapp","1.0", platforms);
		String expected = app.getName();
		assertEquals(expected,"Testapp");
	}
	/**
	 * Test method for {@link com.example.chroma.App#getVersion()}.
	 */
	@Test
	void testGetVersion() {
		List<String> platforms = new ArrayList<>();
		App app = new App("Testapp","1.0", platforms);
		String expected = app.getVersion();
		assertEquals(expected,"1.0");
	}

	/**
	 * Test method for {@link com.example.chroma.App#getPlatforms()}.
	 */
	@Test
	void testGetPlatforms() {
		List<String> platforms = new ArrayList<>();
		platforms.add("ios");
		App app = new App("Testapp","1.0", platforms);
		List<String> expected = app.getPlatforms();
		assertEquals(expected.size(),1);
	}

	/**
	 * Test method for {@link com.example.chroma.App#toString()}.
	 */
	@Test
	void testToString() {
		List<String> platforms = new ArrayList<>();
		platforms.add("ios");
		App app = new App("Testapp","1.0", platforms);
		assertEquals(app.toString(), "Testapp,1.0,ios\n");
	}

}

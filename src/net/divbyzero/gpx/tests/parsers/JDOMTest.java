/*
 * Copyright (c) 2009 Martin Jansen
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. The name of the author may not be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.divbyzero.gpx.tests.parsers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import net.divbyzero.gpx.GPX;
import net.divbyzero.gpx.parser.JDOM;
import net.divbyzero.gpx.parser.Parser;
import net.divbyzero.gpx.parser.ParsingException;

import org.junit.Test;

public class JDOMTest {

	@Test
	public void testTrackParsing() throws Exception {
		Parser parser = new JDOM();
		GPX gpx = parser.parse(new File("data/track.gpx"));
		
		assertEquals(1, gpx.getTracks().size());
		
		assertTrue(gpx.getTracks().get(0).cumulativeAscent() > 0);
		assertTrue(gpx.getTracks().get(0).cumulativeDescent() > 0);
		assertTrue(gpx.getTracks().get(0).length() > 0);
		
		assertNotNull(gpx.getTracks().get(0).startingTime());
		assertNotNull(gpx.getTracks().get(0).endTime());
	}
	
	@Test
	public void testTrackingParsingFromURL() throws ParsingException {
		URL url = null;
		Parser parser = new JDOM();
		try {
			url = new File("data/track.gpx").toURI().toURL();
		} catch (MalformedURLException e) {
			fail();
		}
		
		GPX gpx = parser.parse(url);
		assertEquals(1, gpx.getTracks().size());
		
		assertTrue(gpx.getTracks().get(0).cumulativeAscent() > 0);
		assertTrue(gpx.getTracks().get(0).cumulativeDescent() > 0);
		assertTrue(gpx.getTracks().get(0).length() > 0);
		
		assertNotNull(gpx.getTracks().get(0).startingTime());
		assertNotNull(gpx.getTracks().get(0).endTime());
	}
}

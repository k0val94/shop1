package shop;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ArtikelTest {
	private Artikel artikel;
	
	@Before
	public void setUp() throws Exception {
		artikel = new Artikel();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBeschreibeArtikel() throws IOException {
			
		for(int i = 0;i < 10; i++){
			artikel.beschreibeArtikel("Musterartikel", 100);
			assertEquals("Artikel hat die falsche ID",i,this.artikel.artikeln.get(i).getID());
		}
	}
}

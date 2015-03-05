package shop;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import shop.Kunde;

public class KundeTest {

	
	private Kunde kunde;
	@Before
	public void setUp() throws Exception {
		kunde = new Kunde();
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testBeschreibeKunde() throws IOException {
		
		for(int i = 0;i < 10; i++){
			kunde.beschreibeKunde("Max", "Mustermann", "DE, Musterstadt, Musterstr. 9");
			assertEquals("Kunde hat die falsche ID",i,this.kunde.kunden.get(i).getID());
		}
	}


}

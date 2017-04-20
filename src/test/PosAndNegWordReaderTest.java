package test;

import static org.junit.Assert.*;

import java.util.List;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import db.PosNegWordReader;

public class PosAndNegWordReaderTest {
	
	private PosNegWordReader pnwr;
	private List<String> liwcFile;
	
	@Before
	public void setUp() throws IOException {
		this.pnwr = new PosNegWordReader();
		this.liwcFile = pnwr.read();
	}

	@Test
	public void testFirstLine() {
		assertEquals(liwcFile.get(0), "accettabil	positive");
	}
	
	@Test
	public void testGetFeaturesByCategory() throws IOException {
		
		List<String> posFeatures = this.pnwr.getFeaturesByCategory("positive");
		List<String> negFeatures = this.pnwr.getFeaturesByCategory("negative");
		
		assertEquals(posFeatures.get(0), "accettabil");
		assertEquals(posFeatures.get(13), "affascina");
		
		assertEquals(negFeatures.get(0), "affann");
		assertEquals(negFeatures.get(2), "afferrabil");
		
	}

}

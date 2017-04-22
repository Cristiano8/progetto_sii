package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tweetprocessing.Stemmer;

public class StemmerTest {
	
	private Stemmer stemmer;
	
	@Before
	public void setUp(){
		stemmer = new Stemmer();
	}

	
	/*Test con radice abbadon*/
	@Test
	public void stemmerTestAbbandon() {
		
		String ata = "abbandonata";
		String ataStemmed = stemmer.stem(ata);
		System.out.println(ataStemmed);
		assertTrue(ataStemmed.equals("abbandon"));
		
		String ate = "abbandonate";
		String ateStemmed = stemmer.stem(ate);
		System.out.println(ateStemmed);
		assertTrue(ateStemmed.equals("abbandon"));
		
		String ati = "abbandonati";
		String atiStemmed = stemmer.stem(ati);
		System.out.println(atiStemmed);
		assertTrue(atiStemmed.equals("abbandon"));
		
		String ato = "abbandonato";
		String atoStemmed = stemmer.stem(ato);
		System.out.println(atoStemmed);
		assertTrue(atoStemmed.equals("abbandon"));
		
		String ava = "abbandonava";
		String avaStemmed = stemmer.stem(ava);
		System.out.println(avaStemmed);
		assertTrue(avaStemmed.equals("abbandon"));
		
		String era = "abbandonera";
		String eraStemmed = stemmer.stem(era);
		System.out.println(eraStemmed);
		assertTrue(eraStemmed.equals("abbandon"));
		
		String eranno = "abbandoneranno";
		String erannoStemmed = stemmer.stem(eranno);
		System.out.println(erannoStemmed);
		assertTrue(erannoStemmed.equals("abbandon"));
			
		//ACCENT
		String ero = "abbandonero";
		String eroStemmed = stemmer.stem(ero);
		System.out.println(eroStemmed);
		assertTrue(eroStemmed.equals("abbandon"));
		
		//ACCENT
		String ono = "abbandono";
		String onoStemmed = stemmer.stem(ono);
		System.out.println(onoStemmed);
		assertTrue(onoStemmed.equals("abband"));
	}
	
	/*Test con radice abbass*/
	@Test
	public void stemmerTestAbbass(){
		
		String amento = "abbassamento";
		String amentoStemmed = stemmer.stem(amento);
		System.out.println(amentoStemmed);
		assertTrue(amentoStemmed.equals("abbass"));

		String ando = "abbassando";
		String andoStemmed = stemmer.stem(ando);
		System.out.println(andoStemmed);
		assertTrue(andoStemmed.equals("abbass"));
		
		String andola = "abbassandola";
		String andolaStemmed = stemmer.stem(andola);
		System.out.println(andolaStemmed);
		assertTrue(andolaStemmed.equals("abbass"));

		String andole = "abbassandole";
		String andoleStemmed = stemmer.stem(andole);
		System.out.println(andoleStemmed);
		assertTrue(andoleStemmed.equals("abbass"));
		
		String ar = "abbassar";
		String arStemmed = stemmer.stem(ar);
		System.out.println(arStemmed);
		assertTrue(arStemmed.equals("abbass"));
		
		String are = "abbassare";
		String areStemmed = stemmer.stem(are);
		System.out.println(areStemmed);
		assertTrue(areStemmed.equals("abbass"));
		
		String arono = "abbassarono";
		String aronoStemmed = stemmer.stem(arono);
		System.out.println(aronoStemmed);
		assertTrue(aronoStemmed.equals("abbass"));

		String arsi = "abbassarsi";
		String arsiStemmed = stemmer.stem(arsi);
		System.out.println(arsiStemmed);
		assertTrue(arsiStemmed.equals("abbass"));
		
		String assero = "abbassassero";
		String asseroStemmed = stemmer.stem(assero);
		System.out.println(asseroStemmed);
		assertTrue(asseroStemmed.equals("abbass"));
		
		String ato = "abbassato";
		String atoStemmed = stemmer.stem(ato);
		System.out.println(atoStemmed);
		assertTrue(atoStemmed.equals("abbass"));
		
		String ava = "abbassava";
		String avaStemmed = stemmer.stem(ava);
		System.out.println(avaStemmed);
		assertTrue(avaStemmed.equals("abbass"));
		
		String i = "abbassi";
		String iStemmed = stemmer.stem(i);
		System.out.println(iStemmed);
		assertTrue(iStemmed.equals("abbass"));
	}
	
	/*Test con radice abbatt, e abbast*/
	@Test
	public void stemmerTestAbbatt(){
		
		String anza = "abbastanza";
		String anzaStemmed = stemmer.stem(anza);
		System.out.println(anzaStemmed);
		assertTrue(anzaStemmed.equals("abbast"));
		
		String endo = "abbattendo";
		String endoStemmed = stemmer.stem(endo);
		System.out.println(endoStemmed);
		assertTrue(endoStemmed.equals("abbatt"));
		
		String ere = "abbattere";
		String ereStemmed = stemmer.stem(ere);
		System.out.println(ereStemmed);
		assertTrue(ereStemmed.equals("abbatt"));
		
		String ersi = "abbattersi";
		String ersiStemmed = stemmer.stem(ersi);
		System.out.println(ersiStemmed);
		assertTrue(ersiStemmed.equals("abbatt"));
		
		String esse = "abbattesse";
		String esseStemmed = stemmer.stem(esse);
		System.out.println(esseStemmed);
		assertTrue(esseStemmed.equals("abbattess"));
		
		String eva = "abbatteva";
		String evaStemmed = stemmer.stem(eva);
		System.out.println(evaStemmed);
		assertTrue(evaStemmed.equals("abbatt"));
		
		String evamo = "abbattevamo";
		String evamoStemmed = stemmer.stem(evamo);
		System.out.println(evamoStemmed);
		assertTrue(evamoStemmed.equals("abbatt"));
		
		String evano = "abbattevano";
		String evanoStemmed = stemmer.stem(evano);
		System.out.println(evanoStemmed);
		assertTrue(evanoStemmed.equals("abbatt"));
		
		String imento = "abbattimento";
		String imentoStemmed = stemmer.stem(imento);
		System.out.println(imentoStemmed);
		assertTrue(imentoStemmed.equals("abbatt"));
		
		String uta = "abbattuta";
		String utaStemmed = stemmer.stem(uta);
		System.out.println(utaStemmed);
		assertTrue(utaStemmed.equals("abbatt"));
		
		String uti = "abbattuti";
		String utiStemmed = stemmer.stem(uti);
		System.out.println(utiStemmed);
		assertTrue(utiStemmed.equals("abbatt"));
		
		String uto = "abbattuto";
		String utoStemmed = stemmer.stem(uto);
		System.out.println(utoStemmed);
		assertTrue(utoStemmed.equals("abbatt"));
		
		String abbi = "abbi";
		String abbiStemmed = stemmer.stem(abbi);
		System.out.println(abbiStemmed);
		assertTrue(abbiStemmed.equals("abb"));
	}
	
	/*Test con radice pronunc*/
	@Test
	public void stemmerTestPronunc(){
		
		String pronto = "pronto";
		String prontoStemmed = stemmer.stem(pronto);
		System.out.println(prontoStemmed);
		assertTrue(prontoStemmed.equals("pront"));
		
		String uncera = "pronuncera";
		String unceraStemmed = stemmer.stem(uncera);
		System.out.println(unceraStemmed);
		assertTrue(unceraStemmed.equals("pronunc"));
		
		String uncia = "pronuncia";
		String unciaStemmed = stemmer.stem(uncia);
		System.out.println(unciaStemmed);
		assertTrue(unciaStemmed.equals("pronunc"));
		
		String unciamento = "pronunciamento";
		String unciamentoStemmed = stemmer.stem(unciamento);
		System.out.println(unciamentoStemmed);
		assertTrue(unciamentoStemmed.equals("pronunc"));

		String unciare = "pronunciare";
		String unciareStemmed = stemmer.stem(unciare);
		System.out.println(unciareStemmed);
		assertTrue(unciareStemmed.equals("pronunc"));

		String unciarsi = "pronunciarsi";
		String unciarsiStemmed = stemmer.stem(unciarsi);
		System.out.println(unciarsiStemmed);
		assertTrue(unciarsiStemmed.equals("pronunc"));

		String unciata = "pronunciata";
		String unciataStemmed = stemmer.stem(unciata);
		System.out.println(unciataStemmed);
		assertTrue(unciataStemmed.equals("pronunc"));

		String unciate = "pronunciate";
		String unciateStemmed = stemmer.stem(unciate);
		System.out.println(unciateStemmed);
		assertTrue(unciateStemmed.equals("pronunc"));

		String unciato = "pronunciato";
		String unciatoStemmed = stemmer.stem(unciato);
		System.out.println(unciatoStemmed);
		assertTrue(unciatoStemmed.equals("pronunc"));

	}
	
	/*Test con radice pronunz*/
	@Test
	public void stemmerTestPronunz(){
		
		String unzia = "pronunzia";
		String unziaStemmed = stemmer.stem(unzia);
		System.out.println(unziaStemmed);
		assertTrue(unziaStemmed.equals("pronunz"));
		
		String unzio = "pronunzio";
		String unzioStemmed = stemmer.stem(unzio);
		System.out.println(unzioStemmed);
		assertTrue(unzioStemmed.equals("pronunz"));
		
		String unziano = "pronunziano";
		String unzianoStemmed = stemmer.stem(unziano);
		System.out.println(unzianoStemmed);
		assertTrue(unzianoStemmed.equals("pronunz"));
		
		String unziare = "pronunziare";
		String unziareStemmed = stemmer.stem(unziare);
		System.out.println(unziareStemmed);
		assertTrue(unziareStemmed.equals("pronunz"));

		String unziarle = "pronunziarle";
		String unziarleStemmed = stemmer.stem(unziarle);
		System.out.println(unziarleStemmed);
		assertTrue(unziarleStemmed.equals("pronunz"));

		String unziato = "pronunziato";
		String unziatoStemmed = stemmer.stem(unziato);
		System.out.println(unziatoStemmed);
		assertTrue(unziatoStemmed.equals("pronunz"));

	}

}

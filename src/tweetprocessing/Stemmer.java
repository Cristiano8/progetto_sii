package tweetprocessing;

import java.text.Normalizer;

/*Used to stem a word and to eliminate stopwords*/
public class Stemmer {

	StopWord sW = new StopWord();

	/*Veramente rozzo*/
	public String stem(String s){
		String stemmed = s;

		//STEP 0.
		//Delete the accented letter
		stemmed = Normalizer.normalize(stemmed, Normalizer.Form.NFD);
		stemmed = stemmed.replaceAll("[^\\p{ASCII}]", "");


		//STEP 0.1 
		//Eliminiate the stopwords
		//stopWordList.add(sW.stopWordArray);
		if(sW.getStopWordList().contains(s) || s.length() <= 1) {
			return stemmed = null;
		}


		String suffixed = "";

		//Search for RV 
		String sV = "";
		String sPreV = "";
		boolean rV = false;
		if(stemmed.charAt(1) != 'a' && stemmed.charAt(1) != 'e' && stemmed.charAt(1) != 'i' 
				&& stemmed.charAt(1) != 'o' && stemmed.charAt(1) !=  'u'){
			for(int i = 2; i < stemmed.length() && !rV; i++){
				if(stemmed.charAt(i) == 'a' || stemmed.charAt(i) == 'e' || stemmed.charAt(i) == 'i' 
						|| stemmed.charAt(i) == 'o' || stemmed.charAt(i) ==  'u'){
					sPreV = stemmed.substring(0, i+1);
					sV = stemmed.substring(i+1, stemmed.length());
					rV = true;
				}
			}

		} else if((stemmed.charAt(0) == 'a' || stemmed.charAt(0) == 'e' || stemmed.charAt(0) == 'i' 
				|| stemmed.charAt(0) == 'o' || stemmed.charAt(0) ==  'u') && stemmed.charAt(1) == 'a' || stemmed.charAt(1) == 'e' 
				|| stemmed.charAt(1) == 'i' || stemmed.charAt(1) == 'o' || stemmed.charAt(1) ==  'u'){
			for(int i = 2; i < stemmed.length() && !rV; i++){
				if(stemmed.charAt(i) != 'a' || stemmed.charAt(i) != 'e' || stemmed.charAt(i) != 'i' 
						|| stemmed.charAt(i) != 'o' || stemmed.charAt(i) !=  'u'){
					sPreV = stemmed.substring(0, i+1);
					sV = stemmed.substring(i+1, stemmed.length());
					rV = true;
				}
			}
		} else {
			if (stemmed.length() >= 3) {
				sPreV = stemmed.substring(0, 3);
				sV = stemmed.substring(3, stemmed.length());
			}
		}

		//STEP 1.
		//Starting to delete some suffixes. If some of these follow one of 'ando endo' delete, else if they follow 'ar er ir' substitute with e.
		//I have to divide the conditions based on the length of the suffixes to control if the follow one of the sequences told.

		//Length of the suffixes: 6.
		if(rV){
			if(sV.endsWith("gliela") || sV.endsWith("gliele") || sV.endsWith("glieli") || sV.endsWith("glielo")
					|| sV.endsWith("gliene") ){
				suffixed = sV.substring(0, sV.length()-6);
			}

			//Length of the suffixes: 4.
			if(sV.endsWith("sene") || sV.endsWith("mela") || sV.endsWith("mele") || sV.endsWith("mele")
					|| sV.endsWith("mene") || sV.endsWith("tela") || sV.endsWith("tele") || sV.endsWith("teli")
					|| sV.endsWith("telo") || sV.endsWith("tene") || sV.endsWith("cela") || sV.endsWith("cele")
					|| sV.endsWith("celi") || sV.endsWith("celo") || sV.endsWith("cene") || sV.endsWith("vela")
					|| sV.endsWith("vele") || sV.endsWith("veli") || sV.endsWith("velo") || sV.endsWith("vene")){
				suffixed = sV.substring(0, sV.length()-4);
			}

			//Length of the suffixes: 3.
			if(sV.endsWith("gli")) {
				suffixed = sV.substring(0, sV.length()-3);
			}


			//Length of the suffixes: 2.
			if(sV.endsWith("ci")  || sV.endsWith("la") || sV.endsWith("le")
					|| sV.endsWith("li") || sV.endsWith("lo") || sV.endsWith("mi") || sV.endsWith("ne")
					|| sV.endsWith("si") || sV.endsWith("ti") || sV.endsWith("vi")) {

				suffixed = sV.substring(0, sV.length()-2);		
			}

			//Control which strings the suffixes follow. I save again in sV cause i must control it at the step3
			if(suffixed.endsWith("ando") || suffixed.endsWith("endo")){
				sV = suffixed;
			} else if(suffixed.endsWith("ar") || suffixed.endsWith("er") || suffixed.endsWith("ir")){
				sV = suffixed + "e";
			} else {

			}
		}


		//STEP 2. Standard suffix removal.
		//Delete suffixes if they are in R1(region after the first non-vowel following a vowel)
		//Search for R1
		String sR1 = "";
		String sPreR1 = "";
		boolean r1 = false;
		for(int i = 0;i < stemmed.length() && !r1; i++){
			if(stemmed.charAt(i) == 'a' || stemmed.charAt(i) == 'e' || stemmed.charAt(i) == 'i' 
					|| stemmed.charAt(i) == 'o' || stemmed.charAt(i) ==  'u'){
				for(int j = i+1; j < stemmed.length() && !r1; j++){
					if(stemmed.charAt(j) != 'a' && stemmed.charAt(j) != 'e' && stemmed.charAt(j) != 'i' 
							&& stemmed.charAt(j) != 'o' && stemmed.charAt(j) !=  'u'){

						sPreR1 = stemmed.substring(0, j+1);
						sR1 = stemmed.substring(j+1, stemmed.length());
						r1 = true;
					}
				}
			}
		}

		//Search for R2(region after the first non-vowel following a vowel in R1)
		String sR2 = "";
		String sPreR2 = "";
		boolean r2 = false;
		for(int i = 0;i < sR1.length() && !r2; i++){
			if(sR1.charAt(i) == 'a' || sR1.charAt(i) == 'e' || sR1.charAt(i) == 'i' 
					|| sR1.charAt(i) == 'o' || sR1.charAt(i) ==  'u'){
				for(int j = i+1; j < sR1.length() && !r2; j++){
					if(sR1.charAt(j) != 'a' && sR1.charAt(j) != 'e' && sR1.charAt(j) != 'i' 
							&& sR1.charAt(j) != 'o' && sR1.charAt(j) !=  'u'){

						sR2 = sR1.substring(j+1, sR1.length());
						sPreR2 = sR1.substring(0, j+1);
						r2 = true;
					}
				}
			}
		}

		//If i found r1 the search for the suffixes to delete or to replace

		boolean step2Applied = false;

		if(r1) {

			//Delete 'amente' if preceded by 'abil' 'ic' 'iv' 'os'
			if(sR1.endsWith("amente")){
				sR1 = sR1.substring(0, sR1.length()-6);
				step2Applied = true;
			}
			if(step2Applied){
				stemmed = sPreR1 + sR1;
			}


		}


		//If i found r2 then search for the suffixes to delete or to replace
		if(r2) {


			//Suffixes to delete
			//Length of the suffixes: 6.
			if(sR2.endsWith("atrice") || sR2.endsWith("atrici") ) {
				sR2 = sR2.substring(0, sR2.length()-6);
				step2Applied = true;
			}

			//Length of the suffixes: 5.
			if(sR2.endsWith("abile") || sR2.endsWith("abili") || sR2.endsWith("ibili") || sR2.endsWith("mente")) {
				sR2 = sR2.substring(0, sR2.length()-5);
				step2Applied = true;
			}

			//Length of the suffixes: 4.
			if(sR2.endsWith("anza") || sR2.endsWith("anze") || sR2.endsWith("iche") || sR2.endsWith("ichi") || sR2.endsWith("ismo") 
					|| sR2.endsWith("ismi") || sR2.endsWith("ista") || sR2.endsWith("iste") || sR2.endsWith("isti")
					|| sR2.endsWith("ante") || sR2.endsWith("anti")) {
				sR2 = sR2.substring(0, sR2.length()-4);
				step2Applied = true;
			}

			//Length of the suffixes: 3.
			if(sR2.endsWith("ico") || sR2.endsWith("ici") || sR2.endsWith("ica") || sR2.endsWith("ice") || sR2.endsWith("oso") 
					|| sR2.endsWith("osi") || sR2.endsWith("osa") || sR2.endsWith("ose")){
				sR2 = sR2.substring(0, sR2.length()-3);
				step2Applied = true;
			}



			//Suffixes to delete if preceded by 'ic'
			//Length of the suffixes: 6.	
			if(sR2.endsWith("azione") || sR2.endsWith("azioni") ){
				suffixed = sR2.substring(0, sR2.length()-6);
				step2Applied = true;
			}

			//Length of the suffixes: 5.	
			if(sR2.endsWith("atori") || sR2.endsWith("atore") ){
				suffixed = sR2.substring(0, sR2.length()-5);
				step2Applied = true;
			}

			//Control if preceded by 'ic'
			if(suffixed.endsWith("ic")){
				stemmed = suffixed;
				step2Applied = true;
			}



			//Replace 'logia' 'logie' with 'log'
			if(sR2.endsWith("logia") || sR2.endsWith("logie")){
				sR2 = sR2.substring(0, sR2.length()-2);
				step2Applied = true;
			}


			//Replace 'uzione' 'uzioni' 'usione' 'usioni' whit 'u'
			if(sR2.endsWith("uzioni") || sR2.endsWith("uzione") || sR2.endsWith("usioni") || sR2.endsWith("usione")){
				sR2 = sR2.substring(0, sR2.length()-5);
				step2Applied = true;
			}


			//Replace 'enze' 'enza' with 'ente'
			if(sR2.endsWith("enze") || sR2.endsWith("enza")){
				sR2 = sR2.substring(0, sR2.length()-2) + "te";
				step2Applied = true;
			}


			//Delete 'ita' if preceded by 'abil' 'ic' 'iv'
			if(sR2.endsWith("ita")){
				suffixed = sR2.substring(0, sR2.length()-3);
				if(suffixed.endsWith("abil") || suffixed.endsWith("ic") || suffixed.endsWith("iv")){
					stemmed = suffixed;
					step2Applied = true;
				}
			}

			//Delete 'amento' 'amenti' 'imento' 'imenti'
			if(sR2.endsWith("amento") || sR2.endsWith("amenti") || sR2.endsWith("imento") || sR2.endsWith("imenti")){
				sR2 = sR2.substring(0, sR2.length()-6);
				step2Applied = true;
			}

			//Delete 'amente' if preceded by 'abil' 'ic' 'iv' 'os'
			if(sR2.endsWith("amente")){
				suffixed = sR2.substring(0, sR2.length()-6);
				if(suffixed.endsWith("at") || suffixed.endsWith("ic") || suffixed.endsWith("abil") || suffixed.endsWith("os")){
					stemmed = suffixed;
					step2Applied = true;
				}
			}



			//Delete 'ivo' 'iva' 'ivi' 'ive' if preceded by 'at' 'ic'
			if(sR2.endsWith("ivo") || sR2.endsWith("ivi") || sR2.endsWith("iva") || sR2.endsWith("ive")){
				suffixed = sR2.substring(0, sR2.length()-3);
				if(suffixed.endsWith("at") || suffixed.endsWith("ic")){
					stemmed = suffixed;
					step2Applied = true;
				}
			}


			if(step2Applied){
				stemmed = sPreR1 + sPreR2 + sR2;
			}
		}



		//STEP 3. Only if step2 hasn't removed anything.
		//Remove suffixes from rV.

		boolean step3Applied = false;

		if(rV && !step2Applied){

			//Length of the suffixes: 6:
			if(sV.endsWith("assero") || sV.endsWith("assimo") || sV.endsWith("eranno") || sV.endsWith("erebbe")
					|| sV.endsWith("eremmo") || sV.endsWith("ereste") || sV.endsWith("eresti") || sV.endsWith("essero")
					|| sV.endsWith("iranno") || sV.endsWith("iranno") || sV.endsWith("irebbe") || sV.endsWith("iremmo")
					|| sV.endsWith("ireste") || sV.endsWith("iresti") || sV.endsWith("iscano") || sV.endsWith("iscono")
					|| sV.endsWith("iscono") || sV.endsWith("issero")){
				sV = sV.substring(0, sV.length()-6);
				step3Applied = true;
			}

			//Length of the suffixes: 5.
			if(sV.endsWith("assimo") || sV.endsWith("arono") || sV.endsWith("avano") || sV.endsWith("avate")
					|| sV.endsWith("eremo") || sV.endsWith("erete") || sV.endsWith("evamo") || sV.endsWith("evano")
					|| sV.endsWith("iremo") || sV.endsWith("irete") || sV.endsWith("ivamo") || sV.endsWith("ivano")){
				sV = sV.substring(0, sV.length()-5);
				step3Applied = true;
			}

			//Length of the suffixes: 4.
			if(sV.endsWith("ammo") || sV.endsWith("ando") || sV.endsWith("asse") || sV.endsWith("assi")
					|| sV.endsWith("emmo") || sV.endsWith("enda") || sV.endsWith("ende") || sV.endsWith("endi")
					|| sV.endsWith("endo") || sV.endsWith("erai") || sV.endsWith("erei") || sV.endsWith("yamo")
					|| sV.endsWith("iamo") || sV.endsWith("immo") || sV.endsWith("ieri") || sV.endsWith("isca")
					|| sV.endsWith("isce") || sV.endsWith("isci") || sV.endsWith("isco")){
				sV = sV.substring(0, sV.length()-4);
				step3Applied = true;
			}

			//Length of the suffixes: 3.
			if(sV.endsWith("ano") || sV.endsWith("are") || sV.endsWith("ata") || sV.endsWith("ate")
					|| sV.endsWith("ati") || sV.endsWith("ato") || sV.endsWith("ava") || sV.endsWith("avi")
					|| sV.endsWith("avo") || sV.endsWith("ere") || sV.endsWith("era") || sV.endsWith("ero")
					|| sV.endsWith("ete") || sV.endsWith("eva") || sV.endsWith("evi") || sV.endsWith("evo")
					|| sV.endsWith("ira") || sV.endsWith("ire") || sV.endsWith("ita") || sV.endsWith("ite")
					|| sV.endsWith("iti") || sV.endsWith("ito") || sV.endsWith("iva") || sV.endsWith("ivi")
					|| sV.endsWith("ivo") || sV.endsWith("ono") || sV.endsWith("uta") || sV.endsWith("ute")
					|| sV.endsWith("uti") || sV.endsWith("uto")){
				sV = sV.substring(0, sV.length()-3);
				step3Applied = true;
			}


			//Length of the suffixes: 2.
			if(sV.endsWith("ir")  || sV.endsWith("ar")){
				sV = sV.substring(0, sV.length()-2);	
				step3Applied = true;
			}

			if(step3Applied){
				stemmed = sPreV + sV;
			}

		}



		//STEP 4a.
		//Delete a e i o. Delete i preceding those letters.
		if(stemmed.endsWith("a") || stemmed.endsWith("e") || stemmed.endsWith("i") || stemmed.endsWith("o")){
			stemmed = stemmed.substring(0, stemmed.length()-1);

			if(stemmed.endsWith("i")){
				stemmed = stemmed.substring(0, stemmed.length()-1);
			}

		}



		//STEP4b.
		//Replace ch (gh) with c (g).
		if(stemmed.endsWith("ch")){
			stemmed = stemmed.substring(0, stemmed.length()-1);
		}

		if(stemmed.endsWith("gh")){
			stemmed = stemmed.substring(0, stemmed.length()-1);
		}


		return stemmed;
	}

}

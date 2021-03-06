package classifier;


import weka.core.Stopwords;
import weka.core.stopwords.StopwordsHandler;

public class StopWordsHandler implements StopwordsHandler {

	private Stopwords stopWordList;

	public StopWordsHandler() {
		this.stopWordList = new Stopwords();
		this.addStopWords();
	}

	@Override
	public boolean isStopword(String word) {
		return stopWordList.is(word);
	}

	private void addStopWords() {
		stopWordList.add("ad");
		stopWordList.add("al");
		stopWordList.add("allo");
		stopWordList.add("ai");
		stopWordList.add("agli");
		stopWordList.add("all");
		stopWordList.add("agl");
		stopWordList.add("alla");
		stopWordList.add("alle");
		stopWordList.add("con");
		stopWordList.add("col");
		stopWordList.add("coi");
		stopWordList.add("da");
		stopWordList.add("dal");
		stopWordList.add("dallo");
		stopWordList.add("dai");
		stopWordList.add("dagli");
		stopWordList.add("dall");
		stopWordList.add("dagl");
		stopWordList.add("dalla");
		stopWordList.add("dalle");
		stopWordList.add("di");
		stopWordList.add("del");
		stopWordList.add("dello");
		stopWordList.add("dai");
		stopWordList.add("degli");
		stopWordList.add("dell");
		stopWordList.add("degl");
		stopWordList.add("della");
		stopWordList.add("delle");
		stopWordList.add("in");
		stopWordList.add("nel");		
		stopWordList.add("nello");
		stopWordList.add("nei");
		stopWordList.add("negli");
		stopWordList.add("nell");
		stopWordList.add("negl");
		stopWordList.add("nella");
		stopWordList.add("nelle");
		stopWordList.add("su");
		stopWordList.add("sul");
		stopWordList.add("sullo");
		stopWordList.add("sui");
		stopWordList.add("sugli");
		stopWordList.add("sull");
		stopWordList.add("sugl");
		stopWordList.add("sulla");
		stopWordList.add("sulle");
		stopWordList.add("per");
		stopWordList.add("tra");
		stopWordList.add("contro");
		stopWordList.add("io");
		stopWordList.add("tu");
		stopWordList.add("lui");
		stopWordList.add("lei");
		stopWordList.add("noi");
		stopWordList.add("voi");
		stopWordList.add("loro");
		stopWordList.add("mio");
		stopWordList.add("mia");
		stopWordList.add("miei");
		stopWordList.add("mie");
		stopWordList.add("tuo");
		stopWordList.add("tua");
		stopWordList.add("tuoi");
		stopWordList.add("tue");
		stopWordList.add("suo");
		stopWordList.add("sua");	
		stopWordList.add("suoi");
		stopWordList.add("sue");
		stopWordList.add("nostro");
		stopWordList.add("nostra");
		stopWordList.add("nostri");
		stopWordList.add("nostre");
		stopWordList.add("vostro");
		stopWordList.add("vostra");
		stopWordList.add("vostri");
		stopWordList.add("vostre");
		stopWordList.add("mi");
		stopWordList.add("ti");
		stopWordList.add("ci");
		stopWordList.add("vi");
		stopWordList.add("lo");
		stopWordList.add("la");
		stopWordList.add("li");
		stopWordList.add("le");
		stopWordList.add("gli");	
		stopWordList.add("ne");
		stopWordList.add("il");
		stopWordList.add("un");
		stopWordList.add("una");
		stopWordList.add("ma");
		stopWordList.add("ed");
		stopWordList.add("se");
		stopWordList.add("perche");
		stopWordList.add("anche");
		stopWordList.add("come");
		stopWordList.add("dov");
		stopWordList.add("dove");
		stopWordList.add("che");
		stopWordList.add("chi");
		stopWordList.add("cui");
		stopWordList.add("non");
		stopWordList.add("piu");
		stopWordList.add("quale");
		stopWordList.add("quanto");
		stopWordList.add("quanti");
		stopWordList.add("quanta");
		stopWordList.add("quante");
		stopWordList.add("quello");
		stopWordList.add("quelli");	
		stopWordList.add("quella");
		stopWordList.add("quelle");
		stopWordList.add("questo");
		stopWordList.add("questi");
		stopWordList.add("questa");
		stopWordList.add("queste");
		stopWordList.add("si");
		stopWordList.add("tutto");
		stopWordList.add("tutti");
		stopWordList.add("a");
		stopWordList.add("b");
		stopWordList.add("c");
		stopWordList.add("d");
		stopWordList.add("e");
		stopWordList.add("f");
		stopWordList.add("g");
		stopWordList.add("h");
		stopWordList.add("i");
		stopWordList.add("j");
		stopWordList.add("k");
		stopWordList.add("l");
		stopWordList.add("m");
		stopWordList.add("n");
		stopWordList.add("o");
		stopWordList.add("p");
		stopWordList.add("q");
		stopWordList.add("r");
		stopWordList.add("s");
		stopWordList.add("t");
		stopWordList.add("u");
		stopWordList.add("v");
		stopWordList.add("w");
		stopWordList.add("x");
		stopWordList.add("y");
		stopWordList.add("z");
		stopWordList.add("ho");
		stopWordList.add("hai");
		stopWordList.add("abbiamo");
		stopWordList.add("avete");
		stopWordList.add("hanno");
		stopWordList.add("abbia");
		stopWordList.add("abbiate");
		stopWordList.add("abbiano");
		stopWordList.add("avro");	
		stopWordList.add("avrai");
		stopWordList.add("avra");
		stopWordList.add("avremo");
		stopWordList.add("avrete");
		stopWordList.add("avranno");
		stopWordList.add("avrei");
		stopWordList.add("avresti");
		stopWordList.add("avrebbe");
		stopWordList.add("avremmo");
		stopWordList.add("avreste");
		stopWordList.add("avrebbero");
		stopWordList.add("avevo");
		stopWordList.add("avevi");
		stopWordList.add("aveva");
		stopWordList.add("avevamo");
		stopWordList.add("avevate");
		stopWordList.add("avevano");
		stopWordList.add("ebbi");
		stopWordList.add("avesti");
		stopWordList.add("evve");
		stopWordList.add("avemmo");
		stopWordList.add("aveste");
		stopWordList.add("ebbero");
		stopWordList.add("avessi");	
		stopWordList.add("avesse");
		stopWordList.add("avessimo");
		stopWordList.add("avessero");
		stopWordList.add("avendo");
		stopWordList.add("avuto");
		stopWordList.add("avuta");
		stopWordList.add("avuti");
		stopWordList.add("avute");
		stopWordList.add("sono");
		stopWordList.add("sei");
		stopWordList.add("e");
		stopWordList.add("siamo");
		stopWordList.add("siete");
		stopWordList.add("sia");
		stopWordList.add("siate");
		stopWordList.add("siano");
		stopWordList.add("saro");
		stopWordList.add("sarai");
		stopWordList.add("sara");
		stopWordList.add("saremo");
		stopWordList.add("sarete");
		stopWordList.add("saranno");
		stopWordList.add("sarei");
		stopWordList.add("saresti");	
		stopWordList.add("sarebbe");
		stopWordList.add("saremmo");
		stopWordList.add("sareste");
		stopWordList.add("sarebbero");
		stopWordList.add("ero");
		stopWordList.add("eri");
		stopWordList.add("era");
		stopWordList.add("eravamo");
		stopWordList.add("eravate");
		stopWordList.add("erano");
		stopWordList.add("fui");
		stopWordList.add("fosti");
		stopWordList.add("fu");
		stopWordList.add("fummo");
		stopWordList.add("foste");
		stopWordList.add("furono");
		stopWordList.add("fossi");
		stopWordList.add("fosse");
		stopWordList.add("fossimo");
		stopWordList.add("fossero");
		stopWordList.add("essendo");
		stopWordList.add("faccio");
		stopWordList.add("fai");
		stopWordList.add("facciamo");	
		stopWordList.add("faccia");
		stopWordList.add("facciate");
		stopWordList.add("facciano");
		stopWordList.add("faro");
		stopWordList.add("farai");
		stopWordList.add("fara");
		stopWordList.add("faremo");
		stopWordList.add("farete");
		stopWordList.add("faranno");
		stopWordList.add("farei");
		stopWordList.add("faresti");
		stopWordList.add("farebbe");
		stopWordList.add("faremmo");
		stopWordList.add("fareste");
		stopWordList.add("foste");
		stopWordList.add("farebbero");
		stopWordList.add("facevo");
		stopWordList.add("facevi");
		stopWordList.add("faceva");
		stopWordList.add("facevamo");
		stopWordList.add("facevate");
		stopWordList.add("facevano");
		stopWordList.add("feci");
		stopWordList.add("facesti");
		stopWordList.add("fece");
		stopWordList.add("facemmo");
		stopWordList.add("faceste");
		stopWordList.add("fecero");
		stopWordList.add("facessi");
		stopWordList.add("facesse");
		stopWordList.add("facessimo");
		stopWordList.add("facessero");
		stopWordList.add("facendo");
		stopWordList.add("sto");
		stopWordList.add("stai");
		stopWordList.add("sta");
		stopWordList.add("stiamo");
		stopWordList.add("stanno");
		stopWordList.add("stia");
		stopWordList.add("stiate");
		stopWordList.add("stiano");
		stopWordList.add("staro");
		stopWordList.add("starai");
		stopWordList.add("stara");
		stopWordList.add("staremo");
		stopWordList.add("starete");
		stopWordList.add("staranno");
		stopWordList.add("starei");	
		stopWordList.add("staresti");
		stopWordList.add("starebbe");
		stopWordList.add("starebbero");
		stopWordList.add("staremmo");
		stopWordList.add("stareste");
		stopWordList.add("starebbero");
		stopWordList.add("stavo");
		stopWordList.add("stavi");
		stopWordList.add("stava");
		stopWordList.add("stavamo");
		stopWordList.add("stavate");
		stopWordList.add("stavano");
		stopWordList.add("stetti");
		stopWordList.add("stette");
		stopWordList.add("stemmo");
		stopWordList.add("steste");
		stopWordList.add("stettero");
		stopWordList.add("stessi");
		stopWordList.add("starai");
		stopWordList.add("stesse");
		stopWordList.add("stessimo");
		stopWordList.add("stessero");
		stopWordList.add("stando");

	}

}

package classifier;


import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.meta.FilteredClassifier;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.tokenizers.NGramTokenizer;
import weka.filters.unsupervised.attribute.StringToWordVector;

public class WekaClassifier {


	public PredictionResult getBaselineClassification(int cont) throws Exception {
		
		PredictionResult result = new PredictionResult();
		
		DataSource source = new DataSource("train/tweet-train.arff");
		Instances train = source.getDataSet();
		train.setClassIndex(train.numAttributes() - 1);
		
		DataSource dest = new DataSource("predict/tweets.arff");
		Instances test = dest.getDataSet();
		test.setClassIndex(test.numAttributes() - 1);

		NaiveBayes naive = new NaiveBayes();
		FilteredClassifier fc = new FilteredClassifier();
		NGramTokenizer tokenizer = new NGramTokenizer();
		tokenizer.setNGramMinSize(1);
		tokenizer.setNGramMaxSize(1);
		tokenizer.setDelimiters("\\W");

		//multiFilter.
		StringToWordVector filter = new StringToWordVector();
		filter.setInputFormat(train);
		filter.setOutputWordCounts(true);
		filter.setTFTransform(true);
		filter.setIDFTransform(true);
		filter.setTokenizer(tokenizer);
		filter.setWordsToKeep(1000000);
		filter.setDoNotOperateOnPerClassBasis(true); 
		filter.setStopwordsHandler(new StopWordsHandler());

		fc.setFilter(filter);
		fc.setClassifier(naive);
		// train and make predictions
		fc.buildClassifier(train);

		double[] rate = fc.distributionForInstance(test.instance(0));

		double pred = fc.classifyInstance(test.instance(0));
		result.setRate(rate);
		result.setClassPredicted(test.classAttribute().value((int) pred));


		return result;
	}


}

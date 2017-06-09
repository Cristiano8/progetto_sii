package classifier;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import util.Constants;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.meta.FilteredClassifier;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.tokenizers.NGramTokenizer;
import weka.filters.unsupervised.attribute.StringToWordVector;

public class WekaClassifier {

	private Instances train;
	private Instances test;
	private FilteredClassifier fc;


	public List<PredictionResult> getBaselineClassification() throws Exception {

		List<PredictionResult> result = new ArrayList<PredictionResult>();

		double[] rate;
		double pred;


		for (int i = 0; i<test.numInstances(); i++) {

			rate = fc.distributionForInstance(test.instance(i));

			pred = fc.classifyInstance(test.instance(i));

			//System.out.println(test.instance(i).toString() + " | " + rate[0] + " | " + rate[1] + " | " + pred);

			result.add(new PredictionResult((int)pred, rate));
		}

		return result;
	}

	public void initClassifier() throws Exception {

		DataSource source = new DataSource(Constants.TRAINING_PATH);
		this.train = source.getDataSet();
		this.train.setClassIndex(train.numAttributes() - 1);

		DataSource dest = new DataSource(Constants.PREDICT_PATH);
		this.test = dest.getDataSet();
		this.test.setClassIndex(test.numAttributes() - 1);
		
		this.evaluate();
		
		NaiveBayes naive = new NaiveBayes();
		this.fc = new FilteredClassifier();
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
	}

	private void evaluate() throws Exception {
		
		NaiveBayes naive = new NaiveBayes();
		FilteredClassifier classifierForEvaluation = new FilteredClassifier();
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
//		filter.setStopwordsHandler(new StopWordsHandler());

		classifierForEvaluation.setFilter(filter);
		classifierForEvaluation.setClassifier(naive);
		
		Evaluation eval = new Evaluation(this.train);
		eval.crossValidateModel(classifierForEvaluation, train, 10, new Random(1));
		
		System.out.println("Estimated accuracy: " + eval.pctCorrect());
		
	}


}

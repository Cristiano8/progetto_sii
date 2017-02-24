package connection;


import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterConnection {
	
	private ConfigurationBuilder cb;
	private Twitter twitter;

	public TwitterConnection() {
		this.cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		.setOAuthConsumerKey("mMabn7RCiIi8bvqAhEpGexzgR")
		.setOAuthConsumerSecret("Vy7QWIndaqDv3s4S7AHVhtTqnWI1ok0MXgAgZqx1UvkuSmtguT")
		.setOAuthAccessToken("823910390597906432-vSQqv0u3zUC0zri5ZBDfu2PbG0ZEYKW")
		.setOAuthAccessTokenSecret("Zqu4sWBqSE6XqrhaN1ciAVSFzlaOI2Zv8eVyQZqUB9oEF");
		this.twitter = new TwitterFactory(cb.build()).getInstance();
	}

	public Twitter getTwitter() {
		return twitter;
	}

	
}

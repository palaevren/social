package com.pala.social;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.PropertyResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Hello world!
 *
 */
public class App 
{
	private static Logger LOGGER = LoggerFactory.getLogger(App.class);
    public static void main( String[] args )
    {
    	System.out.println( "Hello World! Data collector for Twitter is statrted");
    	Properties props = new Properties();
    	try{
	    	props.load(App.class.getClassLoader().getResourceAsStream("twitter4j.properties"));
	        Twitter twitter = new TwitterFactory().getInstance();
	//        twitter.setOAuthConsumer("DAJAPKwXfpDOf8hsJQbUSw", "7iO8xKIVSMUoE3t1dCJZktskq79Q4UH16ogWDLkdWCI");
	        String accessToken = props.getProperty("oauth.accessToken");
	        String accessTokenSecret = props.getProperty("oauth.accessTokenSecret");
	        twitter.setOAuthAccessToken(new AccessToken(accessToken, accessTokenSecret));
	        List<Status> statuses = null;
			try {
				statuses = twitter.getHomeTimeline();
			} catch (TwitterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        System.out.println("Showing home timeline.");
	        for (Status status : statuses) {
	            System.out.println(status.getUser().getName() + ":" +
	                               status.getText() + "class:" + status.getClass());
	        }
    	} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
    }
}

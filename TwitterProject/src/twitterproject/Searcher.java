package twitterproject;

import java.util.ArrayList;
import java.util.List;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class Searcher {

    public static int count;
    private File file;
    ConfigurationBuilder obj;
    ArrayList<Tweet> data = new ArrayList<Tweet>();
    String word;

    /*
    public Searcher() {
        this.obj = new ConfigurationBuilder();
          this.obj.setDebugEnabled(true)
                //key;
    }
     */

    public void search(String keyword) throws TwitterException {
        TwitterFactory tf = new TwitterFactory(obj.build());
        twitter4j.Twitter twitter = tf.getInstance();
        Query query = new Query(keyword);
        query.setCount(100);
        word = keyword;
        QueryResult result;
        result = twitter.search(query);
        List<Status> tweets = result.getTweets();
        for (Status tweet : tweets) {
            String info = "@" + tweet.getUser().getScreenName() + " -- " + tweet.getText() + " -- " + tweet.getCreatedAt();
            //System.out.println(info);
            data.add(new Tweet(tweet.getUser().getScreenName(), tweet.getCreatedAt(), tweet.getText()));
        }
    }

    public void printResult() {
        for (int i = 0; i < data.size(); i++) {
            System.out.println(data.get(i).getUsername()+" --- "+data.get(i).getText()+data.get(i).getDate());
            System.out.println("");
        }
        System.out.println("");
        System.out.println("Keyword : " + word);
        System.out.println("Amount : " + data.size());
    }
}

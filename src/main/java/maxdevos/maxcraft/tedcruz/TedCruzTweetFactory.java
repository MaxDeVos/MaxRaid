package maxdevos.maxcraft.tedcruz;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TedCruzTweetFactory {

    public static String getRandomTweetCommand(String user){
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("6PHZcxVNLzVJn1AQn4CJ6y0zm")
                .setOAuthConsumerSecret("J00WzhUZ6DggBHP8zy0nUVsrCtPkQavCqU16oAVuQC0vRRQ6Km")
                .setOAuthAccessToken("2608647180-4PDkMO01Rk7th2n1isfIRlLssJuD6EnMR5abSSY")
                .setOAuthAccessTokenSecret("DVjFRVbL49DwRfqm1DUSWPURXTfO6poohcVpA1EBWUQKC");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();

        try {
            Paging p = new Paging();
            p.setCount(9967);
            List<Status> statuses;
            statuses = twitter.getUserTimeline(user,p);
            ArrayList<String> tedTweets = new ArrayList<>();
            ArrayList<String> shortTedTweets = new ArrayList<>();
            for (Status status : statuses) {
                if(!status.getText().contains("RT")){
                    tedTweets.add(status.getText());
                }
            }

            for(String tweet:tedTweets){
                if(tweet.length() > 60){
                    shortTedTweets.add(tweet);
                }
            }

            int rnd = new Random().nextInt(shortTedTweets.size());
            return (removeUrl(shortTedTweets.get(rnd)).replace("\n",""));



        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
            System.exit(-1);
        }
        return "";

    }


    private static String removeUrl(String commentstr) {
    String urlPattern = "((https?|ftp|gopher|telnet|file|Unsure|http):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
    Pattern p = Pattern.compile(urlPattern,Pattern.CASE_INSENSITIVE);
    Matcher m = p.matcher(commentstr);
    int i = 0;
    while (m.find()) {
        commentstr = commentstr.replaceAll(m.group(i),"").trim();
        i++;
    }
    return commentstr;
}

}

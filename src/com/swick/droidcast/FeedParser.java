package com.swick.droidcast;

import java.util.concurrent.Executors;

import org.mcsoxford.rss.RSSFeed;
import org.mcsoxford.rss.RSSReader;
import org.mcsoxford.rss.RSSReaderException;

import android.content.Context;
import android.util.Log;

public class FeedParser {

	public void parseXml(Context c) {
		
		Executors.newSingleThreadExecutor().execute(new Runnable() {
			
			@Override
			public void run() {
				try {
					RSSReader reader = new RSSReader();
					RSSFeed feed = reader.load("http://feeds.thisamericanlife.org/talpodcast");
					Log.d("FeedParser", feed.getTitle());
				} catch (RSSReaderException e) {
					throw new RuntimeException(e);
				}
			}
		});
	}
}

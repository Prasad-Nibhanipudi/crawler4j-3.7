import org.apache.log4j.Logger;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;


public class RICrawltest {

	public static void main(String[] args) throws Exception {
		Logger.getRootLogger().getLoggerRepository().resetConfiguration();
		CrawlConfig config = new CrawlConfig();
		config.setCrawlStorageFolder("/home/psubbu/crawlerTest");
		config.setFollowRedirects(true);
		config.setPolitenessDelay(100) ; // interval between crawl requests
		config.setMaxDepthOfCrawling(-1);  //default value
		config.setMaxPagesToFetch(1000000);  //default is -1, generally 1000000
		config.setUserAgentString("SG Crawler");
		config.setResumableCrawling(false);
		config.setMaxDownloadSize(4*1024*1024);
		PageFetcher pageFetcher = new PageFetcher(config);
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig,
				pageFetcher);
		CrawlController controller = new CrawlController(config, pageFetcher,
				robotstxtServer);
		controller.addSeed("http://www.seneca-global.com");

		/*
		 * Start the crawl. This is a non-blocking operation, meaning that your code
		 * will exit even before crawling is finished.
		 */

		controller.start(RICrawler.class, 5);
		
	}

}

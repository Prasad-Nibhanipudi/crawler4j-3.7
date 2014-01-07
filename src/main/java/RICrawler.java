/**
 * 
 */

import java.util.regex.Pattern;

import edu.uci.ics.crawler4j.crawler.*;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

/**
 * @author psubbu
 *
 */
public class RICrawler extends WebCrawler {

	private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|bmp|gif|jpe?g"
	+ "|pkg|png|tiff?|mid|mp2|mp3|mp4"
	+ "|flv|wav|avi|mov|mpeg|ram|m4v|pdf"
	+ "|rm|smil|wmv|swf|wma|zip|rar|gz))\\$"); 

	
	/**
	 * You should implement this function to specify whether the given url
	 * should be crawled or not (based on your crawling logic).
	 */
	@Override
	public boolean shouldVisit(WebURL url) {
		
		String href = url.getURL().toLowerCase();
		boolean toVisit  = ( 
								!FILTERS.matcher(href).matches() 
								
							);
		return toVisit  ;
	}
	
	/**
	 * This function is called when a page is fetched and ready to be processed
	 * by your program.
	 */
	@Override
	public void visit(Page page) {
		
		String url = page.getWebURL().getURL() ;
		String category = page.getWebURL().getPath(); 
		String pagName = "" ;
		if (page.getParseData() instanceof HtmlParseData) {
			HtmlParseData htmlParseData = (HtmlParseData) page.getParseData(); 
			pagName = htmlParseData.getTitle();
		}
	}

	@Override
	protected void handlePageStatusCode(WebURL webUrl, int statusCode, String statusDescription) {
		if (statusCode >= 300 && statusCode <= 400) {
			String url = webUrl.getURL();
				System.out.println("1:"+statusCode+":"+url);
			}
			
		
		
	}
	@Override
	protected void handlePageRedirects(WebURL webUrl, WebURL movedURL, String anchor, int statusCode, String statusDescription) {
		if (statusCode >= 200) {
			String url = webUrl.getURL();
				System.out.println("2:"+statusCode+":"+url);
			}
	}
	
	
}

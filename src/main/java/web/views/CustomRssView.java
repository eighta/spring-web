package web.views;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.feed.AbstractRssFeedView;

import com.rometools.rome.feed.rss.Channel;
import com.rometools.rome.feed.rss.Content;
import com.rometools.rome.feed.rss.Item;

public class CustomRssView extends AbstractRssFeedView{

	@Override
	protected List<Item> buildFeedItems(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {


//		List<SampleContent> listContent = (List<SampleContent>) model.get("feedContent");
		List<Item> items = new ArrayList<>();
		
//		for(SampleContent tempContent : listContent ){
		
			Item item = new Item();
			
			Content content = new Content();
			content.setValue("tempContent.getSummary()");
			item.setContent(content);
			
			item.setTitle("tempContent.getTitle()");
			item.setLink("tempContent.getUrl()");
			item.setPubDate(new Date());
			
			items.add(item);
//		}
		
		return items;
	}
	
	@Override
	protected void buildFeedMetadata(Map<String, Object> model, Channel feed,
		HttpServletRequest request) {
		
		feed.setTitle("FEED: Title");
		feed.setDescription("FEED: Description");
		feed.setLink("FEED: Link");
		
		super.buildFeedMetadata(model, feed, request);
	}

}

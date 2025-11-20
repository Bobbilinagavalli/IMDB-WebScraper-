package scraper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import model.Movie;

public class ImdbScraper {
	
		public List<Movie> scrapeTop250() throws IOException{
	
		String Url = "https://www.imdb.com/chart/top";
		Document doc = Jsoup.connect(Url)
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
		        .header("Accept-Language", "en-US,en;q=0.9")
		        .header("Accept-Encoding", "gzip, deflate, br")
		        .ignoreContentType(true)
		        .get();
		System.out.println("In ImdbScraper");
		List<Movie> movies = new ArrayList<>();
		for(Element row : doc.select("li.ipc-metadata-list-summary-item")) {
			//System.out.println(row);

			String h3text = row.select("h3.ipc-title__text").text();
			
			if(h3text.isEmpty()) continue;	

			String[] parts = h3text.split("\\. ", 2);
			if (parts.length < 2) continue;
			
			
			//Rank
			String rank = parts[0].trim();
			
			//Title
			String title = parts[1].trim();
			
			Element YearElements = row.select("span.cli-title-metadata-item").first();
			
			//Year
			int year = (YearElements != null) ?Integer.parseInt(YearElements.text()) : 0;
			
			//rating
			String rating = row.select("span.ipc-rating-star--rating").text();
	
			System.out.println(rank);
			movies.add(new Movie(rank,title,year,rating));
	
		}
			return movies;
		
		}
}

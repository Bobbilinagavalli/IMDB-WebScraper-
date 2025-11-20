import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import model.Movie;
import scraper.ImdbScraper;
import writer.ExcelWriter;

public class main {
	public static void main(String args[]) throws IOException {
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
		
		Runnable task = () ->{
			try {
		        ImdbScraper scraper = new ImdbScraper();
		        List<Movie> movies = scraper.scrapeTop250();
		    	System.out.println(movies);		
		        
		        ExcelWriter writer = new ExcelWriter();
		        writer.writeMoviesToExcel(movies, "C:\\MyProject\\CineScrape Excel\\IMDB_TOP25_MOVIES.xlsx");
			}catch(Exception e) {
				e.printStackTrace();
			}	
		};
		
		scheduler.scheduleAtFixedRate(task,0,1,TimeUnit.MINUTES);
	}
}

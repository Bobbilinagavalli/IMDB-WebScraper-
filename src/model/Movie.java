package model;

public class Movie {
	
	private String rank;
	private String title ;
	private int year;
	private String rating;

	

    public Movie(String rank, String title, int year, String rating) {
        this.rank = rank;
        this.title = title;
        this.year = year;
        this.rating = rating;
    }



	public String getRank() {
		return rank;
	}

	public String getTitle() {
		return title;
	}

	public int getYear() {
		return year;
	}


	public String getRating() {
		return rating;
	}


	@Override
	public String toString() {
		return "Movie [rank=" + rank + ", title=" + title + ", year=" + year + ", rating=" + rating + "]";
	}
	
	
}

package moviePages;

import baseClass.BaseClass;

public class ImdbPage extends BaseClass {

	public static String movieReleaseDate_IMDB;
	public static String countryName_IMDB;
	public static String movieName;

	public static void movieSearch() throws Throwable {

		movieName = repository.getProperty("MovieName");
		isDisplayed("imdbPage.SearchBar", "Search bar is not displayed");
		typeDataInTheElement("imdbPage.SearchBar", movieName);
		printInConsole("Entered movie name: " + movieName);
		
	}

	public static void selectMovieFromSearchList() throws Throwable {

		Thread.sleep(3000);
		isDisplayed("imdbPage.ListBox", "Suggestion box is not displayed");
		findElementandClick("imdbPage.ListBox", movieName);
		
	}

	public static void scrollToMovieDetails() throws Throwable {

		scrollToTheElement("imdbPage.Details");
		isDisplayed("imdbPage.Details", "Details section is not displayed");
		printInConsole("Scrolled to the movie details");

	}

	public static void retrieveMovieDetails() throws Throwable {

		getwebElement("imdbPage.Releasedate");
		movieReleaseDate_IMDB = getText("imdbPage.Releasedate");
		countryName_IMDB = getText("imdbPage.Country");

		printInConsole("Movie release date in imdb: " + movieReleaseDate_IMDB);
		printInConsole("Country of origin in imdb: " + countryName_IMDB);

	}

}

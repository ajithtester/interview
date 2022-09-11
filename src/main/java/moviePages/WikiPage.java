package moviePages;

import baseClass.BaseClass;

public class WikiPage extends BaseClass {

	public static String movieReleaseDate_WIKI;
	public static String countryName_WIKI;
	public static String movieName;

	public static void movieSearch() throws Throwable {

		movieName = repository.getProperty("MovieName");
		isDisplayed("Wikipage.SearchBar", "Search bar is not displayed");
		typeDataInTheElement("Wikipage.SearchBar", movieName);
		printInConsole("Entered movie name: " + movieName);

	}

	public static void selectMovieFromSearchList() throws Throwable {

		Thread.sleep(3000);
		isDisplayed("Wikipage.ListBox", "Suggestion box is not displayed");
		findElementandClick("Wikipage.ListBox", movieName);

	}

	public static void scrollToMovieDetails() throws Throwable {

		scrollToTheElement("Wikipage.Releasedate");
		isDisplayed("Wikipage.Releasedate", "Details section is not displayed");
		printInConsole("Scrolled to the movie details");

	}

	public static void retrieveMovieDetails() throws Throwable {

		movieReleaseDate_WIKI = getText("Wikipage.Releasedate");
		countryName_WIKI = getText("Wikipage.Country");

		printInConsole("Movie release date in wiki: " + movieReleaseDate_WIKI);
		printInConsole("Country of origin in wiki: " + countryName_WIKI);

	}

}

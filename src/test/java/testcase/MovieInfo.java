package testcase;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import moviePages.ImdbPage;
import moviePages.WikiPage;

public class MovieInfo extends BaseClass {

	@BeforeMethod()
	public static void preCondition() throws Throwable {

		try {
			loadpropertiesFile();
		} catch (Exception e) {
			printInConsole(e.getMessage());
		}

	}

	@Test(priority = 1)
	public static void navigateToImdbPage() throws Throwable {

		try {
			openBrowserAndLaunchURL("Browser", "ImdbURL");
		} catch (Exception e) {
			printInConsole(e.getMessage());
		}

	}

	@Test(priority = 2)
	public static void movieDetailsFromImdb() throws Throwable {

		try {
			ImdbPage.movieSearch();
			ImdbPage.selectMovieFromSearchList();
			ImdbPage.scrollToMovieDetails();
			ImdbPage.retrieveMovieDetails();
		} catch (Exception e) {
			printInConsole(e.getMessage());
		}

	}

	@Test(priority = 3)
	public static void navigateToWikiPage() throws Throwable {

		try {
			openBrowserAndLaunchURL("Browser", "WikiURL");
		} catch (Exception e) {
			printInConsole(e.getMessage());
		}

	}

	@Test(priority = 4)
	public static void movieDetailsFromWiki() throws Throwable {

		try {
			WikiPage.movieSearch();
			WikiPage.selectMovieFromSearchList();
			WikiPage.scrollToMovieDetails();
			WikiPage.retrieveMovieDetails();
		} catch (Exception e) {
			printInConsole(e.getMessage());
		}

	}

	@Test(priority = 5)
	public static void assertTheValues() throws Throwable {

		try {
			Assert.assertEquals(ImdbPage.countryName_IMDB, WikiPage.countryName_WIKI, "Countries are not same");
			printInConsole("Country in both source are same");

			Assert.assertEquals(ImdbPage.movieReleaseDate_IMDB, WikiPage.movieReleaseDate_WIKI,
					"Release dates are not same");
			printInConsole("Release date in both source are same");
		} catch (Exception e) {
			printInConsole(e.getMessage());
		}

	}
}

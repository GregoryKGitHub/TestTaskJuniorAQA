package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchResultPage;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BookingTest extends BaseTest   {
    private final String SEARCHINGCITY = "New York";
    private final Date CHECKINDATE = new Date(122, 11, 1);
    private final Date CHECKOUTDATE = new Date(122, 11, 31);
    private SearchResultPage searchResultPage;

    @BeforeClass
    public void setUpScenario() {
        HomePage homePage = new HomePage(driver);
        homePage.setSearchCity(SEARCHINGCITY);
        homePage.setCheckInDate(CHECKINDATE);
        homePage.setCheckOutDate(CHECKOUTDATE);
        searchResultPage = homePage.searchResults();
    }

    @Test
    public void cityAccordanceTest()    {
        List<String> addressList = searchResultPage.getCityResult();
        for(String address : addressList)   {
            Assert.assertTrue(address.contains(SEARCHINGCITY));
        }
    }

    //Check in and check out dates are missing at search page, we need to go deeply
    //into each result to get them. So I decided to check duration of booking.

    @Test
    public void durationAccordanceTest()    {
        long diffInMillies = Math.abs(CHECKOUTDATE.getTime() - CHECKINDATE.getTime());
        long durationExpected = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        List<String> durationList = searchResultPage.getDurationResult();
        for(String duration : durationList) {
            Assert.assertTrue(duration.contains(String.valueOf(durationExpected)));
        }
    }

    //My other scenario is to filter by review score 7+ and check if result is correct

    @Test
    public void reviewScoreTest()   {
        searchResultPage.setReviewScore7();
        List<Float> reviewScoreList = searchResultPage.getReviewScoreResult();
        for(Float reviewScore : reviewScoreList) {
            Assert.assertTrue(reviewScore >= 7.0);
        }
    }
}

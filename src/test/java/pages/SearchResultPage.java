package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class SearchResultPage {
    private final WebDriver driver;

    public SearchResultPage(WebDriver driver)   {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//div[@data-filters-item='review_score:review_score=70']//span[2]")
    private WebElement reviewScore7CheckBox;

    @FindBy(xpath = "//span[@data-testid='address']")
    private List<WebElement> addressList;

    @FindBy(xpath = "//div[@data-testid='price-for-x-nights']")
    private List<WebElement> durationList;

    @FindBy(xpath = "//div[@data-testid='review-score']/div[1]")
    private List<WebElement> reviewScoreList;

    public List<String> getCityResult()   {
        List<String> cities = new ArrayList<>();
        for(WebElement address : addressList)   {
            cities.add(address.getText());
        }
        return cities;
    }

    //Check in and check out dates are missing at search page, we need to go deeply
    //into each result to get them. So I decided to check duration of booking.

    public List<String> getDurationResult()   {
        List<String> durations = new ArrayList<>();
        for(WebElement duration : durationList)   {
            durations.add(duration.getText());
        }
        return durations;
    }

    //My other scenario is to filter by review score 7+ and check if result is correct

    public void setReviewScore7()   {
        reviewScore7CheckBox.click();
        driver.navigate().refresh();
    }

    public List<Float> getReviewScoreResult()   {
        List<Float> reviewScores = new ArrayList<>();
        for(WebElement reviewScore : reviewScoreList)   {
            reviewScores.add(Float.parseFloat(reviewScore.getText()));
        }
        return reviewScores;
    }
}

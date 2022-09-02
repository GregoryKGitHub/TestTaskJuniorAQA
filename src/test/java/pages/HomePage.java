package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utility.DatePickerFacade;

import java.util.Date;

public class HomePage {
    private final WebDriver driver;

    public HomePage(WebDriver driver)   {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//input[@type='search']")
    private WebElement searchField;

    @FindBy(xpath = "//div[@class='xp__dates xp__group']")
    private WebElement datePicker;

    @FindBy(xpath = "//button[@class='sb-searchbox__button ']")
    private WebElement searchBtn;

    public void setSearchCity(String city)  {
        searchField.sendKeys(city);
    }

    public void setCheckInDate(Date checkInDate)   {
        datePicker.click();
        CalendarPageComponent calendar = new CalendarPageComponent(driver, checkInDate);
        DatePickerFacade datePickerFacade = new DatePickerFacade(calendar);
        datePickerFacade.setDesiredDate();
    }

    public void setCheckOutDate(Date checkOutDate)  {
        CalendarPageComponent calendar = new CalendarPageComponent(driver, checkOutDate);
        DatePickerFacade datePickerFacade = new DatePickerFacade(calendar);
        datePickerFacade.setDesiredDate();
    }

    public SearchResultPage searchResults() {
        searchBtn.click();
        return new SearchResultPage(driver);
    }
}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CalendarPageComponent {
    private final WebDriver driver;
    private final Date date;
    private String year;
    private String month;
    private SimpleDateFormat formatter;

    public CalendarPageComponent(WebDriver driver, Date date)  {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.date = date;
    }

    @FindBy(xpath = "//div[@data-bui-ref='calendar-next']")
    private WebElement calendarNextBtn;

    @FindBy(xpath = "//div[@class='bui-calendar__month']")
    private WebElement monthField;

    public void setYear()    {
        formatter = new SimpleDateFormat("yyyy");
        year = formatter.format(date);
        while(!monthField.getText().contains(year))    {
            calendarNextBtn.click();
        }
    }

    public void setMonth()  {
        formatter = new SimpleDateFormat("MMMM", Locale.ENGLISH);
        month = formatter.format(date);
        while(!monthField.getText().contains(month))    {
            calendarNextBtn.click();
        }
    }

    public void setDay()  {
        formatter = new SimpleDateFormat("d");
        String day = formatter.format(date);
        driver.findElement(By.xpath(
                "//span[@aria-label='" + day + " " + month + " " + year + "']"))
                .click();
    }
}

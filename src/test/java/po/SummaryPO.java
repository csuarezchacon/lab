package po;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.List;

@Getter
public class SummaryPO {
    @FindAll(@FindBy(xpath = "//div[div[contains(text(),'Summary')]]//tr"))
    List<WebElement> summaryList;

    public SummaryPO(WebDriver driver) {
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 10);
        PageFactory.initElements(factory, this);
    }
}

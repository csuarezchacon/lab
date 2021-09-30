package po;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

@Getter
public class GadgetsnowPO {
    @FindBy(xpath = "//ul[@class='grid_wrapper']/li[.//text()='Add gadget to compare']")
    WebElement compareCard;
    @FindBy(xpath = "//button[text()='compare']")
    WebElement compareButton;

    public GadgetsnowPO(WebDriver driver) {
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 10);
        PageFactory.initElements(factory, this);
    }
}

package ru.bellIntegrator.page.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class GoogleSearch {
    public WebDriver driver;

    //поисковая строка
    @FindBy(how = How.NAME, name = "q")
    WebElement inputField;

    //список ссылкок в результатах поиска
    @FindAll(@FindBy(how=How.XPATH, using = "//*[@id='rso']//a"))
    List<WebElement> googleResults;

        public GoogleSearch(WebDriver driver){
            this.driver = driver;
        }

        public void search(String key){
            inputField.click();
            inputField.sendKeys(key);
            inputField.submit();
        }



    public List<WebElement> getGoogleResults() {
        return googleResults;
    }
}

package ru.bellIntegrator.page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GoogleAfterSearch extends GoogleBeforeSearch {
    //спиок результатов поиска
List<WebElement> googleResults;

public GoogleAfterSearch(WebDriver driver){
    super(driver);
    googleResults = driver.findElements(By.xpath("//*[@id='rso']//a"));

}

public List<WebElement> getGoogleResults(){
    return googleResults;
}



}

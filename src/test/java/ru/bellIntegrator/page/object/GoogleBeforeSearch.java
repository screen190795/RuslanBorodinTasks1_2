package ru.bellIntegrator.page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogleBeforeSearch {
    protected WebDriver driver;

    //строка поиска
    private WebElement inputField;


    public GoogleBeforeSearch(WebDriver driver){
        this.driver = driver;
        this.inputField = driver.findElement(By.name("q"));


    }

    //поиск по ключевому слову
     public void search(String key){
        inputField.click();
        inputField.sendKeys(key);
        inputField.submit();
    }
}

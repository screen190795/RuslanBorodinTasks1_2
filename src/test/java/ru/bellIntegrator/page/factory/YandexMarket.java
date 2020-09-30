package ru.bellIntegrator.page.factory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.*;
import java.util.stream.Collectors;

public class YandexMarket {
    private WebDriver driver;

     private String marketMainURL="https://market.yandex.ru/";

    //Вкладка главного меню "электроника"
   @FindBy(how= How.LINK_TEXT, using = "Электроника")
   public WebElement electroincsLink;

    //Вкладка "смартфоны"
   @FindBy(how=How.XPATH, using = "//body//a[text()=\"Смартфоны\"]")
    public WebElement smartphonesLink;

    //чекбокс- Производитель "Apple"
    @FindBy(how=How.XPATH, using = "//input[@type='checkbox'][@name='Производитель Apple']")
    public WebElement appleCheckBox;

    //кнопка в списке товаров "Показать ещё"
    @FindBy(how=How.XPATH, using = "//button[contains(text(),\"Показать ещё\")]")
    public WebElement showNextButton;

    //Название товара в результатах поиска
    @FindAll(@FindBy(how=How.XPATH, using = "//div[@data-zone-name=\"SearchResults\"]//article//a[contains(@title,'Смартфон')]"))
    public List<WebElement> productTitles;

    public YandexMarket(WebDriver driver) {
        this.driver = driver;
    }

    public void goToPage(WebElement linkElement){
        Actions builder = new Actions(driver);
        builder.moveToElement(linkElement).click(linkElement);
        Action mouseoverAndClick = builder.build();
        mouseoverAndClick.perform();
    }

    public void goToMainURL(){
        driver.get(marketMainURL);
    }

    public void  loadMore(){
        try {
            Thread.sleep(3000);
            this.goToPage(this.showNextButton);
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public List<String> collectTitles(){
        List<String> titles = this.productTitles.stream()
                .map(x->x.getText()).collect(Collectors.toList());
        titles.forEach(System.out::println);
        System.out.println(titles.size());
        return  titles;
    }
}

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


    //кнопка в списке товаров "Показать ещё"
    @FindBy(how=How.XPATH, using = "//button[contains(text(),\"Показать ещё\")]")
    public WebElement showNextButton;

    //Название товара в результатах поиска
    @FindAll(@FindBy(how=How.XPATH, using = "//div[@data-zone-name=\"SearchResults\"]//article//a/span"))
    public List<WebElement> productTitles;

    public YandexMarket(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToElement(WebElement linkElement){
        Actions builder = new Actions(driver);
        builder.moveToElement(linkElement).click(linkElement);
        Action mouseoverAndClick = builder.build();
        mouseoverAndClick.perform();
    }

    /*
     * Переход по вкладке основного меню
     */
    public void goByMainMenuTab(String tabName){
        try{
        WebElement tab = driver.findElement(By.linkText("Электроника"));
        this.clickToElement(tab);
        tab.click();
        }catch(org.openqa.selenium.NoSuchElementException n){
            System.out.println("Элементы не найден");
        }
    }


    /*
     * Выбор категории товара
     */
    public  void addCategory(String category) {
        try {
            WebElement categoryTab = driver.findElement(By.xpath("//body//a[text()=\"" + category + "\"]"));
            this.clickToElement(categoryTab);
        }catch(org.openqa.selenium.NoSuchElementException n){
                System.out.println("Элементы не найден");
            }
        }



    /*
     * Выбор фильтра "Производитель"
     */
    public void addMakerFilter(String maker){
        try {

            WebElement makerTab = driver.findElement(By.xpath("//input[@type='checkbox'][@name='Производитель " + maker + "']"));
            this.clickToElement(makerTab);
        } catch (org.openqa.selenium.NoSuchElementException n){System.out.println("Элементы не найден");}

    }

    public void goToMainURL(){
        driver.get(marketMainURL);
    }


    /*
     * Открытие всех товаров в списке
     */
    public void  loadPages()  {
        try {
        while (showNextButton.isDisplayed()) {
            this.clickToElement(this.showNextButton);
        }
            } catch (org.openqa.selenium.NoSuchElementException n){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return;
        }
    }

    /*
     * Сбор названий всех товаров в результате поиска
     */
    public List<String> collectTitles(){
        List<String> titles = this.productTitles.stream()
                .map(x->x.getText())
                .collect(Collectors.toList());
        return  titles;
    }

}

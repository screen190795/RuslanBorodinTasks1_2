package ru.bellIntegrator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import ru.bellIntegrator.page.factory.GoogleSearch;
import ru.bellIntegrator.page.object.GoogleAfterSearch;
import ru.bellIntegrator.page.object.GoogleBeforeSearch;
import ru.bellIntegrator.page.object.OpenRuMain;
import ru.bellIntegrator.page.object.SberbankASTMain;


import java.util.List;

import static java.util.stream.Collectors.toList;

public class Tests extends TestBase{

    @Test
    /*
     * проверка: наличие ссылки в результатах поиска
     */
    public  void  findGoogleResultsPO(){
        driver.get("http://www.google.com");
        GoogleBeforeSearch googleBeforeSearch = new GoogleBeforeSearch(driver);
        googleBeforeSearch.search("Гладиолус");
        GoogleAfterSearch googleAfterSearch = new GoogleAfterSearch(driver);
        Assertions.assertTrue(googleAfterSearch.getGoogleResults()
                .stream().anyMatch(x->x.getText().contains("Шпажник — Википедия")
                        && x.getAttribute("href").contains("ru.wikipedia.org")),"результаты не нейдены");

    }


    @Test
    /*
     * проверка: наличие ссылки в результатах поиска
     */
        public void findGoogleResultsPF(){
        driver.get("http://www.google.com");
        GoogleSearch googleSearch = PageFactory.initElements(driver,GoogleSearch.class);
        googleSearch.search("гладиолус");
        Assertions.assertTrue(googleSearch.getGoogleResults()
                .stream().anyMatch(x->x.getText().contains("Шпажник — Википедия")
                        && x.getAttribute("href").contains("ru.wikipedia.org")),"результаты не нейдены");
    }

    @Test
    /*
     * проверка: курс продажи доллара меньше курса покупки доллара
     */
    public void compareExchangeRate(){
        driver.get("http://www.google.com");
        GoogleSearch googleSearch = PageFactory.initElements(driver,GoogleSearch.class);
        googleSearch.search("открытие");
        driver.findElement(By.xpath("//div/a[@href='https://www.open.ru/']")).click();
        OpenRuMain openRuMain = new OpenRuMain(driver);
        List<Float> testRates = openRuMain.getUSDExchangeRates().stream()
                .map(WebElement::getText)
                .map(s -> s.replaceAll(",", "."))
                .map(Float::valueOf)
                .collect(toList());
        Assertions.assertTrue(testRates.get(0)<testRates.get(1),"некорректное отображение курса");
    }

    @Test
    public void SberbankMenu()
    {
        SberbankASTMain sberbankASTMain= new SberbankASTMain(driver);
        sberbankASTMain.goToPage();
       //List<WebElement> menuElements =  driver.findElements(By.xpath(sberbankASTMain.getSelectorSubMenuHeader()));
        //menuElements.forEach(x->System.out.println(x.getText()));
        //System.out.println("Пункты субменю:");
        //List<WebElement> submenuElements =  driver.findElements(By.xpath("//ul[@class='navSubmenu']/li//ul//a[text()]"));
        //submenuElements.forEach(x->System.out.println(x.getAttribute("innerText")));
        sberbankASTMain.getCollectSubMenuLinks();
            Assertions.assertTrue(1==1);
    }

}
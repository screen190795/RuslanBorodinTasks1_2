package ru.bellIntegrator.page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.*;
import java.util.Map.Entry;

public class SberbankASTMain {
    private WebDriver driver;

    //Корневая таблица главного меню
    private String selectorMainMenu ="//nav/ul[@class='navSubmenu']";
    //пункты меню
    private String selectorMainMenuTop = "//ul[@class='navSubmenu']/li[not(./a[@class='SearchIco'])]";
    // ссылки - пункты подменю
    private String selectorMainMenuSubLink = "//ul[@class='navSubmenu']//a[not(@class='SearchIco')]";



    private WebElement mainMenu;
    private List<WebElement> mainMenuTopElements;
    private  List<WebElement> mainMenuSubLinks;
    private List<Map<WebElement,WebElement>> allMainMenuElements;

    private String sberbankAstURL="https://www.sberbank-ast.ru/";

    public SberbankASTMain(WebDriver driver) {
        this.driver = driver;
        driver.get(sberbankAstURL);
        this.mainMenu = driver.findElement(By.xpath(selectorMainMenu));
        this.mainMenuTopElements = mainMenu.findElements(By.xpath(selectorMainMenuTop));
        this.mainMenuSubLinks = driver.findElements(By.xpath(selectorMainMenuSubLink));
    }


    /*
     * метод собирает все пункты и подпункты главного меню
     */
    public List<Map<WebElement, WebElement>> collectSubMenuLinks() {
        allMainMenuElements = new ArrayList<>();
        List<WebElement> mainMenuTopElements =  driver.findElements(By.xpath(this.selectorMainMenuTop));
            for(int i = 0; i < mainMenuTopElements.size(); i++){
                List<WebElement> mainMenuSubElements = driver.findElement(By.xpath(selectorMainMenu))
                        .findElements(By.xpath(selectorMainMenuTop + "["+(i+1)+"]//a"));

                for (WebElement subElement : mainMenuSubElements) {
                    Map<WebElement, WebElement> collectSubLinks = new HashMap<>();
                    collectSubLinks.put(mainMenuTopElements.get(i), subElement);
                    allMainMenuElements.add(i, collectSubLinks);
                }
            }

            return  allMainMenuElements;
    }

    /*
     * Переход по ссылке подменю. На вход принимает названия элементов меню и подменю
     */
    public void goToSubLink(String topElement, String subElement )  {
        for (Map<WebElement, WebElement> map : collectSubMenuLinks()){
            for(Entry entry: map.entrySet()) {
                WebElement top = (WebElement) entry.getKey();
                WebElement sub = (WebElement) entry.getValue();
                if(top.getText().equals(topElement) && sub.getAttribute("innerText").equals(subElement)){
                    driver.get(sub.getAttribute("href"));
                    break;
                }
            }
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public List<WebElement> getMainMenuTopElements() {
        return mainMenuTopElements;
    }

    public List<WebElement> getMainMenuSubLinks() {
        return mainMenuSubLinks;
    }
}

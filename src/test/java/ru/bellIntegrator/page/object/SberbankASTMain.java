package ru.bellIntegrator.page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.*;

public class SberbankASTMain {

    private String selectorMainMenu ="//nav[ul[@class='navSubmenu']]";
    private String selectorSubMenuHeader = "//ul[@class='navSubmenu']/li";
    private String selectorSubMenuTitle = "//ul[@class='navSubmenu']/li/h3";
    private String selectorSubMenuElement = "//ul[@class='navSubmenu']/li//a";
    private WebDriver driver;
    private WebElement subMenuLinks;
    private WebElement mainMenu;
    private List<Map<String,String>> collectSubMenuLinks = new ArrayList<>();
    private String mainURL="https://www.sberbank-ast.ru/";

    public SberbankASTMain(WebDriver driver) {
        this.driver = driver;
    }

    public List<Map<String, Object>> getCollectSubMenuLinks() throws InterruptedException {
        List<Map<String,Object>> allMenu= new ArrayList<>();
        List<WebElement> menuElements =  driver.findElements(By.xpath(this.getSelectorSubMenuHeader()));

            for(int i = 0; i < menuElements.size(); i++){
                List<WebElement> subelements = driver.findElements(By.xpath("//ul[@class='navSubmenu']/li["+(i+1)+"]//a"));
                for (WebElement subelement : subelements) {
                    Map<String, Object> collectSubLinks = new HashMap<>();
                    collectSubLinks.put(menuElements.get(i).getText(), subelement.getAttribute("innerText"));
                    allMenu.add(i, collectSubLinks);
                }
            }
        allMenu.forEach(map -> map.forEach((key, value) -> System.out.println(key + ":" + value)));
        System.out.println("Количество элементов: " + allMenu.size());
            System.out.println("Размер коллекции: " + allMenu.size());
            return  allMenu;
    }



    public void goToPage(){
        driver.get(mainURL);
    }
    public String getSelectorMainMenu() {
        return selectorMainMenu;
    }
    public String getSelectorSubMenuHeader() {
        return selectorSubMenuHeader;
    }
    public String getSelectorSubMenuElement() {
        return selectorSubMenuElement;
    }
}

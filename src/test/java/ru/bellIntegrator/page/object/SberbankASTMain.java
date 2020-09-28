package ru.bellIntegrator.page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SberbankASTMain {

    private String selectorMainMenu ="//nav[ul[@class='navSubmenu']]";
    private String selectorSubMenuHeader = "//ul[@class='navSubmenu']/li";
    private String selectorSubMenuTitle = "//ul[@class='navSubmenu']/li/h3";
    private String selectorSubMenuElement = "//ul[@class='navSubmenu']/li//ul//a[text()]";

    private WebDriver driver;

    private WebElement subMenuLinks;
    private WebElement mainMenu;
    private List<Map<String,String>> collectSubMenuLinks = new ArrayList<>();

    private String mainURL="https://www.sberbank-ast.ru/";

    public SberbankASTMain(WebDriver driver) {
        this.driver = driver;
        if(!driver.getTitle().contains("Сбербанк-АСТ"))
            driver.get(mainURL);
        mainMenu= driver.findElement(By.xpath(selectorMainMenu));
    }

    /*public int countLi(){
        List<WebElement> menuElements =  driver.findElements(By.xpath(this.getSelectorSubMenuHeader()));
        for(int i = 0; i < menuElements.size(); ++i){
            List<WebElement> submenuElements= menuElements.get(i).findElements(By.xpath(this.getSelectorSubMenuElement()));
            System.out.println(elements);
        }

        return elements;
    }*/

    public void getCollectSubMenuLinks(){
        List<WebElement> menuElements =  driver.findElements(By.xpath(this.getSelectorSubMenuHeader()));
        List<WebElement> submenuElements= driver.findElements(By.xpath(this.getSelectorSubMenuElement()));
            for(int i = 1; i < menuElements.size(); ++i){
                Map<String,String> collectSubLinks = new HashMap<>();
                List<WebElement> subelements = driver.findElements(By.xpath("//ul[@class='navSubmenu']/li["+(i+1)+"]//ul//a[text()]"));
                int count= subelements.size();
                System.out.println(count);
                subelements.forEach(x->System.out.println(x.getAttribute("innerText")));

                //for(int j = 0; j < count; ++j){

                        //collectSubLinks.put(
                        //menuElements.get(i).getText(),
                                //menuElements.get(i).findElement(By.xpath("//li["+(j+1)+"]")).getAttribute("innerText")
                        //);
                   // System.out.println(submenuElements.get(j).getAttribute("innerText"));
                //}
                //collectSubMenuLinks.add(collectSubLinks);
                //collectSubMenuLinks.forEach(x->System.out.println());
            }

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

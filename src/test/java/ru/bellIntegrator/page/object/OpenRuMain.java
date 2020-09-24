package ru.bellIntegrator.page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.bellIntegrator.TestBase;

import java.util.List;

public class OpenRuMain {
    protected WebDriver driver;

    //элементы-значения продажи и покупки доллара
    List<WebElement> USDExchangeRates;

    public OpenRuMain(WebDriver driver) {
        this.driver = driver;
        this.USDExchangeRates = driver.findElements(By.xpath("//td[contains(.//span, 'USD')]/following-sibling::td//span"));
    }

    public List<WebElement> getUSDExchangeRates() {
        return USDExchangeRates;
    }


}

package com.likhtarovich.page;

import com.likhtarovich.util.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;


public class TablePage {

    private final WebDriver driver;

    private final static By POP_UP_WINDOW_BUTTON_LOCATOR = By.xpath("//button[contains(text(),'Go')]");
    private final static By STORAGE_ENGINE_LOCATOR = By.name("tbl_storage_engine");
    private final static By COLLATION_LOCATOR = By.name("tbl_collation");
    private final static By SUBMIT_CREATION_LOCATOR = By.name("do_save_data");

    public TablePage(WebDriver driver) {
        this.driver = driver;
    }

    public TablePage setName(String name, int row) {
        String columnNameLocator = "//*[@id='field_%d_1']";

        driver.findElement(By.xpath(String.format(columnNameLocator, row - 1))).clear();
        driver.findElement(By.xpath(String.format(columnNameLocator, row - 1))).sendKeys(name);

        return this;
    }

    public TablePage setType(String type, int row) {
        String columnTypeLocator = "//*[@id='field_%d_2']";

        WebElement element = driver.findElement(By.xpath(String.format(columnTypeLocator, row - 1)));
        Select select = new Select(element);

        select.selectByVisibleText(type.toUpperCase());
        return this;

    }

    public TablePage setLengthOfValues(int length, int row) {
        String columnNameLocator = "//*[@id='field_%d_3']";

        driver.findElement(By.xpath(String.format(columnNameLocator, row - 1))).clear();
        driver.findElement(By.xpath(String.format(columnNameLocator, row - 1))).sendKeys(String.valueOf(length));

        return this;
    }

    public TablePage setIndexKey(String index, int row) {
        String columnIndexKeyLocator = "//*[@id='field_%d_7']";
        WebElement element = driver.findElement(By.xpath(String.format(columnIndexKeyLocator, row - 1)));

        Select select = new Select(element);
        select.selectByVisibleText(index.toUpperCase());

        // close pop up window

        try {
            Waiter.getWaiter(driver, 1).until(ExpectedConditions.visibilityOfElementLocated(POP_UP_WINDOW_BUTTON_LOCATOR));
        } catch (Exception e) {
            return this;
        }

        driver.findElement(POP_UP_WINDOW_BUTTON_LOCATOR).click();
        return this;

    }

    public TablePage setAutoIncrement(boolean enabledAI, int row) {
        String autoIncrementLocator = "//*[@id='field_%d_8']";
        WebElement checkboxAI = driver.findElement(By.xpath(String.format(autoIncrementLocator, row - 1)));
        if (enabledAI) {

            if (!checkboxAI.isSelected()) {
                checkboxAI.click();
                if (!checkboxAI.isSelected()) {
                    checkboxAI.sendKeys(Keys.ARROW_RIGHT);
                    checkboxAI.click();
                    checkboxAI.sendKeys(Keys.ARROW_LEFT);
                }
            }
        } else {

            if (checkboxAI.isSelected()) {
                checkboxAI.sendKeys(Keys.ARROW_RIGHT);
                checkboxAI.click();
                checkboxAI.sendKeys(Keys.ARROW_LEFT);
            }
        }
        // close pop up window
        try {
            Waiter.getWaiter(driver, 1).until(ExpectedConditions.visibilityOfElementLocated(POP_UP_WINDOW_BUTTON_LOCATOR));
        } catch (Exception e) {
            return this;
        }

        driver.findElement(POP_UP_WINDOW_BUTTON_LOCATOR).click();
        return this;
    }

    public TablePage setTableCollation(String collocation) {
        WebElement element = driver.findElement(COLLATION_LOCATOR);
        Select select = new Select(element);
        select.selectByVisibleText(collocation);
        return this;
    }

    public TablePage setStorageEngine(String storageEngine) {
        WebElement element = driver.findElement(STORAGE_ENGINE_LOCATOR);
        Select select = new Select(element);
        select.selectByVisibleText(storageEngine);
        return this;
    }

    public TablePage setNull(boolean enabledNull, int row) {
        String isNullLocator = "//*[@id='field_%d_6']";
        WebElement checkboxNull = driver.findElement(By.xpath(String.format(isNullLocator, row - 1)));
        if (enabledNull) {

            if (!checkboxNull.isSelected()) {
                checkboxNull.click();
            }
        } else {

            if (checkboxNull.isSelected()) {
                checkboxNull.click();
            }
        }

        return this;
    }

    public TablePage createColumn(int num, String name, String type, int length, boolean enabledNull, String index, boolean enabledAI) {
        setName(name, num);
        setType(type, num);
        setLengthOfValues(length, num);
        setNull(enabledNull, num);
        setIndexKey(index, num);
        setAutoIncrement(enabledAI, num);
        return this;
    }


    public TablePage createColumn(int num, String name, String type, int length, boolean enabledNull, boolean enabledAI) {
        setName(name, num);
        setType(type, num);
        setLengthOfValues(length, num);
        setNull(enabledNull, num);
        setAutoIncrement(enabledAI, num);
        return this;
    }

    public TablePage createColumn(int num, String name, String type, int length, boolean enabledNull) {
        setName(name, num);
        setType(type, num);
        setLengthOfValues(length, num);
        setNull(enabledNull, num);
        return this;
    }

    public TableStructurePage submitCreatingTable() {
        driver.findElement(SUBMIT_CREATION_LOCATOR).submit();
        return new TableStructurePage(driver);
    }

}



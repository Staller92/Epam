package com.likhtarovich.page;

import com.likhtarovich.util.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class DatabaseStructurePage {

    private final WebDriver driver;

    private final static By GO_HOME_LOCATOR = By.xpath("//*[@class='icon ic_b_home']");
    private final static By TABLE_NAME_LOCATOR = By.xpath("//*[@class='formelement']//input[@name='table']");
    private final static By COLUMNS_LOCATOR = By.xpath("//*[@class='formelement']//input[@name='num_fields']");
    private final static By ALL_TABLES_LOCATOR = By.xpath("//table[@id='structureTable']//tbody[1]/tr");
    private final static By INSERT_TABLE_LOCATOR = By.xpath(".//img[@title='Insert']");
    private final static By CHECKBOX_TABLE_LOCATOR = By.name("selected_tbl[]");
    private final static By CREATE_TABLE_BUTTON_LOCATOR = By.xpath("//input[@value='Go']");
    private final static By TABLE_CREATE_BUTTON = By.xpath(".//*[@id='create_table_form_minimal']/fieldset[1]/legend");
    private final static By LINK_BROWSE_TABLE_LOCATOR = By.xpath("./th/a");
    private final static By LINK_STRUCTURE_TABLE_LOCATOR = By.xpath(".//img[@title='Structure']");

    public DatabaseStructurePage(WebDriver driver) {
        this.driver = driver;
    }


    public DatabaseStructurePage setTableName(String tableName) {
        driver.findElement(TABLE_NAME_LOCATOR).clear();
        driver.findElement(TABLE_NAME_LOCATOR).sendKeys(tableName);
        return this;
    }

    public DatabaseStructurePage setColumns(int columns) {
        driver.findElement(COLUMNS_LOCATOR).clear();
        driver.findElement(COLUMNS_LOCATOR).sendKeys(String.valueOf(columns));
        return this;
    }


    public TablePage submitTable() {
        driver.findElement(CREATE_TABLE_BUTTON_LOCATOR).click();
        return new TablePage(driver);
    }

    public TablePage createNewTable(String tableName, int columns) {
        setTableName(tableName);
        setColumns(columns);
        return submitTable();
    }

    public InsertDataPage insertDataToTable(String tableName) {
        List<WebElement> tables = driver.findElements(ALL_TABLES_LOCATOR);
        for (WebElement table : tables) {
            if (table.findElement(CHECKBOX_TABLE_LOCATOR).getAttribute("value").equals(tableName)) {
                Waiter.getWaiter(driver, 15).until((ExpectedConditions.elementToBeClickable(INSERT_TABLE_LOCATOR)));
                table.findElement(INSERT_TABLE_LOCATOR).click();
                break;
            }
        }
        return new InsertDataPage(driver);
    }

    public MainPage goHome() {
        Waiter.getWaiter(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(TABLE_CREATE_BUTTON));
        driver.findElement(GO_HOME_LOCATOR).click();
        return new MainPage(driver);
    }

    public boolean isTableExist(String tableName) {
        List<WebElement> tables = driver.findElements(ALL_TABLES_LOCATOR);
        for (WebElement table : tables) {
            if (table.findElement(CHECKBOX_TABLE_LOCATOR).getAttribute("value").equals(tableName)) {

                return true;
            }
        }
        return false;
    }

    public TableBrowsePage selectTable(String tableName) {

        List<WebElement> tables = driver.findElements(ALL_TABLES_LOCATOR);
        for (WebElement table : tables) {
            if (table.findElement(CHECKBOX_TABLE_LOCATOR).getAttribute("value").equals(tableName)) {
                table.findElement(LINK_BROWSE_TABLE_LOCATOR).click();
                break;
            }
        }

        return new TableBrowsePage(driver);
    }

    public TableStructurePage goToStructureTable(String tableName) {

        List<WebElement> tables = driver.findElements(ALL_TABLES_LOCATOR);
        for (WebElement table : tables) {
            if (table.findElement(CHECKBOX_TABLE_LOCATOR).getAttribute("value").equals(tableName)) {
                Waiter.getWaiter(driver, 15).until((ExpectedConditions.elementToBeClickable(LINK_STRUCTURE_TABLE_LOCATOR)));
                table.findElement(LINK_STRUCTURE_TABLE_LOCATOR).click();
                break;
            }
        }
        return new TableStructurePage(driver);
    }

}

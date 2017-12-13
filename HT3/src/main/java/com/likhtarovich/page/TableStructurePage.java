package com.likhtarovich.page;

import com.likhtarovich.util.Cell;
import com.likhtarovich.util.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class TableStructurePage {
    private final WebDriver driver;

    private List<List<Cell>> table;

    private final static By GO_HOME_LOCATOR = By.xpath("//*[@class='icon ic_b_home']");
    private final static By TABLE_STRUCTURE_BUTTON_LOCATOR = By.xpath("//*[@id='table_strucuture_id']");
    private final static By ROWS_LOCATOR = By.xpath("//*[@id='tablestructure']//tr");
    private final static By CELLS_LOCATOR = By.xpath("./child::*");
    private final static By HEADERS_LOCATOR = By.xpath(".//*[@id='tablestructure']/thead/tr/th");

    public TableStructurePage(WebDriver driver) {
        this.driver = driver;
    }

    public MainPage goHome() {
        Waiter.getWaiter(driver, 15).until((ExpectedConditions.visibilityOfElementLocated(TABLE_STRUCTURE_BUTTON_LOCATOR)));
        driver.findElement(GO_HOME_LOCATOR).click();
        return new MainPage(driver);
    }

    public void initTable() {
        table = new ArrayList<>();

        List<WebElement> rows = driver.findElements(ROWS_LOCATOR);
        List<String> headers = getHeaders();
        for (int i = 1; i <rows.size(); i++) {
            List<WebElement> cells = rows.get(i).findElements(CELLS_LOCATOR);
            table.add(new ArrayList<Cell>());
            for (int j = 0; j < headers.size(); j++) {
                table.get(i-1).add(new Cell(headers.get(j), cells.get(j)));
            }
        }
    }

    private List<String> getHeaders() {
        List<String> headersValues = new ArrayList<>();
        List<WebElement> headers = driver.findElements(HEADERS_LOCATOR);
        for (WebElement header : headers) {
            headersValues.add(header.getText());
        }
        return headersValues;
    }

    private List<Cell> getRow(int id) {
        return table.get(id-1);

    }

    public String getValue(int id, String header) {
        for (Cell cell : getRow(id)) {
            if (cell.getName().equals(header)) {
                return cell.getElement().getText();
            }
        }
        return null;
    }

}




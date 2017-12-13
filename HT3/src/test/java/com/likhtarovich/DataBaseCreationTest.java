package com.likhtarovich;

import com.likhtarovich.page.*;
import com.likhtarovich.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
/**
Windoows 7 x64; Firefox 57; geckodriver 0.19.1 Selenium 3.8.1
*/
public class DataBaseCreationTest extends TestBase {
    LoginPage loginPage;
    MainPage mainPage;
    DatabasePage databasePage;

    @BeforeClass
    public void beforeClass() {
        setup();
        loginPage = new LoginPage(driver);
        mainPage = loginPage.loginAs("English","root", "root");
        databasePage = mainPage.openDatabasePage();
        if (databasePage.isDatabaseExist("auth")) {
            databasePage.dropDatabase("auth");
        }
        exit();
    }

    @AfterClass
    public void afterClass() {
        beforeClass();
    }

    @BeforeMethod
    public void beforeTest() {
        setup();
        loginPage = new LoginPage(driver);
        mainPage = loginPage.loginAs("English","root", "root");
    }

    @AfterMethod
    public void afterTest() {
        exit();

    }


    @Test
    public void testAddDatabase() {

        databasePage = mainPage.openDatabasePage();
        //check database is not exist
        Assert.assertFalse(databasePage.isDatabaseExist("auth"));
        //creation database
        DatabaseStructurePage databaseStructurePage = databasePage.createNewDatabase("auth", "utf8_general_ci");
        mainPage = databaseStructurePage.goHome();
        databasePage = mainPage.openDatabasePage();
        //database appeared
        Assert.assertTrue(databasePage.isDatabaseExist("auth"));
    }

    @Test(dependsOnMethods = {"testAddDatabase"})
    public void testAddTable() {

        databasePage = mainPage.openDatabasePage();
        //select database
        DatabaseStructurePage databaseStructurePage = databasePage.selectDatabase("auth");
        //check table does not exist
        Assert.assertFalse(databaseStructurePage.isTableExist("users"));
        //creation table
        TablePage tablePage = databaseStructurePage.createNewTable("users", 6);
        tablePage.createColumn(1, "u_id", "int", 11, false, true);
        tablePage.createColumn(2, "u_login", "varchar", 255, false);
        tablePage.createColumn(3, "u_password", "char", 40, false);
        tablePage.createColumn(4, "u_email", "varchar", 255, false);
        tablePage.createColumn(5, "u_name", "varchar", 255, false);
        tablePage.createColumn(6, "u_remember", "char", 40, false);
        tablePage.setStorageEngine("InnoDB");
        tablePage.setTableCollation("utf8_general_ci");
        TableStructurePage tableStructurePage = tablePage.submitCreatingTable();

        mainPage = tableStructurePage.goHome();
        databasePage = mainPage.openDatabasePage();
        databaseStructurePage = databasePage.selectDatabase("auth");

        //table appeared
        Assert.assertTrue(databaseStructurePage.isTableExist("users"));
    }

    @Test(dependsOnMethods = {"testAddTable"})
    public void testTable() {

        databasePage = mainPage.openDatabasePage();
        //select database
        DatabaseStructurePage databaseStructurePage = databasePage.selectDatabase("auth");
        //select table
        TableStructurePage tableStructurePage = databaseStructurePage.goToStructureTable("users");
        //get info about table
        tableStructurePage.initTable();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(tableStructurePage.getValue(1, "Name"), "u_id","unable to fill 'Name' 1 field");
        softAssert.assertEquals(tableStructurePage.getValue(2, "Name"), "u_login","unable to fill 'Name' 2 field");
        softAssert.assertEquals(tableStructurePage.getValue(3, "Name"), "u_password","unable to fill 'Name' 3 field");
        softAssert.assertEquals(tableStructurePage.getValue(4, "Name"), "u_email","unable to fill 'Name' 4 field");
        softAssert.assertEquals(tableStructurePage.getValue(5, "Name"), "u_name","unable to fill 'Name' 5 field");
        softAssert.assertEquals(tableStructurePage.getValue(6, "Name"), "u_remember","unable to fill 'Name' 6 field");
        softAssert.assertEquals(tableStructurePage.getValue(1, "Type"), "int(11)","unable to fill 'Type' 1 field");
        softAssert.assertEquals(tableStructurePage.getValue(2, "Type"), "varchar(255)","unable to fill 'Type' 2 field");
        softAssert.assertEquals(tableStructurePage.getValue(3, "Type"), "char(40)","unable to fill 'Type' 3 field");
        softAssert.assertEquals(tableStructurePage.getValue(4, "Type"), "varchar(255)","unable to fill 'Type' 4 field");
        softAssert.assertEquals(tableStructurePage.getValue(5, "Type"), "varchar(255)","unable to fill 'Type' 5 field");
        softAssert.assertEquals(tableStructurePage.getValue(6, "Type"), "char(40)","unable to fill 'Type' 6 field");
        softAssert.assertEquals(tableStructurePage.getValue(4, "Collation"), "utf8_general_ci","unable to fill 'Collation' 4 field");
        softAssert.assertEquals(tableStructurePage.getValue(1, "Null"), "No","unable to fill 'Null' 1 field");
        softAssert.assertEquals(tableStructurePage.getValue(3, "Null"), "No","unable to fill 'Null' 3 field");
        softAssert.assertEquals(tableStructurePage.getValue(1, "Extra"), "AUTO_INCREMENT","unable to fill 'Extra' 1 field");

        softAssert.assertAll();

    }

    @Test(dependsOnMethods = {"testTable"})
    public void testFillTable() {

        databasePage = mainPage.openDatabasePage();
        //select database
        DatabaseStructurePage databaseStructurePage = databasePage.selectDatabase("auth");
        //select table
        InsertDataPage insertDataPage = databaseStructurePage.insertDataToTable("users");
        //fill table
        insertDataPage.setValue("u_id", 1);
        insertDataPage.setValue("u_login", "user1");
        insertDataPage.setValue("u_password", "e38ad214943daad1d64c102faec29de4afe9da3d");
        insertDataPage.setValue("u_email", "user1@mail.com");
        insertDataPage.setValue("u_name", "Pupkin");
        insertDataPage.setValue("u_remember", "");
        insertDataPage.submitCreatingTable();
        databaseStructurePage.insertDataToTable("users");
        insertDataPage.setValue("u_id", 2);
        insertDataPage.setValue("u_login", "user2");
        insertDataPage.setValue("u_password", "2aa60a8ff7fcd473d321e0146afd9e26df395147");
        insertDataPage.setValue("u_email", "user2@mail.com");
        insertDataPage.setValue("u_name", "Smith");
        insertDataPage.setValue("u_remember", "");
        TableBrowsePage tableBrowsePage = insertDataPage.submitCreatingTable().goHome().openDatabasePage().selectDatabase("auth").selectTable("users");
        tableBrowsePage.initTable();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(tableBrowsePage.getValue(1, "u_id"), "1","unable to fill 'u_id' 1 field");
        softAssert.assertEquals(tableBrowsePage.getValue(2, "u_id"), "2","unable to fill 'u_id' 2 field");
        softAssert.assertEquals(tableBrowsePage.getValue(1, "u_login"), "user1","unable to fill 'u_login' 1 field");
        softAssert.assertEquals(tableBrowsePage.getValue(2, "u_login"), "user2","unable to fill 'u_login' 2 field");
        softAssert.assertEquals(tableBrowsePage.getValue(1, "u_email"), "user1@mail.com","unable to fill 'u_email' 1 field");
        softAssert.assertEquals(tableBrowsePage.getValue(2, "u_email"), "user2@mail.com","unable to fill 'u_email' 2 field");
        softAssert.assertEquals(tableBrowsePage.getValue(1, "u_name"), "Pupkin","unable to fill 'u_name' 1 field");
        softAssert.assertEquals(tableBrowsePage.getValue(2, "u_name"), "Smith","unable to fill 'u_name' 2 field");
        softAssert.assertAll();

    }


}


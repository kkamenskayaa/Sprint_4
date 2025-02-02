package Praktikum;

import PageObjects.MainPage;
import PageObjects.OrderPage;
import org.junit.Rule;
import org.openqa.selenium.WebDriver;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.assertTrue;


@RunWith(Parameterized.class)
public class OrderTest {
    private MainPage mainPage;
    private OrderPage orderPage;

    private final String MainPageURL = "https://qa-scooter.praktikum-services.ru/";

    private final String name;
    private final String surname;
    private final String phone;
    private final String address;
    private final String metro;
    private final String comment;
    private final String period;
    private final String color;
    private final String date;

    @Rule
    public DriverRule driverRule = new DriverRule();

    public OrderTest (String name, String surname, String address, String metro, String phone, String date, String period, String color, String comment, String expected) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;

        this.date = date;
        this.period = period;
        this.color = color;
        this.comment = comment;
    }

    @Before
    public void init() {
        WebDriver driver = driverRule.getDriver();
        driver.get(MainPageURL);
        mainPage = new MainPage(driver);
        orderPage = new OrderPage(driver);
    }



    @Parameterized.Parameters
    public static Object[][] settingFAQ() {
        return new Object[][]{
                {"Иван", "Иванович", "ул. Академика Королёва 12", "Лубянка", "+79204005450", "15.05.2025", "сутки", "чёрный жемчуг", "будьте осторожны", "Заказ оформлен"},
                {"Питер", "Паркер", "ул. Новослободская д.24", "Арбатская", "+79993334444", "12.02.2025", "пятеро суток", "серая безысходность", "", "Заказ оформлен"},
        };
    }

    @Test
    public void TestOrderHeader(){
        mainPage.clickCookiesButton();
        mainPage.clickOrderButtonHeader();
        orderPage.waitOrderHeaderLoad();
        orderPage.fillName(name);
        orderPage.fillSurname(surname);
        orderPage.fillAddress(address);
        orderPage.selectMetro(metro);
        orderPage.fillPhone(phone);
        orderPage.clickNextButton();
        orderPage.fillDateField(date);
        orderPage.fillPeriodField(period);
        orderPage.selectColor(color);
        orderPage.fillCommentField(comment);
        orderPage.clickOrderButton();
        orderPage.clickConfirmOrderButton();
        assertTrue(orderPage.checkOrderOK());
    }
    @Test
    public void TestOrderBody(){
        mainPage.clickCookiesButton();
        mainPage.clickOrderButtonBody();
        orderPage.waitOrderHeaderLoad();
        orderPage.fillName(name);
        orderPage.fillSurname(surname);
        orderPage.fillAddress(address);
        orderPage.selectMetro(metro);
        orderPage.fillPhone(phone);
        orderPage.clickNextButton();
        orderPage.fillDateField(date);
        orderPage.fillPeriodField(period);
        orderPage.selectColor(color);
        orderPage.fillCommentField(comment);
        orderPage.clickOrderButton();
        orderPage.clickConfirmOrderButton();
        assertTrue(orderPage.checkOrderOK());
    }


}

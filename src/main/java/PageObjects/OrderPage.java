package PageObjects;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

import java.time.Duration;

public class OrderPage {
    // драйвер
    private WebDriver driver;

    private final By orderHeader = By.className("Order_Header__BZXOb");
    //локатор поля Имя
    private final By nameField = By.xpath("//input[@placeholder='* Имя']");
    //локатор поля Фамилия
    private final By surnameField = By.xpath("//input[@placeholder='* Фамилия']");
    //локатор поля Адрес
    private final By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    //локатор поля Станция метро
    private final By metroField = By.xpath("//input[@placeholder='* Станция метро']");
    //строка с xpath, куда будет подставляться название станции метро
    private final String metroDropdown = "//div[contains(@class, 'Order_Text__2broi') and text()='%s']";
    //локатор поля Телефон
    private final By phoneField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    //локатор кнопки Далее
    private final By nextButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Далее']");
    //локатор поля Дата
    private final By dateField = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    //локатор элемента выбранной даты в поп-ап календаре
    private final By datePopupSelected = By.className("react-datepicker__day--selected");
    //локатор поля Срок Аренды
    private final By periodField = By.xpath("//*[contains(@class, 'Dropdown-root')]");
    //строка с xpath, куда будет подставляться выбранный срок аренды
    private final String periodPopup = "//div[contains(@class, 'Dropdown-option') and text()='%s']";
    //строка с xpath, куда будет подставляться название цвета
    private final String colorLabel = "//label[text()='%s']";
    //локатор поля Комментарий курьеру
    private final By commentField = By.xpath("//input[@placeholder='Комментарий для курьера']");
    //локатор кнопки Заказать
    private final By orderButton = By.xpath("//div[contains(@class, 'Order_Buttons__1xGrp')]//button[text()='Заказать']");
    //локатор кнопки Да (В поп-апе "Хотите оформить заказ?")
    private final By confirmOrderButton = By.xpath(".//*[text()='Да']");
    //локатор элемента с текстом "Заказ оформлен" для проверки успешности заказа
    private final By orderOK = By.xpath("//div[contains(@class, 'Order_ModalHeader__3FDaJ') and contains(text(), 'Заказ оформлен')]");

    //Конструктор
    public OrderPage(WebDriver driver){
        this.driver = driver;
    }

    public void waitOrderHeaderLoad(){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(orderHeader));

    }
    //метод ввода имени
    public void fillName(String name){
        driver.findElement(nameField).sendKeys(name);
    }
    //ввод фамилии
    public void fillSurname(String surname){
        driver.findElement(surnameField).sendKeys(surname);
    }
    //ввод адреса
    public void fillAddress(String address){
        driver.findElement(addressField).sendKeys(address);
    }
    //выбор метро
    public void selectMetro(String metro){
        WebElement metroFieldElement = driver.findElement(metroField);
        metroFieldElement.click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(metroDropdown, metro)))).click();
    }
    //ввод телефона
    public void fillPhone(String phone){
        driver.findElement(phoneField).sendKeys(phone);
    }
    //нажатие на кнопку Далее
    public void clickNextButton(){
        driver.findElement(nextButton).click();
    }
    //ввод даты
    public void fillDateField(String date){
        driver.findElement(dateField).sendKeys(date);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(datePopupSelected)).click();
    }
    //выбор срока аренды
    public void fillPeriodField(String period){
        driver.findElement(periodField).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(periodPopup, period)))).click();
    }
    //выбор цвета
    public void selectColor(String color){
        WebElement colorCheckbox = driver.findElement(By.xpath(String.format(colorLabel, color)));
        colorCheckbox.click();
    }
    //ввод комментария курьеру
    public void fillCommentField(String comment){
        driver.findElement(commentField).sendKeys(comment);
    }
    //нажатие на кнопку Заказать
    public void clickOrderButton(){
        driver.findElement(orderButton).click();
    }
    //нажатие на кнопку "Да" в поп-апе
    public void clickConfirmOrderButton(){
        driver.findElement(confirmOrderButton).click();
    }
    //проверка на наличие поп-апа, подтверждающего успешный заказ
    public boolean checkOrderOK(){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(orderOK));
        WebElement orderOkPopup = driver.findElement(orderOK);
        return orderOkPopup.isDisplayed();
    }
}

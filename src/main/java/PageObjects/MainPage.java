package PageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage {
    //Драйвер
    private WebDriver driver;

    //кнопка "Принять куки"
    private final By cookieButton = By.id("rcc-confirm-button");

    //секция FAQ
    private final By faqSection = By.className("accordion");
    //базовый ID локатор вопросов (под добавление индекса)
    private final String faqQuestionBaseId = "accordion__heading-";
    //базовый ID локатор ответов (под добавление индекса)
    private final String faqAnswerBaseId = "accordion__panel-";

    //локатор кнопки заказать в хедере
    private final By orderButtonHeader = By.className("Button_Button__ra12g");
    //локатор кнопки заказать в теле
    private By orderButtonBody = By.xpath(".//div[@class = 'Home_ThirdPart__LSTEE']/div[@class = 'Home_RoadMap__2tal_']/div[@class = 'Home_FinishButton__1_cWm']/button");

    // конструктор
    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    // нажатие на кнопку "Принять куки"
    public void clickCookiesButton(){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(cookieButton)).click();
        waitForCookiesFloaterToDisappear();
    }
    // подождать, пока флоатер куков пропадёт
    public void waitForCookiesFloaterToDisappear() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.invisibilityOfElementLocated(cookieButton));
    }
    // нажатие на кнопку "Заказать" в хедере
    public void clickOrderButtonHeader(){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(orderButtonHeader));
        driver.findElement(orderButtonHeader).click();
    }


    // нажатие на кнопку "Заказать" на странице
    public void clickOrderButtonBody() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(orderButtonBody));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", button);
        button.click();
    }


    //скролл до FAQ секции
    public void scrollToQuestion(int index){
        WebElement faqSectionElement = driver.findElement(By.id(faqQuestionBaseId+index));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", faqSectionElement);
    }

    //проверка, загрузилась ли секция с FAQ
    public boolean checkFaqSectionIsLoaded(){
        WebElement faqSectionElement = driver.findElement(faqSection);
        return faqSectionElement.isDisplayed();
    }

    // нажатие вопрос по индексу
    public void clickQuestion(int index){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id(faqQuestionBaseId+index)));
        driver.findElement(By.id(faqQuestionBaseId+index)).click();
    }
    // получение ответа по индексу
    public String getAnswerText(int index){
        return driver.findElement(By.id(faqAnswerBaseId+index)).getText();
    }

    // Проверка, загрузился ли ответ
    public boolean checkAnswerIsLoaded(int index){
        return driver.findElement(By.id(faqAnswerBaseId + index)).isDisplayed();
    }
}


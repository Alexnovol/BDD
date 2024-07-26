package stepdefinitions;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.AllArgsConstructor;
import org.openqa.selenium.Keys;
import utilities.CommonActions;
import utilities.ScenarioContext;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static utilities.CommonActions.hoverToCategoryMenu;
import static utilities.Locators.*;


@AllArgsConstructor
public class MainStep {

    private ScenarioContext scenarioContext;

    @Given("Пользователь на главной странице сайта {string}")
    public void openMainPage(String url) {
        open(url);

        Selenide.$x(XPATH_MAIN_PAGE_CONTENT).shouldBe(enabled);
    }

    @When("Пользователь вводит {string} в поисковую строку и нажимает Enter")
    public void findProduct(String product) {

        SelenideElement searchField = $(ID_SEARCH_INPUT);

        searchField.click();
        searchField.setValue(product);
        searchField.sendKeys(Keys.ENTER);

        scenarioContext.setContextEl("searchField", searchField);

    }

    @When("Пользователь кликает на кнопку Смена города")
    public void clickButtonChangeCity() {
        SelenideElement buttonChangeCity = $x(XPATH_BUTTON_CHANGE_CITY);
        buttonChangeCity.click();

        scenarioContext.setContextEl("buttonChangeCity", buttonChangeCity);
    }

    @Then("Происходит переход на главную страницу")
    public void checkGoToMainPage() {
        scenarioContext.getContextEl("infoDeliveryPoint").shouldBe(disappear);
    }

    @And("Отображается адрес пункта выдачи, который выбрал пользователь")
    public void checkAddressDeliveryPointOnMainPage() {
        SelenideElement buttonChangeCity = scenarioContext.getContextEl("buttonChangeCity");
        String expAddress = scenarioContext.getContextText("firstAddress");

        buttonChangeCity.shouldHave(text(expAddress));
    }

    @When("Пользователь нажимает на кнопку Фильтры")
    public void clickButtonFilters() {
        $(CSS_BUTTON_FILTERS).click();
    }

    @When("Пользователь выбирает Бытовая техника - Техника для дома - Пылесосы и пароочистители - Роботы-Пылесосы")
    public void goToCategoryRobotHoovers() {
        hoverToCategoryMenu();
        $(CSS_HOUSE_APPLIANCES).scrollTo().shouldBe(enabled).click();
        $x(XPATH_APPLIANCES_FOR_HOUSE).click();
        $x(XPATH_CATEGORY_HOOVERS_AND_STEAM_CLEANERS).click();
        $x(XPATH_CATEGORY_ROBOT_HOOVERS).click();
    }

    @When("Пользователь выбирает фильтр Электроника - Ноутбуки и компьютеры - Ноутбуки")
    public void goToCategoryLaptops() {
        hoverToCategoryMenu();
        $x(XPATH_ELECTRONICS_CATEGORY).shouldBe(enabled).click();
        $x(XPATH_LAPTOPS_AND_COMPUTERS_CATEGORY).click();
        $x(XPATH_LAPTOPS_CATEGORY).click();
    }
}

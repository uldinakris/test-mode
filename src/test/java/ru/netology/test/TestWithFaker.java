package ru.netology.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestWithFaker {

    private RegistrationInfo info;


    @Test
    void shouldSendFormRegisteredUserActive() {

        info = RegisterUserHelper.registerUser("active");
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $x("//span[@data-test-id='login']//input").val(info.getLogin());
        $x("//span[@data-test-id='password']//input").val(info.getPassword());
        $x("//button[contains(., 'Продолжить')]").click();
        $x("//*[contains(text(),'Личный кабинет')]")
                .should(Condition.visible, Duration.ofSeconds(15));

        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertEquals("http://localhost:9999/dashboard", currentUrl);
        $x("//h2[contains(., 'Личный кабинет')]").should(Condition.visible);

    }

    @Test
    void shouldSendFormRegisteredUserNonActive() {

        info = RegisterUserHelper.registerUser("blocked");
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $x("//span[@data-test-id='login']//input").val(info.getLogin());
        $x("//span[@data-test-id='password']//input").val(info.getPassword());
        $x("//button[contains(., 'Продолжить')]").click();
        $x("//div[@data-test-id='error-notification']//div[contains(., 'Пользователь заблокирован')]")
                .should(Condition.visible, Duration.ofSeconds(15));

    }

    @Test
    void shouldSendFormNonValidPassword() {

        info = RegisterUserHelper.registerUser("active");
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $x("//span[@data-test-id='login']//input").val(info.getLogin());
        $x("//span[@data-test-id='password']//input").val(
                DataGenerator.Registration.generatePassword()
        );
        $x("//button[contains(., 'Продолжить')]").click();
        $x("//div[@data-test-id='error-notification']//div[contains(., 'Неверно указан логин или пароль')]")
                .should(Condition.visible, Duration.ofSeconds(15));

    }

    @Test
    void shouldSendFormNonValidLogin() {

        info = RegisterUserHelper.registerUser("active");
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $x("//span[@data-test-id='login']//input").val(
                DataGenerator.Registration.generateUsername()
        );
        $x("//span[@data-test-id='password']//input").val(info.getPassword());
        $x("//button[contains(., 'Продолжить')]").click();
        $x("//div[@data-test-id='error-notification']//div[contains(., 'Неверно указан логин или пароль')]")
                .should(Condition.visible, Duration.ofSeconds(15));

    }

}

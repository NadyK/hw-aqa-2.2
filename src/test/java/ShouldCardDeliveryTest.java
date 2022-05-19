import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;


public class ShouldCardDeliveryTest {
    public String generateDate(int days) {

        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

    }
    @Test
    public void shouldCardDelivery () {
        String planningDate = generateDate(3);
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
        $( "[data-test-id= city] input").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $(byName("name")).setValue("Василий Петров");
        $( "[data-test-id=phone] input").setValue("+79998881234");

        $("[data-test-id=agreement]").click();
        $(byClassName("button")).click();
        $(byText("Успешно!")).shouldBe(Condition.appear, Duration.ofSeconds(15));
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15));


    }
}

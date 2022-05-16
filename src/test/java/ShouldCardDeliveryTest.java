import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;


public class ShouldCardDeliveryTest {
    @Test
    public void shouldCardDelivery () {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
        $( "[data-test-id= city] input").setValue("Москва");
        $(byName("name")).setValue("Василий Петров");
        $( "[data-test-id=phone] input").setValue("+79998881234");
        $("[data-test-id=agreement]").click();
        $(byClassName("button")).click();
        $(byText("Успешно!")).shouldBe(Condition.appear, Duration.ofSeconds(15));

    }
}

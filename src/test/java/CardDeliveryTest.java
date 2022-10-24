import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {

    String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    void correctTest() {
        String date = generateDate(4);
        open("http://localhost:9999");
        $("span[data-test-id='city'] input").setValue("Санкт-Петербург");
        $("span[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT,Keys.HOME),Keys.BACK_SPACE);
        $("span[data-test-id='date'] input").setValue(date);
        $("span[data-test-id='name'] input").setValue("Василькова-Иванова Маргарита");
        $("span[data-test-id='phone'] input").setValue("+71234567890");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(".notification__content").shouldHave(Condition.text("Встреча успешно забронирована на " + date), Duration.ofSeconds(15)).shouldBe(Condition.visible);
    }
}

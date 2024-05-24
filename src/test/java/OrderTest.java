import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class OrderTest {
    @Test
    void shouldFullFillingOrder(){
        open("http://localhost:9999/");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").val("Кузнецов Иван");
        form.$("[data-test-id=phone] input").val("+79150000000");
        form.$("[data-test-id=agreement").click();
        form.$(".button__text").click();
        $("[data-test-id=order-success]").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));

    }

    @Test
    void onlyName(){
        open("http://localhost:9999/");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").val("Иван");
        form.$("[data-test-id=phone] input").val("+79150000000");
        form.$("[data-test-id=agreement").click();
        form.$(".button__text").click();
        $("[data-test-id=order-success]").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));

    }

    @Test
    void uncorrectedPhone(){
        open("http://localhost:9999/");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").val("Иван");
        form.$("[data-test-id=phone] input").val("89150000000");
        form.$("[data-test-id=agreement").click();
        form.$(".button__text").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }

    @Test
    void uncorrectedName(){
        open("http://localhost:9999/");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").val("Qwerty");
        form.$("[data-test-id=phone] input").val("+79150000000");
        form.$("[data-test-id=agreement").click();
        form.$(".button__text").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }
    @Test
    void noAgreement(){
        open("http://localhost:9999/");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").val("Иван");
        form.$("[data-test-id=phone] input").val("+79150000000");

        form.$(".button__text").click();
        $("[data-test-id=agreement].input_invalid").shouldHave(Condition.visible);

    }
    @Test
    void noName(){
        open("http://localhost:9999/");
        SelenideElement form = $(".form");

        form.$("[data-test-id=phone] input").val("+79150000000");
        form.$("[data-test-id=agreement").click();
        form.$(".button__text").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void noPhone(){
        open("http://localhost:9999/");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").val("Иван");

        form.$("[data-test-id=agreement").click();
        form.$(".button__text").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));

    }

}

package homework8;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selectors.byText;

public class ParamSpotifyTest {


    @CsvSource(value = {
            "Metallica, Metallica и не только.",
            "Blur, Blur и не только."
    })
    @ParameterizedTest(name = "Поиск в Spotify исполнителя: {0}" )
    void spotifySearchTest(String testData, String expectedResult) {
        Selenide.open("https://open.spotify.com/");
        Selenide.$(byText("Поиск")).click();
        Selenide.$("[data-testid='search-input']").setValue(testData).pressEnter();
        Selenide.$(byText(expectedResult))
                .shouldHave(Condition.text(expectedResult));
    }

    @ValueSource(strings = {"0ZERO", "олнайт"})
    @ParameterizedTest(name = "Тестирование прослушивания исполнителя: {0}" )
    void commonSearchTest(String testData) {
        Selenide.open("https://open.spotify.com/");
        Selenide.$(byText("Поиск")).click();
        Selenide.$("[data-testid='search-input']").setValue(testData).pressEnter();
        Selenide.$("[data-testid='herocard-click-handler']").click();
        Selenide.$("[aria-rowindex='1']").doubleClick();
        Selenide.$(byText("Слушай что угодно в бесплатной версии Spotify"))
                .shouldHave(Condition.text("Слушай что угодно в бесплатной версии Spotify"));
    }

    static Stream<Arguments> spotifyTestDataProvider() {
        return Stream.of(
                Arguments.of("The Offspring", false, "Days Go By"),
                Arguments.of("0ZERO", true, "Гавана клаб")
        );
    }

    @MethodSource("spotifyTestDataProvider")
    @ParameterizedTest(name = "Тестирование общего алгоритма поиска с тестовыми данными: {0}" )
    void superSpotifyTest(String testData, boolean flag, String expectedRes) {
        System.out.println("Russian artist: " + flag);

        Selenide.open("https://open.spotify.com/");
        Selenide.$(byText("Поиск")).click();
        Selenide.$("[data-testid='search-input']").setValue(testData).pressEnter();
        Selenide.$("[data-testid='herocard-click-handler']").click();
        Selenide.$(byText(expectedRes))
                .shouldHave(Condition.text(expectedRes));
    }
}

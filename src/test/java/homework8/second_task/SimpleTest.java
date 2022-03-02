package homework8.second_task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


//@Disabled - чтоб выключить все тесты, находящиеся в классе
@DisplayName("Класс с тестами проверяющими 3 и 2")
public class SimpleTest {


    @Test
    @Disabled
    @DisplayName("Тест проверяет 3 < 2 (выкл)")
    void test() {
        Assertions.assertTrue(3 < 2);
    }

    @Test
    @DisplayName("Тест проверяет 3 > 2")
    void test1() {
        Assertions.assertTrue(3 > 2);
    }

    @Test
    @DisplayName("Тест проверяет null")
    void test2() {
        throw new NullPointerException();
    }

    @Test
    @DisplayName("Пустой тест")
    void test3() {
    }
}

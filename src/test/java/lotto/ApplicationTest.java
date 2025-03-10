package lotto;

import camp.nextstep.edu.missionutils.test.NsTest;


import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    private enum MESSAGE{
        //Result_MESSAGE1(),
        ERROR_MESSAGE1("[ERROR] 숫자를 입력해주세요."),
        ERROR_MESSAGE2("[ERROR] 1000원 이상 넣어야 1장 이상 살 수 있습니다.");
        private final String table_value;

        MESSAGE(String table_value){
            this.table_value = table_value;
        }
        public String getValue() {return table_value;}
    }
    @DisplayName("이상적인 경우 확인")
    @Test
    void 기능_테스트() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    run("8000", "1,2,3,4,5,6", "7");
                    assertThat(output()).contains(
                            "8개를 구매했습니다.",
                            "[8, 21, 23, 41, 42, 43]",
                            "[3, 5, 11, 16, 32, 38]",
                            "[7, 11, 16, 35, 36, 44]",
                            "[1, 8, 11, 31, 41, 42]",
                            "[13, 14, 16, 38, 42, 45]",
                            "[7, 11, 30, 40, 42, 43]",
                            "[2, 13, 22, 32, 38, 45]",
                            "[1, 3, 5, 14, 22, 45]",
                            "3개 일치 (5,000원) - 1개",
                            "4개 일치 (50,000원) - 0개",
                            "5개 일치 (1,500,000원) - 0개",
                            "5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
                            "6개 일치 (2,000,000,000원) - 0개",
                            "총 수익률은 62.5%입니다."
                    );
                },
                List.of(8, 21, 23, 41, 42, 43),
                List.of(3, 5, 11, 16, 32, 38),
                List.of(7, 11, 16, 35, 36, 44),
                List.of(1, 8, 11, 31, 41, 42),
                List.of(13, 14, 16, 38, 42, 45),
                List.of(7, 11, 30, 40, 42, 43),
                List.of(2, 13, 22, 32, 38, 45),
                List.of(1, 3, 5, 14, 22, 45)
        );
    }
    @DisplayName("숫자가 아닌 금액 확인")
    @Test
    void 예외_테스트() {
        assertSimpleTest(() -> {
            runException("1000j");
            assertThat(output()).contains(MESSAGE.ERROR_MESSAGE1.getValue());
        });
    }
    @DisplayName("금액이 너무 작을 경우")
    @Test
    void 예외_테스트2() {
        assertSimpleTest(() -> {
            runException("900");
            assertThat(output()).contains(MESSAGE.ERROR_MESSAGE2.getValue());
        });
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}

package calc;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;
import static calc.StringCalculator.add;

@RunWith(JUnitParamsRunner.class)
public class StringCalculatorTest {

    @Test
    @Parameters
    public void testAdd(String input, int expected) throws Exception {
        // given
        // when
        int result = add(input);

        // then
        assertThat(result).isEqualTo(expected);
    }


    @Test(expected = IllegalArgumentException.class)
    @Parameters
    public void testAddAndExpectException(String input, String exceptionMessage) {
        // given
        // when
        try {
            add(input);
        } catch(IllegalArgumentException e) {
            assertThat(e.getMessage()).isEqualTo(exceptionMessage);
            throw e;
        }
    }

    private Object parametersForTestAddAndExpectException() {
        return new Object[]{
                param("-2", "negatives are not allowed: [-2]"),
                param("1,-2", "negatives are not allowed: [-2]"),
                param("1,-2,3,-4", "negatives are not allowed: [-2, -4]"),
                param("1\n-2,3\n-4", "negatives are not allowed: [-2, -4]"),
                param("//!\n-6!-2!3!-4!5", "negatives are not allowed: [-6, -2, -4]"),
                param("//\n1,-2\n3\n-8,5", "negatives are not allowed: [-2, -8]")
        };
    }


    private Object parametersForTestAdd() {
        return new Object[] {
                param("1", 1),
                param("1,2",3),
                param("1,2,3,4", 10),
                param("1\n2,3\n4", 10),
                param("//!\n1!2!3!4!5", 15),
                param("//\n1,2\n3\n4,5", 15)
        };
    }

    private Object[] param(String s, String exceptionMessage) {
        return new Object[]{s, exceptionMessage};
    }

    private Object[] param(String s, int i) {
        return new Object[]{s, i};
    }
}
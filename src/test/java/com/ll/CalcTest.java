package com.ll;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
// 목표 Calc.run("((3 + 5) * 5 + -10) * 10 / 5");
public class CalcTest {
    @Test
    @DisplayName("3 + 5 = 8")
    public void t1() {
        int rs = Calc.run1("3 + 5");

        assertThat(8).isEqualTo(rs);
    }
    @Test
    @DisplayName("10 + 5 = 15")
    public void t2() {
        int rs = Calc.run1("10 + 5");

        assertThat(15).isEqualTo(rs);
    }
    @Test
    @DisplayName("(3 + 5) * 5 = 40")
    public void t3() {
        int rs = Calc.run2("(3 + 5) * 5");

        assertThat(40).isEqualTo(rs);
    }
    @Test
    @DisplayName("(10 + 5) * 5 = 75")
    public void t4() {
        int rs = Calc.run2("(10 + 5) * 5");

        assertThat(75).isEqualTo(rs);
    }
}

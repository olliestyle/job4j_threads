package ru.job4j.concurrent.pool;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class FindIndexTest {
    @Test
    public void whenFind() {
        String[] stringsBig = {"aaa", "bbb", "ccc", "ddd", "eee", "fff", "ggg", "hhh", "iii",
                "jjj", "kkk", "lll", "mmm", "nnn", "ooo", "ppp", "qqq", "rrr",
                "sss", "ttt", "uuu", "vvv", "www", "xxx", "yyy", "zzz", "111",
                "222", "333", "444", "555", "666", "777", "888", "999"};
        FindIndex<String> stringFindIndex = new FindIndex<>(stringsBig, "999", 0, stringsBig.length - 1);
        assertThat(stringFindIndex.findIndex(), is(34));
    }

    @Test
    public void whenNotFind() {
        String[] stringsBig = {"aaa", "bbb", "ccc", "ddd", "eee", "fff", "ggg", "hhh", "iii",
                "jjj", "kkk", "lll", "mmm", "nnn", "ooo", "ppp", "qqq", "rrr",
                "sss", "ttt", "uuu", "vvv", "www", "xxx", "yyy", "zzz", "111",
                "222", "333", "444", "555", "666", "777", "888", "999"};
        FindIndex<String> stringFindIndex = new FindIndex<>(stringsBig, "abc", 0, stringsBig.length - 1);
        assertThat(stringFindIndex.findIndex(), is(-1));
    }
}
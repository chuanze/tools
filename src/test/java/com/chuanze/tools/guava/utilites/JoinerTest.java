package com.chuanze.tools.guava.utilites;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.Files;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@Slf4j
public class JoinerTest {
    private final List<String> stringList = Arrays.asList(
            "Google", "Guava", "Java", "Scala", "Kafka"
    );

    private final List<String> stringListWithNullValue = Arrays.asList(
            "Google", "Guava", "Java", "Scala", null
    );

    private final String targetFileName = "E:\\Test\\guava-joiner.txt";
    private final String targetFileNameToMap = "E:\\Test\\guava-joiner-map.txt";

    private final Map<String, String> stringMap = ImmutableMap.of("Hello", "Guava", "Java", "Scala");

    @Test
    public void testJoinOnJoin() {
        String resutl = Joiner.on("#").join(stringList);
        log.info(resutl);
        assertEquals(resutl, "Google#Guava#Java#Scala#Kafka");
    }

    @Test
    public void testJoinOnJoinWithNullValueButSkip() {
        String result = Joiner.on("#").skipNulls().join(stringListWithNullValue);
        log.info(result);
        assertEquals(result, "Google#Guava#Java#Scala");
    }

    @Test
    public void testJoin_On_JoinWithNullValue_UserDefaultValue() {
        String result = Joiner.on("#").useForNull("DEFAULT").join(stringListWithNullValue);
        log.info(result);
        assertEquals(result, "Google#Guava#Java#Scala#DEFAULT");
    }

    @Test
    public void testJoin_On_Append_To_StringBuilder() {
        final StringBuilder builder = new StringBuilder();
        StringBuilder stringBuilder = Joiner.on("#").useForNull("DEFAULT").appendTo(builder, stringListWithNullValue);
        assertEquals(stringBuilder, builder);
        assertEquals(stringBuilder.toString(), builder.toString());
    }

    @Test
    public void testJoin_On_Append_To_Writer() {
        try (FileWriter fileWriter = new FileWriter(new File(targetFileName))) {
            Joiner.on("#").useForNull("DEFAULT").appendTo(fileWriter, stringListWithNullValue);
            assertEquals(Files.isFile().test(new File(targetFileName)), true);

        } catch (IOException e) {
            log.error(e.getMessage(), e);
            fail("append to the writer occur fetal error");
        }
    }

    /**
     * 使用 java8 来实现
     */
    @Test
    public void testJoiningByStreamSkipNullValues() {
        String result = stringListWithNullValue.stream()
                .filter(item -> null != item && !item.isEmpty())
                .collect(Collectors.joining("#"));
        assertEquals(result, "Google#Guava#Java#Scala");
    }

    @Test
    public void testJoiningByStreamWithDefaultValue() {
        String result = stringListWithNullValue.stream()
                .map(this::defaultValue)
                .collect(Collectors.joining("#"));
        assertEquals(result, "Google#Guava#Java#Scala#DEFAULT");
    }

    private String defaultValue(final String item) {
        return null == item || item.isEmpty() ? "DEFAULT" : item;
    }

    @Test
    public void testJoinOnWithMap() {
        String result = Joiner.on("#").withKeyValueSeparator("=").join(stringMap);
        log.info(result);
        assertEquals(result, "Hello=Guava#Java=Scala");
    }

    @Test
    public void testJoinOnWithMapToAppendable() {
        try (FileWriter fileWriter = new FileWriter(new File(targetFileNameToMap))) {
            Joiner.on("#").withKeyValueSeparator("=").appendTo(fileWriter, stringMap);
            assertEquals(Files.isFile().test(new File(targetFileName)), true);

        } catch (IOException e) {
            log.error(e.getMessage(), e);
            fail("append to the writer occur fetal error");
        }
    }

}

package com.mobiqiuty.packer;

import com.mobiquity.exception.APIException;
import com.mobiquity.packer.Packer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Objects;

/**
 * @author mahmood
 * @since 7/17/21
 */
public class PackerTest {

    @Test
    void test_OK() throws APIException {
        String packResponse = Packer.pack(Objects.requireNonNull(this.getClass().getResource("/example_input")).getPath());
        Assertions.assertThat(packResponse).isEqualTo("4\n" +
                "-\n" +
                "2,7\n" +
                "8,9");
    }

    @Test
    void test_OK2() throws APIException {
        String packResponse = Packer.pack(Objects.requireNonNull(this.getClass().getResource("/example_input2")).getPath());
        Assertions.assertThat(packResponse).isEqualTo("4\n" +
                "2,3,4");
    }

    @Test
    void test_OK3() throws APIException {
        String packResponse = Packer.pack(Objects.requireNonNull(this.getClass().getResource("/example_input3")).getPath());
        Assertions.assertThat(packResponse).isEqualTo("8,9\n"+
                "6,9\n"+
                "6,10\n"+
                "7,9,10");
    }

    @Test
    void test_EmptyPath() {
        Assertions.assertThatThrownBy(() -> Packer.pack(""))
                .isInstanceOf(APIException.class)
                .hasMessage("File path is empty");
    }

    @Test
    void test_fileNotFound() {
        Assertions.assertThatThrownBy(() -> Packer.pack("/some/invalid/path"))
                .isInstanceOf(APIException.class)
                .hasMessage("File not found");
    }

}

package kr.ac.phdljr.sbaws.web.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HelloResponseDtoTest {
    @Test
    void 롬복_기능_테스트() {
        String name = "test";
        int amount = 1000;

        HelloResponseDto helloResponseDto = new HelloResponseDto(name, amount);

        assertThat(helloResponseDto.getName()).isEqualTo("test");
        assertThat(helloResponseDto.getAmount()).isEqualTo(1000);
    }
}
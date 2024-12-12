package yjh.board.reply.presentation.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ReplyRequest {

    @NotNull
    private Long postId;

    @NotBlank
    private String memberName;

    @NotBlank
    @Size(min = 4, max = 4)
    private String password;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

}

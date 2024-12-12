package yjh.board.board.presentation.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import yjh.board.board.infrastructure.BoardEntity;

@Getter
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BoardResponse {
    private Long id;

    private String boardName;

    private String status;

    public static BoardResponse from(BoardEntity board) {
        return BoardResponse.builder()
                .id(board.getId())
                .boardName(board.getBoardName())
                .status(board.getStatus())
                .build();
    }
}

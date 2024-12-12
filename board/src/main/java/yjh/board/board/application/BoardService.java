package yjh.board.board.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yjh.board.board.infrastructure.BoardEntity;
import yjh.board.board.infrastructure.BoardRepository;
import yjh.board.board.presentation.dto.BoardRequest;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardEntity create(BoardRequest boardRequest) {
        BoardEntity entity = BoardEntity.builder()
                .boardName(boardRequest.getBoardName())
                .status("REGISTERED")
                .build();

        return boardRepository.save(entity);
    }

    public BoardEntity retrieve(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found"));
    }
}

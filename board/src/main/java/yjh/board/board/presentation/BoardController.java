package yjh.board.board.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yjh.board.board.application.BoardService;
import yjh.board.board.infrastructure.BoardRepository;
import yjh.board.board.presentation.dto.BoardRequest;
import yjh.board.board.presentation.dto.BoardResponse;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final BoardRepository boardRepository;

    @PostMapping
    public BoardResponse create(
            @Valid @RequestBody BoardRequest boardRequest
    ) {
        var board = boardService.create(boardRequest);
        return BoardResponse.from(board);
    }


    @GetMapping("/{id}")
    public BoardResponse retrieve(
            @PathVariable("id") Long id
    ) {
        var board = boardService.retrieve(id);
        return BoardResponse.from(board);
    }

    @GetMapping
    public List<BoardResponse> retrieveAll() {
        return boardRepository.findAll().stream()
                .map(BoardResponse::from)
                .toList();
    }
}

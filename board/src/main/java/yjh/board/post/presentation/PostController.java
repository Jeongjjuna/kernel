package yjh.board.post.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yjh.board.common.response.PaginationApi;
import yjh.board.post.application.PostService;
import yjh.board.post.infrastructure.PostEntity;
import yjh.board.post.presentation.dto.PostRequest;
import yjh.board.post.presentation.dto.PostViewRequest;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public PostEntity create(
            @Valid @RequestBody PostRequest postRequest
    ) {
        return postService.create(postRequest);
    }

    @PostMapping("/retrieve")
    public PostEntity retrieve(
            @Valid @RequestBody PostViewRequest postViewRequest
    ) {
        return postService.view(postViewRequest);
    }


    @GetMapping
    public PaginationApi<List<PostEntity>> retrieveAll(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC)
            Pageable pageable
    ) {
        return postService.all(pageable);
    }

    @DeleteMapping
    public void delete(
            @Valid @RequestBody PostViewRequest postViewRequest
    ) {
        postService.delete(postViewRequest);
    }
}

package yjh.board.post.application;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import yjh.board.board.infrastructure.BoardRepository;
import yjh.board.common.response.Pagination;
import yjh.board.common.response.PaginationApi;
import yjh.board.post.infrastructure.PostEntity;
import yjh.board.post.infrastructure.PostRepository;
import yjh.board.post.presentation.dto.PostRequest;
import yjh.board.post.presentation.dto.PostViewRequest;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final BoardRepository boardRepository;

    public PostEntity create(
            PostRequest postRequest
    ) {
        var boardEntity = boardRepository.findById(postRequest.getBoardId())
                .orElseThrow(() -> new IllegalArgumentException("not fount"));

        var entity = PostEntity.builder()
                .boardEntity(boardEntity)
                .memberName(postRequest.getMemberName())
                .password(postRequest.getPassword())
                .email(postRequest.getEmail())
                .status("REGISTERED")
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .postedAt(LocalDateTime.now())
                .build();

        return postRepository.save(entity);
    }

    public PostEntity view(PostViewRequest postViewRequest) {

        return postRepository.findFirstByIdAndStatusOrderByIdDesc(postViewRequest.getPostId(), "REGISTERED")
                .map(it -> {
                    if (!it.getPassword().equals(postViewRequest.getPassword())) {
                        var format = "패스워드가 맞지 않습니다 %s vs %s";
                        throw new RuntimeException(String.format(format, it.getPassword(), postViewRequest.getPassword()));
                    }
                    return it;

                }).orElseThrow(
                        () -> {
                            return new RuntimeException("해당 게시글이 존재 하지 않습니다 : " + postViewRequest.getPostId());
                        }
                );
    }

    public PaginationApi<List<PostEntity>> all(Pageable pageable) {
        var list = postRepository.findAll(pageable);

        var pagination = Pagination.builder()
                .page(list.getNumber())
                .size(list.getSize())
                .currentElements(list.getNumberOfElements())
                .totalElements(list.getTotalElements())
                .totalPage(list.getTotalPages())
                .build();

        return PaginationApi.<List<PostEntity>>builder()
                .body(list.toList())
                .pagination(pagination)
                .build();
    }

    public void delete(PostViewRequest postViewRequest) {
        postRepository.findById(postViewRequest.getPostId())
                .map(it -> {
                    if (!it.getPassword().equals(postViewRequest.getPassword())) {
                        var format = "패스워드가 맞지 않습니다 %s vs %s";
                        throw new RuntimeException(String.format(format, it.getPassword(), postViewRequest.getPassword()));
                    }

                    it.setStatus("UNREGISTERED");
                    postRepository.save(it);
                    return it;
                }).orElseThrow(() -> new RuntimeException("해당 게시글이 존재 하지 않습니다 : " + postViewRequest.getPostId()));
    }
}

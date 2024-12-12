package yjh.board.reply.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yjh.board.post.infrastructure.PostRepository;
import yjh.board.reply.infrastructure.ReplyEntity;
import yjh.board.reply.infrastructure.ReplyRepository;
import yjh.board.reply.presentation.dto.ReplyRequest;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final PostRepository postRepository;

    public ReplyEntity create(ReplyRequest replyRequest) {
        var optionalPostEntity = postRepository.findById(replyRequest.getPostId());

        if (optionalPostEntity.isEmpty()) {
            throw new IllegalArgumentException("not found post : " + replyRequest.getPostId());
        }

        var entity = ReplyEntity.builder()
                .post(optionalPostEntity.get())
                .memberName(replyRequest.getMemberName())
                .password(replyRequest.getPassword())
                .status("REGISTERED")
                .title(replyRequest.getTitle())
                .content(replyRequest.getContent())
                .repliedAt(LocalDateTime.now())
                .build();

        return replyRepository.save(entity);
    }

    public List<ReplyEntity> findAllByPostId(Long postId) {
        return replyRepository.findAllByPostIdAndStatusOrderByIdDesc(postId, "REGISTERED");
    }
}

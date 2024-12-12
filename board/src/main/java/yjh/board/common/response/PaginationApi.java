package yjh.board.common.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PaginationApi<T> {

    private T body;

    private Pagination pagination;
}
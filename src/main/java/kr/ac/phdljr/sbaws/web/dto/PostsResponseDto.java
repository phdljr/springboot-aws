package kr.ac.phdljr.sbaws.web.dto;

import kr.ac.phdljr.sbaws.domain.posts.Posts;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@NoArgsConstructor
public class PostsResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;

    // 일부 필드만 사용할 것이기 때문에, 파라미터에 모든 필드를 적지 않음
    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}

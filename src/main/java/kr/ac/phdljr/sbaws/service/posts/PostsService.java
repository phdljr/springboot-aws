package kr.ac.phdljr.sbaws.service.posts;

import kr.ac.phdljr.sbaws.domain.posts.Posts;
import kr.ac.phdljr.sbaws.domain.posts.PostsRepository;
import kr.ac.phdljr.sbaws.web.dto.PostsListResponseDto;
import kr.ac.phdljr.sbaws.web.dto.PostsResponseDto;
import kr.ac.phdljr.sbaws.web.dto.PostsSaveRequestDto;
import kr.ac.phdljr.sbaws.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(posts);
    }

    // 조회 기능을 남겨두어 조회 속도 개선 효과를 볼 수 있음
    // 영속성 컨텍스트는 스냅샷을 보관하지 않음 -> 메모리 사용량 최적화
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        postsRepository.delete(posts);
    }
}

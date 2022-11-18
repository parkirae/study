package com.jojoldu.book.springboot.web.dto;

import com.jojoldu.book.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    
    private String title;
    private String content;
    private String author;
    
    @Builder
    public PostsSaveRequestDto(String tile, String content, String author) {
        this.title = tile;
        this.content = content;
        this.author = author;
    }
    
    public Posts toEntity() {        // View Layer
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}

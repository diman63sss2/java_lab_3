package com.example.dezdemoniyslab.requests.book;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BookCreationRequest {
    private String content;

    private Long authorId;
}

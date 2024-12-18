package com.ll.rest.domain.post.post.entity;

import com.ll.rest.global.jpa.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post extends BaseEntity {
    @Column(length = 100)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;
}

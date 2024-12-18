package com.ll.rest.domain.post.post.controller;

import com.ll.rest.domain.post.post.entity.Post;
import com.ll.rest.domain.post.post.service.PostService;
import com.ll.rest.global.redata.RsData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class ApiV1PostController {
    private final PostService postService;

    @GetMapping
    public List<Post> getItems() {
        return postService.findAllByOrderByIdDesc();
    }

    @GetMapping("/{id}")
    public Post getItem(
            @PathVariable long id
    ) {
        return postService.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public RsData deleteItem(@PathVariable long id) {
        Post post = postService.findById(id).get();
        postService.delete(post);

        return new RsData("200-1", "%d번 글을 삭제되었습니다.".formatted(id));
    }

    @AllArgsConstructor
    @Getter
    public static class PostModifyReqBody {
        private String title;
        private String content;
    }

    @PutMapping("/{id}")
    @Transactional
    public RsData modifyItem(
            @PathVariable long id
            , @RequestBody PostModifyReqBody req
    ) {
        Post post = postService.findById(id).get();
        postService.modify(post, req.getTitle(), req.getContent());

        return new RsData("200-1", "%d번 글을 수정되었습니다.".formatted(id));
    }
}

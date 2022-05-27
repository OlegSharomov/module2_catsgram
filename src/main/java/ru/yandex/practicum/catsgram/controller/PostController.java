package ru.yandex.practicum.catsgram.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.catsgram.exception.IncorrectParameterException;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.service.PostService;

import java.util.List;

import static ru.yandex.practicum.catsgram.Constants.DESCENDING_ORDER;
import static ru.yandex.practicum.catsgram.Constants.SORTS;

@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // Выводит посты по заданным параметрам. Запрос может быть 3х видов:
    // http://127.0.0.1:8080/posts
    //  http://127.0.0.1:8080/posts?sort=desc&size=128
    //  http://127.0.0.1:8080/posts?sort=asc&size=5&page=2
    @GetMapping("/posts")
    public List<Post> findAll(
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "10", required = false) Integer size,
            @RequestParam(defaultValue = DESCENDING_ORDER, required = false) String sort
    ) {
        if (!SORTS.contains(sort)) {
            throw new IncorrectParameterException("Не корректно указана сортировка", String.valueOf(page));
        }
        if(page < 0){
            throw new IncorrectParameterException("Страница, с которой будут выводиться поля - не должна быть отрицательна",
                    page.toString());
        }
        if(size <= 0){
            throw new IncorrectParameterException("Размер выводимых страниц должен быть больше 0", size.toString());
        }
        Integer from = page * size;
        return postService.findAll(size, from, sort);
    }

    @PostMapping(value = "/post")
    public Post create(@RequestBody Post post) {
        return postService.create(post);
    }

    @GetMapping("/post/{postId}")
    public Post findPost(@PathVariable("postId") Integer postId) {
        return postService.findPostById(postId);
    }
}

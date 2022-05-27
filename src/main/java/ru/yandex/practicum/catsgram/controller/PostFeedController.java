package ru.yandex.practicum.catsgram.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.catsgram.exception.IncorrectParameterException;
import ru.yandex.practicum.catsgram.model.FeedParams;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.service.PostService;

import java.util.ArrayList;
import java.util.List;

import static ru.yandex.practicum.catsgram.Constants.SORTS;

@RestController()
@RequestMapping("/feed/friends")
public class PostFeedController {

    private final PostService postService;

    public PostFeedController(PostService postService) {
        this.postService = postService;
    }

    // Возвращает последние посты друзей. Принимает тело, в котором поля:
    // String sort/ Integer size / List<String> friendsEmails;
    @PostMapping
    List<Post> getFriendsFeed(@RequestBody FeedParams feedParams) {
        if (!SORTS.contains(feedParams.getSort())) {
            throw new IncorrectParameterException("Отсутствует параметр сортировки. " +
                    "Корректные параматры: \"desc\" или \"asc\" ", feedParams.getSort());
        }
        if (feedParams.getFriendsEmails().isEmpty()) {
            throw new IncorrectParameterException("В запросе поля друзей пусты. Заполните их email's, пожалуйста.",
                    feedParams.getFriendsEmails().toString());
        }
        if (feedParams.getSize() == null || feedParams.getSize() <= 0) {
            throw new IncorrectParameterException("Не корректно указан параметр размера возвращаемых постов.",
                    String.valueOf(feedParams.getSize()));
        }

        List<Post> result = new ArrayList<>();
        for (String friendEmail : feedParams.getFriendsEmails()) {
            result.addAll(postService.findAllByUserEmail(friendEmail, feedParams.getSize(), feedParams.getSort()));
        }
        return result;
    }
}

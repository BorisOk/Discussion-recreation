package by.boris.controller;
import by.boris.entity.Comment;
import by.boris.entity.User;
import by.boris.repo.CommentRepo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;
import java.util.Objects;

@Controller
public class MainController {
    private final CommentRepo commentRepo;

    public MainController(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

    @GetMapping("/")
    public String start() {
        return "start";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter,
                       Model model) {
        Iterable<Comment> comments = commentRepo.findAll();

        if (filter != null && !filter.isEmpty()) {
            comments = commentRepo.findByCountry(filter);
        } else {
            comments = commentRepo.findAll();
        }

        model.addAttribute("comments", comments);
        model.addAttribute("filter", filter);
        return "main";
    }

    @PostMapping("/main")
    public String add(@AuthenticationPrincipal User user,
                      @RequestParam String text,
                      @RequestParam String country,
                      Map<String, Object> model) {
        if(text != null & !Objects.requireNonNull(text).isEmpty()
                & country != null & !Objects.requireNonNull(country).isEmpty()) {
            Comment comment = new Comment(text, country, user);
            commentRepo.save(comment);
        }

        Iterable<Comment> comments = commentRepo.findAll();

        model.put("comments", comments);

        return "main";
    }
}
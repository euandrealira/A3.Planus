package br.com.a3.Planus.controller;

import br.com.a3.Planus.model.User;
import br.com.a3.Planus.model.enumerable.ProfileEnum;
import br.com.a3.Planus.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import lombok.*;

import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserRepository repo;

    @GetMapping
    public String getUsers(Model model) {
        model.addAttribute("users", repo.findAll());
        return "user/list";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("profiles", ProfileEnum.values());
        return "user/form";
    }

    @PostMapping
    public String create(@ModelAttribute User user, BindingResult binding, Model model) {
        if (binding.hasErrors()) { return "user/form"; }
        user.setCpf(user.getCpf().replaceAll("\\D", ""));
        repo.save(user);
        return "redirect:/user";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("user", repo.findById(id).orElse(null));
        model.addAttribute("profiles", ProfileEnum.values());
        return "user/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute User user) {
        user.setId(id);
        repo.save(user);
        return "redirect:/user";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/user";
    }
}
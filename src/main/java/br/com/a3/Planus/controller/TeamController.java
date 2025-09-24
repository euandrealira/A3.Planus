package br.com.a3.Planus.controller;

import br.com.a3.Planus.model.Team;
import br.com.a3.Planus.repository.TeamRepository;
import br.com.a3.Planus.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import lombok.*;

@Controller
@RequestMapping("/team")
@RequiredArgsConstructor
@Slf4j
public class TeamController {

    private final TeamRepository repo;
    private final UserRepository userRepo;

    @GetMapping
    @Transactional(readOnly = true)
    public String getTeams(Model model) {
        model.addAttribute("teams", repo.findAllWithMembers());
        return "team/list";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("team", new Team());
        model.addAttribute("users", userRepo.findAll());
        return "team/form";
    }

    @PostMapping
    public String create(@ModelAttribute Team team, BindingResult binding, Model model) {
        if (binding.hasErrors()) {
            model.addAttribute("users", userRepo.findAll());
            return "team/form";
        }
        repo.save(team);
        return "redirect:/team";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("team", repo.findByIdWithMembers(id)
                .orElseThrow(() -> new IllegalArgumentException("Equipe não encontrada: " + id)));
        model.addAttribute("users", userRepo.findAll());
        return "team/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Team team) {
        team.setId(id);
        repo.save(team);
        return "redirect:/team";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/team";
    }
}

package br.com.a3.Planus.controller;

import br.com.a3.Planus.model.Project;
import br.com.a3.Planus.model.enumerable.ProfileEnum;
import br.com.a3.Planus.model.enumerable.StatusEnum;
import br.com.a3.Planus.repository.ProjectRepository;
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
@RequestMapping("/project")
@RequiredArgsConstructor
@Slf4j
public class ProjectController {

    private final ProjectRepository repo;
    private final UserRepository userRepo;
    private final TeamRepository teamRepo;

    @GetMapping
    @Transactional(readOnly = true)
    public String getProjects(Model model) {
        model.addAttribute("projects", repo.findAllWithTeams());
        return "project/list";
    }

    @GetMapping("/new")
    public String newProject(Model model) {
        model.addAttribute("project", new Project());
        model.addAttribute("users", userRepo.findByProfile(ProfileEnum.Gerente));
        model.addAttribute("statusList", StatusEnum.values());
        model.addAttribute("teams", teamRepo.findAll());
        return "project/form";
    }

    @PostMapping
    public String create(@ModelAttribute Project project, BindingResult binding, Model model) {
        if (binding.hasErrors()) {
            model.addAttribute("users", userRepo.findByProfile(ProfileEnum.Gerente));
            model.addAttribute("statusList", StatusEnum.values());
            return "project/form";
        }
        repo.save(project);
        return "redirect:/project";
    }

    @GetMapping("/{id}/edit")
    @Transactional(readOnly = true)
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("project", repo.findByIdWithTeams(id)
                .orElseThrow(() -> new IllegalArgumentException("Projeto não encontrado: " + id)));
        model.addAttribute("users", userRepo.findByProfile(ProfileEnum.Gerente));
        model.addAttribute("statusList", StatusEnum.values());
        return "project/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Project project) {
        project.setId(id);
        repo.save(project);
        return "redirect:/project";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/project";
    }
}

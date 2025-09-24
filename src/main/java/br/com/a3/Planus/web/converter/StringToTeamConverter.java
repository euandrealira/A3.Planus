package br.com.a3.Planus.web.converter;

import br.com.a3.Planus.model.Team;
import br.com.a3.Planus.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StringToTeamConverter implements Converter<String, Team> {

    private final TeamRepository repo;

    @Override
    public Team convert(String source) {
        if (source.isBlank()) return null;
        Long id = Long.valueOf(source);
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Equipe não encontrada: " + source));
    }
}

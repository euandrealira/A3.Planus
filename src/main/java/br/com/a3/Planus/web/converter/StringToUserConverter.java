package br.com.a3.Planus.web.converter;

import br.com.a3.Planus.model.User;
import br.com.a3.Planus.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StringToUserConverter implements Converter<String, User> {

    private final UserRepository repo;

    @Override
    public User convert(String source) {
        if (source.isBlank()) return null;
        Long id = Long.valueOf(source);
        return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado: " + source));
    }
}
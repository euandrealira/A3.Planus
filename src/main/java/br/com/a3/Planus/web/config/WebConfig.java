package br.com.a3.Planus.web.config;

import br.com.a3.Planus.web.converter.StringToTeamConverter;
import br.com.a3.Planus.web.converter.StringToUserConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final StringToUserConverter stringToUserConverter;
    private final StringToTeamConverter stringToTeamConverter;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToUserConverter);
        registry.addConverter(stringToTeamConverter);
    }
}

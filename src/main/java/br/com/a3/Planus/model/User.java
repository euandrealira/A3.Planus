package br.com.a3.Planus.model;

import br.com.a3.Planus.model.enumerable.ProfileEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;
import lombok.*;


@Entity
@Table(name = "TB_USER")
@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Nome é obrigatório")
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "CPF é obrigatório")
    @CPF(message = "CPF inválido")
    private String cpf;

    @Column(nullable = false)
    @NotBlank(message = "E-mail é obrigatório")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "Cargo é obrigatório")
    private String role;

    @Column(nullable = false)
    @NotBlank(message = "Login é obrigatório")
    private String login;

    @Column(nullable = false)
    @NotBlank(message = "Senha é obrigatória")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull
    private ProfileEnum profile;
}

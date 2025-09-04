package com.isaque.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Generated;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@Table(name = "pessoa")
public class Pessoa implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "{validation.name.notblank}")
    private String nome;
    @NotBlank(message = "{validation.email.notblank}")
    @Email(message = "{validation.email.notvalid}")
    private String email;
    @JsonIgnore
    private String senha;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @Setter(value = AccessLevel.NONE)
    private List<PessoaPerfil> pessoaPerfil;

    public void setPessoaPerfil(List<PessoaPerfil> pessoaPerfil) {
        for (PessoaPerfil p:pessoaPerfil) {
            p.setPessoa(this);
        }
        this.pessoaPerfil = pessoaPerfil;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "{validation.name.notblank}") String getNome() {
        return nome;
    }

    public void setNome(@NotBlank(message = "{validation.name.notblank}") String nome) {
        this.nome = nome;
    }

    public @NotBlank(message = "{validation.email.notblank}") @Email(message = "{validation.email.notvalid}") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "{validation.email.notblank}") @Email(message = "{validation.email.notvalid}") String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<PessoaPerfil> getPessoaPerfil() {
        return pessoaPerfil;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return pessoaPerfil.stream().map(user -> new SimpleGrantedAuthority(user.getPerfil().getNome())).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }
}

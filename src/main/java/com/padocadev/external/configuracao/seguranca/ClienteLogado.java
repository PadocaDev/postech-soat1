package com.padocadev.external.configuracao.seguranca;

import com.padocadev.entities.cliente.Cliente;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Optional;

@Component
@RequestScope
public class ClienteLogado {

    public Optional<Cliente> getCliente() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) return Optional.empty();
        Object principal = authentication.getPrincipal();
        if (principal instanceof CustomClientDetails customClientDetails) {
            return Optional.ofNullable(customClientDetails.getCliente());
        }
        return Optional.empty();
    }
}

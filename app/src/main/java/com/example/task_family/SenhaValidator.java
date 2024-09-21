package com.example.task_family;

public class SenhaValidator {
    private String mensagemErro;

    public boolean validarSenha(String senha, String confirmarSenha) {
        if (senha.isEmpty() || confirmarSenha.isEmpty()) {
            mensagemErro = "Por favor, preencha ambos os campos de senha.";
            return false;
        }

        if (!senha.equals(confirmarSenha)) {
            mensagemErro = "As senhas não coincidem.";
            return false;
        }

        if (senha.length() < 6) {
            mensagemErro = "A senha deve ter pelo menos 6 caracteres.";
            return false;
        }

        return true;
    }

    public String getMensagemErro() {
        return mensagemErro;
    }
}

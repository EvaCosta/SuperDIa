package br.com.superdia.modelo;

public enum PerfilUsuario {

    ADMIN("Admin"),
    CAIXA("Caixa"),
    CLIENTE("Cliente");

    private String tipo;

    PerfilUsuario(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return tipo;
    }
}

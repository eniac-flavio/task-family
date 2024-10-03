package com.example.task_family;

public class Dependente extends User {
    private int responsavelId;
    private int[] tarefas;
    private int pontos;
    private int[] skins;
    private int[] conquistas;

    // Construtores sem parâmetros (padrão) e com parâmetros (opcional)
    public Dependente() {
        super();
    }
    public Dependente(int id, String email, String password, String nome, String tipo, int responsavelId, int[] tarefas, int pontos, int[] skins, int[] conquistas) {
        super(id, email, password, nome, tipo);
        this.responsavelId = responsavelId;
        this.tarefas = tarefas;
        this.pontos = pontos;
        this.skins = skins;
        this.conquistas = conquistas;
    }

    // Método para converter um array de inteiros para uma string separada por vírgula
    public static String arrayToString(int[] array) {
        if (array == null || array.length == 0) return "";
        StringBuilder sb = new StringBuilder();
        for (int num : array) {
            sb.append(num).append(",");
        }
        return sb.substring(0, sb.length() - 1); // Remove a última vírgula
    }

    // Método para converter uma string separada por vírgula para um array de inteiros
    public static int[] stringToArray(String str) {
        if (str == null || str.isEmpty()) return new int[0];
        String[] parts = str.split(",");
        int[] result = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            result[i] = Integer.parseInt(parts[i].trim());
        }
        return result;
    }

    // Getters e Setters
    public int getResponsavelId() {
        return responsavelId;
    }
    public void setResponsavelId(int responsavelId) {
        this.responsavelId = responsavelId;
    }

    public int[] getTarefas() {
        return tarefas;
    }
    public void setTarefas(int[] tarefas) {
        this.tarefas = tarefas;
    }

    public int getPontos() {
        return pontos;
    }
    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public int[] getSkins() {
        return skins;
    }
    public void setSkins(int[] skins) {
        this.skins = skins;
    }

    public int[] getConquistas() {
        return conquistas;
    }
    public void setConquistas(int[] conquistas) {
        this.conquistas = conquistas;
    }

    @Override
    public String getTableName() {
        return "dependente";
    }

    @Override
    public String getCreateTableSQL() {
        return "CREATE TABLE " + getTableName() + " ("
                + "id integer primary key autoincrement not null,"
                + "email text not null,"
                + "password text not null,"
                + "nome text not null,"
                + "tipo text not null,"
                + "responsavel_id integer not null,"
                + "pontos integer not null default 0,"
                + "tarefas text," // É necessário atualizar int[] para texto no SQLite
                + "skins text,"
                + "conquistas text,"
                + "FOREIGN KEY(responsavel_id) REFERENCES responsavel(id) ON DELETE CASCADE)";
    }
}

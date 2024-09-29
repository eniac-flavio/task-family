package com.example.task_family;

public abstract class UserEvent {

    // Evento SaveUser (Singleton)
    public static class SaveUser extends UserEvent {
        private static final SaveUser INSTANCE = new SaveUser();

        private SaveUser() {
        }

        public static SaveUser getInstance() {
            return INSTANCE;
        }
    }

    // Evento SetEmail com campo de dados
    public static class SetEmail extends UserEvent {
        private final String email;

        public SetEmail(String email) {
            this.email = email;
        }

        public String getEmail() {
            return email;
        }
    }

    // Evento SetPassword com campo de dados
    public static class SetPassword extends UserEvent {
        private final String password;

        public SetPassword(String password) {
            this.password = password;
        }

        public String getPassword() {
            return password;
        }
    }

    // Evento para salvar um usuário com email e senha
    public static class SaveUserWithDetails extends UserEvent {
        private final String email;
        private final String password;

        public SaveUserWithDetails(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }
    }
}

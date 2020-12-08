package com.example.progetto_android.view.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Objects;

public class Login {

    private boolean loggedIn;
    private String username;
    private boolean isAdmin;

    public Login(boolean loggedIn, String username, boolean isAdmin) {
        this.loggedIn = loggedIn;
        this.username = username;
        this.isAdmin = isAdmin;
    }

    public Login() {
    }

    public boolean getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @NonNull
    @Override
    public String toString() {
        return "LoginData{" + "loggedIn=" + loggedIn + ", username='" + username + '\'' +
                ", isAdmin='" + isAdmin + '\'' + '}';
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Login loginData = (Login) obj;
        return Objects.equals(loggedIn, loginData.loggedIn) &&
                Objects.equals(username, loginData.username) &&
                Objects.equals(isAdmin, loginData.isAdmin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loggedIn, username);
    }
}

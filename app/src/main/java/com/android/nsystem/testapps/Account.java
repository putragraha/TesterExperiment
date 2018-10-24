package com.android.nsystem.testapps;

import java.util.Objects;

public class Account {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return username;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.username);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Account) {
            Account other = (Account) obj;
            return Objects.equals(this.username, other.username);
        }
        return false;
    }
}

package com.cagnosolutions.cei.houseontherock.fantheflamedates.domain;

import javax.persistence.*;

@Entity
@Table(name="user")
public class User {

    @Id
    private String username;
	private String password;
    private String name;
    private String email;
	private String role;
	private boolean active;
	private short challengeProgress;
	private boolean challengeComplete;
	
	public User() {}

	public User(String username, String password, String name, String email, String role, boolean active, short challengeProgress, boolean challengeComplete) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.role = role;
		this.active = active;
		this.challengeProgress = challengeProgress;
		this.challengeComplete = challengeComplete;
	}

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

	public short getChallengeProgress() {
		return challengeProgress;
	}

	public void setChallengeProgress(short challengeProgress) {
		this.challengeProgress = challengeProgress;
	}

	public boolean isChallengeComplete() {
		return challengeComplete;
	}

	public void setChallengeComplete(boolean challengeComplete) {
		this.challengeComplete = challengeComplete;
	}

	public String toString() {
        return String.format("username: %s, password: %s, name: %s, email: %s, role: %s, active: %b",
                username, password, name, email, role, active);
    }

}

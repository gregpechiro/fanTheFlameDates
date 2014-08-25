package com.cagnosolutions.cei.houseontherock.fantheflamedates.domain;

import javax.persistence.*;
import java.util.ArrayList;

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
	private boolean challengeAccepted;
	private short challengeProgress;
	private boolean challengeComplete;
	private ArrayList<String> recentlyViewed;
	
	public User() {}

	public User(String username, String password, String name, String email, String role, boolean active,
				boolean challengeAccepted, short challengeProgress, boolean challengeComplete, ArrayList<String> recentlyViewed) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.role = role;
		this.active = active;
		this.challengeAccepted = challengeAccepted;
		this.challengeProgress = challengeProgress;
		this.challengeComplete = challengeComplete;
		this.recentlyViewed = recentlyViewed;
	}

	public String toString() {
		return String.format("username: %s, password: %s, name: %s, email: %s, role: %s, active: %b",
				username, password, name, email, role, active);
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

	public boolean isActive() {
		return active;
	}

	public boolean isChallengeAccepted() {
		return challengeAccepted;
	}

	public void setChallengeAccepted(boolean challengeAccepted) {
		this.challengeAccepted = challengeAccepted;
	}

	public ArrayList<String> getRecentlyViewed() {
		return recentlyViewed;
	}

	public void setRecentlyViewed(ArrayList<String> recentlyViewed) {
		this.recentlyViewed = recentlyViewed;
	}

	public void advanceChallenge() {
		if (challengeAccepted || !challengeComplete) {
			challengeProgress++;
		}
	}

	public void addRecentlyViewed(String videoUri) {
		if (!recentlyViewed.contains(videoUri)) {
			recentlyViewed.add(videoUri);
			if (recentlyViewed.size() > 4) {
				recentlyViewed.remove(0);
			}
		}
	}

}

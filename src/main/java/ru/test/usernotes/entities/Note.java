package ru.test.usernotes.entities;

import java.util.Base64;
import java.util.Date;

public class Note {
    private Long id;
    private String description;
    private Long userId;
    private byte[] avatar;
    private Date createdDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public String getAvatarAsBase64() {
        return Base64.getEncoder().encodeToString(avatar);
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}

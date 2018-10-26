package ru.test.usernotes.services;

import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Random;

@Service
public class AvatarServiceImpl implements AvatarService {
    private static final String IMAGE_SERVICE_URL = "https://avatars.dicebear.com/v2/identicon/%d.svg";

    public byte[] downloadAvatar() throws IOException {
        try (InputStream stream = new URL(String.format(IMAGE_SERVICE_URL, new Random().nextLong())).openStream()) {
            return StreamUtils.copyToByteArray(stream);
        }
    }
}

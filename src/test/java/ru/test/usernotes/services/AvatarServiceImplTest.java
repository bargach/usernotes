package ru.test.usernotes.services;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.hamcrest.Matchers.greaterThan;

class AvatarServiceImplTest {
    private AvatarService avatarService = new AvatarServiceImpl();

    @Test
    void testDownloadAvatar() throws IOException {
        MatcherAssert.assertThat(avatarService.downloadAvatar().length, greaterThan(100));
    }
}
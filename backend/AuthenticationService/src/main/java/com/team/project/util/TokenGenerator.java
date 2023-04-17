package com.team.project.util;

import java.time.Instant;
import java.util.UUID;

public class TokenGenerator {
    public static String generateToken() {
        StringBuilder token = new StringBuilder();
        long currentTimeInMillisecond = Instant.now().toEpochMilli();
        return token.append(currentTimeInMillisecond).append("-")
                .append(UUID.randomUUID().toString()).toString();
    }
}

package com.cap.admin.catalogo.domain.video;

import java.time.Instant;

import com.cap.admin.catalogo.domain.events.DomainEvent;
import com.cap.admin.catalogo.domain.utils.InstantUtils;

public record VideoMediaCreated(
        String resourceId,
        String filePath,
        Instant occurredOn) implements DomainEvent {

    public VideoMediaCreated(final String resourceId, final String filePath) {
        this(resourceId, filePath, InstantUtils.now());
    }
}
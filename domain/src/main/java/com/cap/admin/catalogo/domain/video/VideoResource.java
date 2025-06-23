package com.cap.admin.catalogo.domain.video;

import com.cap.admin.catalogo.domain.resource.Resource;

public record VideoResource(
        VideoMediaType type,
        Resource resource) {

    public static VideoResource with(final VideoMediaType type, final Resource resource) {
        return new VideoResource(type, resource);
    }
}

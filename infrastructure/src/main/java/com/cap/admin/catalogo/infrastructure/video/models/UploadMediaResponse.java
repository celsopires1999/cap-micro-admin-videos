package com.cap.admin.catalogo.infrastructure.video.models;

import com.cap.admin.catalogo.domain.video.VideoMediaType;
import com.fasterxml.jackson.annotation.JsonProperty;

public record UploadMediaResponse(
        @JsonProperty("video_id") String videoId,
        @JsonProperty("media_type") VideoMediaType mediaType) {
}

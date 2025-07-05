package com.cap.admin.catalogo.infrastructure.video.models;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateVideoRequest(
        @JsonProperty("title") String title,
        @JsonProperty("description") String description,
        @JsonProperty("duration") Double duration,
        @JsonProperty("year_launched") Integer yearLaunched,
        @JsonProperty("opened") Boolean opened,
        @JsonProperty("published") Boolean published,
        @JsonProperty("rating") String rating,
        @JsonProperty("cast_members") Set<String> castMembers,
        @JsonProperty("categories") Set<String> categories,
        @JsonProperty("genres") Set<String> genres) {
}
package com.cap.admin.catalogo.domain.video;

import java.util.Optional;

import com.cap.admin.catalogo.domain.resource.Resource;

public interface MediaResourceGateway {

    AudioVideoMedia storeAudioVideo(VideoID anId, VideoResource aResource);

    ImageMedia storeImage(VideoID anId, VideoResource aResource);

    Optional<Resource> getResource(VideoID anId, VideoMediaType type);

    void clearResources(VideoID anId);
}
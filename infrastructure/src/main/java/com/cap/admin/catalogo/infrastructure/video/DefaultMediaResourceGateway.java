package com.cap.admin.catalogo.infrastructure.video;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.cap.admin.catalogo.domain.resource.Resource;
import com.cap.admin.catalogo.domain.video.AudioVideoMedia;
import com.cap.admin.catalogo.domain.video.ImageMedia;
import com.cap.admin.catalogo.domain.video.MediaResourceGateway;
import com.cap.admin.catalogo.domain.video.VideoID;
import com.cap.admin.catalogo.domain.video.VideoMediaType;
import com.cap.admin.catalogo.domain.video.VideoResource;
import com.cap.admin.catalogo.infrastructure.configuration.properties.storage.StorageProperties;
import com.cap.admin.catalogo.infrastructure.services.StorageService;

@Component
public class DefaultMediaResourceGateway implements MediaResourceGateway {

    private final String filenamePattern;
    private final String locationPattern;
    private final StorageService storageService;

    public DefaultMediaResourceGateway(final StorageProperties props, final StorageService storageService) {
        this.filenamePattern = props.getFilenamePattern();
        this.locationPattern = props.getLocationPattern();
        this.storageService = storageService;
    }

    @Override
    public AudioVideoMedia storeAudioVideo(final VideoID anId, final VideoResource videoResource) {
        final var filepath = filepath(anId, videoResource.type());
        final var aResource = videoResource.resource();
        store(filepath, aResource);
        return AudioVideoMedia.with(aResource.checksum(), aResource.name(), filepath);
    }

    @Override
    public ImageMedia storeImage(final VideoID anId, final VideoResource videoResource) {
        final var filepath = filepath(anId, videoResource.type());
        final var aResource = videoResource.resource();
        store(filepath, aResource);
        return ImageMedia.with(aResource.checksum(), aResource.name(), filepath);
    }

    @Override
    public Optional<Resource> getResource(final VideoID anId, final VideoMediaType type) {
        return this.storageService.get(filepath(anId, type));
    }

    @Override
    public void clearResources(final VideoID anId) {
        final var ids = this.storageService.list(folder(anId));
        this.storageService.deleteAll(ids);
    }

    private String filename(final VideoMediaType aType) {
        return filenamePattern.replace("{type}", aType.name());
    }

    private String folder(final VideoID anId) {
        return locationPattern.replace("{videoId}", anId.getValue());
    }

    private String filepath(final VideoID anId, final VideoMediaType aType) {
        return folder(anId)
                .concat("/")
                .concat(filename(aType));
    }

    private void store(final String filepath, final Resource aResource) {
        this.storageService.store(filepath, aResource);
    }
}

package com.cap.admin.catalogo.infrastructure.video.presenters;

import com.cap.admin.catalogo.application.video.media.upload.UploadMediaOutput;
import com.cap.admin.catalogo.application.video.retrieve.get.VideoOutput;
import com.cap.admin.catalogo.application.video.retrieve.list.VideoListOutput;
import com.cap.admin.catalogo.application.video.update.UpdateVideoOutput;
import com.cap.admin.catalogo.domain.pagination.Pagination;
import com.cap.admin.catalogo.domain.video.AudioVideoMedia;
import com.cap.admin.catalogo.domain.video.ImageMedia;
import com.cap.admin.catalogo.infrastructure.video.models.AudioVideoMediaResponse;
import com.cap.admin.catalogo.infrastructure.video.models.ImageMediaResponse;
import com.cap.admin.catalogo.infrastructure.video.models.UpdateVideoResponse;
import com.cap.admin.catalogo.infrastructure.video.models.UploadMediaResponse;
import com.cap.admin.catalogo.infrastructure.video.models.VideoListResponse;
import com.cap.admin.catalogo.infrastructure.video.models.VideoResponse;

public interface VideoApiPresenter {

    static VideoResponse present(final VideoOutput output) {
        return new VideoResponse(
                output.id(),
                output.title(),
                output.description(),
                output.launchedAt(),
                output.duration(),
                output.opened(),
                output.published(),
                output.rating().getName(),
                output.createdAt(),
                output.updatedAt(),
                present(output.banner()),
                present(output.thumbnail()),
                present(output.thumbnailHalf()),
                present(output.video()),
                present(output.trailer()),
                output.categories(),
                output.genres(),
                output.castMembers());
    }

    static AudioVideoMediaResponse present(final AudioVideoMedia media) {
        if (media == null) {
            return null;
        }
        return new AudioVideoMediaResponse(
                media.id(),
                media.checksum(),
                media.name(),
                media.rawLocation(),
                media.encodedLocation(),
                media.status().name());
    }

    static ImageMediaResponse present(final ImageMedia image) {
        if (image == null) {
            return null;
        }
        return new ImageMediaResponse(
                image.id(),
                image.checksum(),
                image.name(),
                image.location());
    }

    static UpdateVideoResponse present(final UpdateVideoOutput output) {
        return new UpdateVideoResponse(output.id());
    }

    static VideoListResponse present(final VideoListOutput output) {
        return new VideoListResponse(
                output.id(),
                output.title(),
                output.description(),
                output.createdAt(),
                output.updatedAt());
    }

    static Pagination<VideoListResponse> present(final Pagination<VideoListOutput> page) {
        return page.map(VideoApiPresenter::present);
    }

    static UploadMediaResponse present(final UploadMediaOutput output) {
        return new UploadMediaResponse(output.videoId(), output.mediaType());
    }
}

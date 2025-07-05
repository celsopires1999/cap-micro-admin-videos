package com.cap.admin.catalogo.infrastructure.configuration.usecases;

import java.util.Objects;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cap.admin.catalogo.application.video.create.CreateVideoUseCase;
import com.cap.admin.catalogo.application.video.create.DefaultCreateVideoUseCase;
import com.cap.admin.catalogo.application.video.delete.DefaultDeleteVideoUseCase;
import com.cap.admin.catalogo.application.video.delete.DeleteVideoUseCase;
import com.cap.admin.catalogo.application.video.media.get.DefaultGetMediaUseCase;
import com.cap.admin.catalogo.application.video.media.get.GetMediaUseCase;
import com.cap.admin.catalogo.application.video.media.update.DefaultUpdateMediaStatusUseCase;
import com.cap.admin.catalogo.application.video.media.update.UpdateMediaStatusUseCase;
import com.cap.admin.catalogo.application.video.media.upload.DefaultUploadMediaUseCase;
import com.cap.admin.catalogo.application.video.media.upload.UploadMediaUseCase;
import com.cap.admin.catalogo.application.video.retrieve.get.DefaultGetVideoByIdUseCase;
import com.cap.admin.catalogo.application.video.retrieve.get.GetVideoByIdUseCase;
import com.cap.admin.catalogo.application.video.retrieve.list.DefaultListVideosUseCase;
import com.cap.admin.catalogo.application.video.retrieve.list.ListVideosUseCase;
import com.cap.admin.catalogo.application.video.update.DefaultUpdateVideoUseCase;
import com.cap.admin.catalogo.application.video.update.UpdateVideoUseCase;
import com.cap.admin.catalogo.domain.castmember.CastMemberGateway;
import com.cap.admin.catalogo.domain.category.CategoryGateway;
import com.cap.admin.catalogo.domain.genre.GenreGateway;
import com.cap.admin.catalogo.domain.video.MediaResourceGateway;
import com.cap.admin.catalogo.domain.video.VideoGateway;

@Configuration
public class VideoUseCaseConfig {

    private final CategoryGateway categoryGateway;
    private final CastMemberGateway castMemberGateway;
    private final GenreGateway genreGateway;
    private final MediaResourceGateway mediaResourceGateway;
    private final VideoGateway videoGateway;

    public VideoUseCaseConfig(
            final CategoryGateway categoryGateway,
            final CastMemberGateway castMemberGateway,
            final GenreGateway genreGateway,
            final MediaResourceGateway mediaResourceGateway,
            final VideoGateway videoGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
        this.castMemberGateway = Objects.requireNonNull(castMemberGateway);
        this.genreGateway = Objects.requireNonNull(genreGateway);
        this.mediaResourceGateway = Objects.requireNonNull(mediaResourceGateway);
        this.videoGateway = Objects.requireNonNull(videoGateway);
    }

    @Bean
    public CreateVideoUseCase createVideoUseCase() {
        return new DefaultCreateVideoUseCase(categoryGateway, castMemberGateway, genreGateway, mediaResourceGateway,
                videoGateway);
    }

    @Bean
    public UpdateVideoUseCase updateVideoUseCase() {
        return new DefaultUpdateVideoUseCase(videoGateway, categoryGateway, castMemberGateway, genreGateway,
                mediaResourceGateway);
    }

    @Bean
    public GetVideoByIdUseCase getVideoByIdUseCase() {
        return new DefaultGetVideoByIdUseCase(videoGateway);
    }

    @Bean
    public DeleteVideoUseCase deleteVideoUseCase() {
        return new DefaultDeleteVideoUseCase(videoGateway, mediaResourceGateway);
    }

    @Bean
    public ListVideosUseCase listVideosUseCase() {
        return new DefaultListVideosUseCase(videoGateway);
    }

    @Bean
    public GetMediaUseCase getMediaUseCase() {
        return new DefaultGetMediaUseCase(mediaResourceGateway);
    }

    @Bean
    public UploadMediaUseCase uploadMediaUseCase() {
        return new DefaultUploadMediaUseCase(mediaResourceGateway, videoGateway);
    }

    @Bean
    public UpdateMediaStatusUseCase updateMediaStatusUseCase() {
        return new DefaultUpdateMediaStatusUseCase(videoGateway);
    }
}
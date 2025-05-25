package com.cap.admin.catalogo.application.video.retrieve.list;

import com.cap.admin.catalogo.application.UseCase;
import com.cap.admin.catalogo.domain.pagination.Pagination;
import com.cap.admin.catalogo.domain.video.VideoSearchQuery;

public abstract class ListVideosUseCase
                extends UseCase<VideoSearchQuery, Pagination<VideoListOutput>> {
}

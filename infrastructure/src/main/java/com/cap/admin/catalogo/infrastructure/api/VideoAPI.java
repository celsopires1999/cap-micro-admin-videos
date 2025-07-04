package com.cap.admin.catalogo.infrastructure.api;

import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping(value = "videos")
@Tag(name = "Video")
public interface VideoAPI {

}
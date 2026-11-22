package com.guicheng.student_api.dto;

import java.util.Map;

public record ValidationErrorResponse(int status, Map<String, String> errors) {}

package com.ifba.educampo.service;

import java.util.Map;

public interface TemplateService {
    String getProcessedTemplate(String templateId, Map<String, Object> templateVariables);
}
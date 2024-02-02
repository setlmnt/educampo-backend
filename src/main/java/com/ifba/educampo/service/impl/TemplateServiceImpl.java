package com.ifba.educampo.service.impl;

import com.ifba.educampo.entity.Template;
import com.ifba.educampo.enums.ErrorsEnum;
import com.ifba.educampo.exception.ApiException;
import com.ifba.educampo.repository.EmailTemplateRepository;
import com.ifba.educampo.service.TemplateService;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;
import java.util.Optional;

@Service
@Log4j2
public class TemplateServiceImpl implements TemplateService {
    public static final String CONFIGURATION_VERSION = "2.3.31";

    @Autowired
    private EmailTemplateRepository emailTemplateRepository;

    @Override
    public String getProcessedTemplate(String templateId, Map<String, Object> templateVariables) {
        Template template = getEmailTemplate(templateId);

        try {
            return processTemplate(templateVariables, template).toString();
        } catch (Exception e) {
            log.error("Error while processing template {}", templateId, e);
            throw new ApiException(ErrorsEnum.EMAIL_TEMPLATE_PROCESSING_ERROR);
        }
    }

    private StringWriter processTemplate(Map<String, Object> templateVariables, Template emailTemplate) throws IOException, TemplateException {
        Configuration cfg = new Configuration(new Version(CONFIGURATION_VERSION));
        StringReader reader = new StringReader(emailTemplate.getBody());
        freemarker.template.Template template = new freemarker.template.Template(emailTemplate.getName(), reader, cfg);
        StringWriter writer = new StringWriter();
        template.process(templateVariables, writer);
        return writer;
    }

    private Template getEmailTemplate(String templateName) {
        Optional<Template> emailTemplate = emailTemplateRepository.findByName(templateName);
        if (emailTemplate.isEmpty()) {
            log.error("Email template {} not found", templateName);
            throw new ApiException(ErrorsEnum.EMAIL_TEMPLATE_NOT_FOUND);
        }
        return emailTemplate.get();
    }
}
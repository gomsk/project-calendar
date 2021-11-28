package com.gomsk.project.api.service;

import com.gomsk.project.core.domain.entity.Engagement;

public interface EmailService {
    void sendEngagement(Engagement engagement);
}

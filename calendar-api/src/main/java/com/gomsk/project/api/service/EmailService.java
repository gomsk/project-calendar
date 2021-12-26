package com.gomsk.project.api.service;

import com.gomsk.project.api.dto.EngagementEmailStuff;
import com.gomsk.project.core.domain.entity.Engagement;

public interface EmailService {
    void sendEngagement(EngagementEmailStuff stuff);
}

package com.gomsk.project.api.service;

import com.gomsk.project.api.dto.EngagementEmailStuff;
import com.gomsk.project.core.domain.entity.Engagement;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("test")
@Service
public class FakeEmailService implements EmailService {
    @Override
    public void sendEngagement(EngagementEmailStuff stuff) {
        System.out.println("send email. email:" + stuff.getSubject());
    }
}

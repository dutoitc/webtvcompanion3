package ch.mno.wc3.services.instagram.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "instagram")
@Getter
@Builder
@AllArgsConstructor // Needed by Builder
@NoArgsConstructor // Needed by ConfigurationProperties
public class InstagramConfig {

    private String instagramId;
    private String accessToken;

}
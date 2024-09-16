package ch.mno.wc3.services.youtube.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "youtube")
@Getter
@Builder
@AllArgsConstructor // Needed by Builder
@NoArgsConstructor // Needed by ConfigurationProperties
public class YoutubeConfig {

    private String chanelId;
    private String applicationName;
    private String clientSecretsFile;

}
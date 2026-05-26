package co.edu.uco.ucoparking.initializer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Carga infisical-secrets.env antes de crear beans (SMTP, DB, etc.).
 * Funciona con IntelliJ, mvnw y run-dev.ps1 sin depender solo de variables de entorno del SO.
 */
public class InfisicalSecretsEnvironmentPostProcessor implements EnvironmentPostProcessor {

    private static final String PROPERTY_SOURCE_NAME = "infisicalSecrets";
    private static final List<String> CANDIDATE_PATHS = List.of(
            "infisical-secrets.env",
            "uco-parking/infisical-secrets.env"
    );

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        for (String candidate : CANDIDATE_PATHS) {
            Path path = Path.of(candidate).toAbsolutePath().normalize();
            if (!Files.isRegularFile(path)) {
                continue;
            }

            Map<String, Object> properties = loadProperties(path);
            if (properties.isEmpty()) {
                continue;
            }

            environment.getPropertySources().addFirst(
                    new MapPropertySource(PROPERTY_SOURCE_NAME, properties)
            );
            return;
        }
    }

    private Map<String, Object> loadProperties(Path path) {
        Map<String, Object> values = new LinkedHashMap<>();
        Resource resource = new FileSystemResource(path.toFile());

        try {
            Properties properties = new Properties();
            properties.load(resource.getInputStream());
            properties.forEach((key, value) -> {
                if (key != null && value != null) {
                    values.put(String.valueOf(key), String.valueOf(value));
                }
            });
        } catch (IOException ignored) {
            // Si falla la lectura, se usan defaults/application.properties.
        }

        return values;
    }
}

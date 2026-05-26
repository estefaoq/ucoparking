package co.edu.uco.ucoparking.initializer;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Properties;

/**
 * Carga infisical-secrets.env antes del arranque de Spring (IntelliJ, mvnw, etc.).
 */
public final class LocalSecretsLoader {

    private static final List<String> CANDIDATE_PATHS = List.of(
            "infisical-secrets.env",
            "uco-parking/infisical-secrets.env"
    );

    private LocalSecretsLoader() {
    }

    public static void loadIfPresent() {
        for (String candidate : CANDIDATE_PATHS) {
            Path path = Path.of(candidate).toAbsolutePath().normalize();
            if (!Files.isRegularFile(path)) {
                continue;
            }

            try (var reader = new InputStreamReader(Files.newInputStream(path), StandardCharsets.UTF_8)) {
                Properties properties = new Properties();
                properties.load(reader);

                properties.forEach((key, value) -> {
                    if (key == null || value == null) {
                        return;
                    }
                    String name = String.valueOf(key).trim();
                    String propertyValue = String.valueOf(value).trim();
                    if (name.isEmpty()) {
                        return;
                    }
                    if (System.getenv(name) == null) {
                        System.setProperty(name, propertyValue);
                    }
                });

                System.out.println("[UCO Parking] Secretos locales cargados: " + path);
                return;
            } catch (IOException exception) {
                System.err.println("[UCO Parking] No se pudo leer " + path + ": " + exception.getMessage());
            }
        }
    }
}

package pl.wydrzynski.arester;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Log4j2
@RestController
public class Endpoints {

    private String mockFilesLocation;

    public Endpoints() {
        this.mockFilesLocation = AresterApplication.getMockFilesLocation();
    }

    @GetMapping(path = {"/", "/*", "/*/*"})
    public String get() {
        return readResponseFromFile("GET.json");
    }

    @PostMapping(path = {"/", "/*", "/*/*"})
    public String post(String body) {
        return readResponseFromFile("POST.json");
    }

    private String readResponseFromFile(String filename) {
        Path responsePath = Paths.get(mockFilesLocation).resolve(filename);
        String response = String.format("You can create %s file to get any response you like!", responsePath);
        try {
            response = Files.readString(responsePath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            log.error("Failed to read file {}. Cause: {}", responsePath, e.getMessage());
        }
        return response;
    }
}

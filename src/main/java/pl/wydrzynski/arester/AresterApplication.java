package pl.wydrzynski.arester;

import lombok.Getter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AresterApplication {

    @Getter
    private static String mockFilesLocation;

    public static void main(String[] args) {
        if (args.length > 0) {
            mockFilesLocation = args[0];
        } else {
            mockFilesLocation = System.getProperty("user.dir");
        }

        System.out.println("Mock files location = " + mockFilesLocation);

        SpringApplication.run(AresterApplication.class, args);
    }
}

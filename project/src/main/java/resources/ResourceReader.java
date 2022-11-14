package resources;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

public class ResourceReader {
    public String readTextFile(String name){
        URL resource = this.getClass().getClassLoader().getResource(name);

        if (resource == null){
            throw new FailedToReadResource("Resource file not found: " + name);
        }

        try {
            return IOUtils.toString(resource, Charset.defaultCharset());
        } catch (IOException e) {
            throw new FailedToReadResource("Failed to read: " + name, e);
        }
    }
}

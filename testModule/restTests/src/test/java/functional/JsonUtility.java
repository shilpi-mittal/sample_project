package functional;

import org.apache.commons.io.FileUtils;
import utils.HttpClient;
import utils.ResponseEntity;
import utils.TestConstants;

import java.io.File;
import java.io.IOException;

import static utils.TestWebDriver.loadPropertiesFile;

public class JsonUtility {

    public static String getJsonStringFor(String fullJsonTxtFileName) throws IOException {
        File file = new File(JsonUtility.class.getClassLoader().getResource(fullJsonTxtFileName).getFile());
        return FileUtils.readFileToString(file);
    }

    public ResponseEntity sendRequest(String fileName, String url, String commMethod, boolean headersRequired) throws IOException {
        loadPropertiesFile("config.txt");
        HttpClient client = new HttpClient();
        client.createContext();

        return client.SendJSON(
                getJsonStringFor(fileName),
                url,
                commMethod,
                TestConstants.USER_NAME,
                TestConstants.PASSWORD,
                headersRequired);

    }

    public ResponseEntity postRequest(String json, String url, boolean headersRequired) throws IOException {
        loadPropertiesFile("config.txt");
        HttpClient client = new HttpClient();
        client.createContext();

        return client.SendJSON(
                json,
                url,
                "POST",
                TestConstants.USER_NAME,
                TestConstants.PASSWORD,
                headersRequired);

    }

    public ResponseEntity getResponse(String url, boolean headersRequired) throws IOException {
        loadPropertiesFile("config.txt");
        HttpClient client = new HttpClient();
        client.createContext();

        return client.SendJSON(
                "",
                url,
                "GET",
                TestConstants.USER_NAME,
                TestConstants.PASSWORD,
                headersRequired);

    }

    public String urlBuilder(String url, String ... queryParam) {
        StringBuilder newUrl = new StringBuilder(url+"?q=");
        for(String param : queryParam) {
            newUrl.append(param);
        }
        return newUrl.toString();
    }
}

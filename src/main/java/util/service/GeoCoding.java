package util.service;

import java.util.HashMap;

public interface GeoCoding {
    public String getKakaoApiFromAddress(String address);

    public HashMap<String, String> getXYMapfromJson(String jsonString);
}

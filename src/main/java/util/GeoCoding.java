package util;

import java.util.HashMap;

public interface GeoCoding {
    HashMap<String, String> getXYMapfromJson(String jsonString);

    String getKakaoApiFromAddress(String jibunAddress);
}

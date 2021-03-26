package uz.pdp.online.appwerhouseprojectm5l11.payload;

import java.util.UUID;

public class GenerateCode {

    public static String setCode() {
        String code = UUID.randomUUID().toString();
        return code;
    }


}

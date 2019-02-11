package my.spotit.asset.maintenance.api.vo;

import my.spotit.asset.core.api.Record;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class MaintenanceRequestRecord extends Record {

    private MaintenanceRequest request;

    public MaintenanceRequest getRequest() {
        return request;
    }

    public void setRequest(MaintenanceRequest request) {
        this.request = request;
    }

    @JsonCreator
    public static MaintenanceRequestRecord create(String jsonString) {
        MaintenanceRequestRecord o = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            o = mapper.readValue(jsonString, MaintenanceRequestRecord.class);
        } catch (IOException e) {
            // handle
        }
        return o;
    }

}

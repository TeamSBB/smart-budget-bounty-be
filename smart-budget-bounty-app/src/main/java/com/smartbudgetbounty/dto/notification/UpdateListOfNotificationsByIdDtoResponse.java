package com.smartbudgetbounty.dto.notification;

import java.util.ArrayList;
import java.util.List;

public class UpdateListOfNotificationsByIdDtoResponse {
    private List<UpdateNotificationDataDto> data = new ArrayList<>();

    public UpdateListOfNotificationsByIdDtoResponse(List<UpdateNotificationDataDto> data) {
        this.data = data;
    }

    public List<UpdateNotificationDataDto> getData() {
        return data;
    }

    public void setData(List<UpdateNotificationDataDto> data) {
        this.data = data;
    }
}

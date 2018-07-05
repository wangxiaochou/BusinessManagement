package com.xzty.cq.tover.businessmanagement.common.eventbus;

import java.util.List;

/**
 * @author yq
 * @param <T>
 * @explan EventBus数据传输类,处理activity间的大数据传输
 */
public class EventData<T> {
    private List<T> eventData;

    public EventData(List<T> eventData) {
        this.eventData = eventData;
    }

    public List<T> getEventData() {
        return eventData;
    }

    public void setEventData(List<T> eventData) {
        this.eventData = eventData;
    }


}

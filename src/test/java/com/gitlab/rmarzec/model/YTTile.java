package com.gitlab.rmarzec.model;

import lombok.Builder;

@Builder
public class YTTile {
    private String title;
    private String channel;
    private String length;

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    @Override
    public String toString() {

        if (length != "LIVE") {
            return "YTTile{" +
                    "title='" + title + '\'' +
                    ", length='" + length + '\'' +
                    '}';
        }
        return null;
    }
}

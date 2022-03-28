package com.dongnaoedu.dnplayer.common.constant;

public enum SimplePage {

    USER_INFO("用户信息", RoutePath.USER_INFO_FRAGMENT);

    private String title;
    private String path;

    SimplePage(String title, String path) {
        this.title = title;
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

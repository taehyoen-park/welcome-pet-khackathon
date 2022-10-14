package org.taehyeon.welcome_pet_khackathon.Community;

public class WriteInfo {
    private String title;
    private String Contents;
    private String Publisher;

    public WriteInfo(String title, String Contents, String Publisher) {
        this.title = title;
        this.Contents = Contents;
        this.Publisher = Publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return Contents;
    }

    public void setContents(String contents) {
        Contents = contents;
    }

    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String publisher) {
        Publisher = publisher;
    }
}

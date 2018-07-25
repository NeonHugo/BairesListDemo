package br.com.nmsystems.baireslistdemo.model;

public class Card {

    /**
     * Card info specified by the webService
     */
    private String topLabel;
    private String middleLabel;
    private String bottomLabel;
    private int eventCount;
    private String image;
    private int targetId;
    private String targetType;
    private int entityId;
    private String entityType;
    private long startDate;
    private int rank;

    public Card() {
        this.topLabel = "";
        this.middleLabel = "";
        this.bottomLabel = "";
        this.eventCount = -1;
        this.image = "";
        this.targetId = -1;
        this.targetType = "";
        this.entityId = -1;
        this.entityType = "";
        this.startDate = -1;
        this.rank = -1;
    }

    public String getTopLabel() {
        return topLabel;
    }

    public void setTopLabel(String topLabel) {
        this.topLabel = topLabel;
    }

    public String getMiddleLabel() {
        return middleLabel;
    }

    public void setMiddleLabel(String middleLabel) {
        this.middleLabel = middleLabel;
    }

    public String getBottomLabel() {
        return bottomLabel;
    }

    public void setBottomLabel(String bottomLabel) {
        this.bottomLabel = bottomLabel;
    }

    public int getEventCount() {
        return eventCount;
    }

    public void setEventCount(int eventCount) {
        this.eventCount = eventCount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getTargetId() {
        return targetId;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}

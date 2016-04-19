package com.example.doukaili.baiduyun.Data;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Dou.Kaili on 2016/4/16.
 */
public class UserInfo {

    public static final String
            USER_ID = "id",
            USER_NAME = "name",
            USER_USERNAME = "username",
            USER_HTML_URL = "html_url",
            USER_AVATAR_URL = "avatar_url",

    USER_BIO = "bio",
            USER_LOCATION = "location",
            USER_LINKS = "links",
            USER_LINKS_KEYS = "links.keys",
            USER_LINKS_VALUES = "links.values",
            USER_BUCKETS_COUNT = "buckets_count",
            USER_COMMENTS_RECEIVED_COUNT = "comments_received_count",

    USER_FOLLOWERS_COUNT = "followers_count",
            USER_FOLLOWINGS_COUNT = "followings_count",
            USER_LIKES_COUNT = "likes_count",
            USER_LIKES_RECEIVED_COUNT = "likes_received_count",
            USER_PROJECTS_COUNT = "projects_count",

    USER_REBOUNDS_RECEIVED_COUNT = "rebounds_received_count",
            USER_SHOTS_COUNT = "shots_count",
            USER_TEAMS_COUNT = "teams_count",
            USER_CAN_UPLOAD_SHOT = "can_upload_shot",
            USER_TYPE = "type",

    USER_PRO = "pro",
            USER_BUCKETS_URL = "buckets_url",
            USER_FOLLOWERS_URL = "followers_url",
            USER_FOLLOWINGS_URL = "following_url",
            USER_LIKES_URL = "likes_url",

    USER_PROJECTS_URL = "projects_url",
            USER_SHOTS_URL = "shots_url",
            USER_TEAMS_URL = "teams_url",
            USER_CREATED_AT = "created_at",
            USER_UPDATED_AT = "updated_at";

    private String userId;
    private String userName;
    private String htmlUrl;
    private String avatarUrl;
    private String bid;
    private String location;

    private Map<String,String> links;

    private int bucketsCount;
    private int comRecCount;
    private int flowersCount;
    private int flowingsCount;
    private int likesCount;
    private int likesRecCount;
    private int projectsCount;
    private int reboundsRecCount;
    private int shotCount;
    private int teamsCount;
    private boolean canUploadCount;
    private String type;
    private boolean pro;
    private String bucketsUrl;
    private String followersUrl;
    private String followingUrl;
    private String projectsUrl;
    private String likesUrl;
    private String shotsUrl;
    private String teamsUrl;
    private Calendar createdAt;
    private Calendar updatedAt;

    private static UserInfo userInfoInstance;
    public UserInfo(JSONObject json) {
        try{
            //jsonObject
            if(json.has(USER_ID)) userId = json.getString(USER_ID);
            if (json.has(USER_NAME)) userName = json.getString(USER_NAME);
            //if (json.has(USER_USERNAME)) username = json.getString(USER_USERNAME);
            if (json.has(USER_HTML_URL)) htmlUrl = json.getString(USER_HTML_URL);
            if (json.has(USER_AVATAR_URL)) avatarUrl = json.getString(USER_AVATAR_URL);
            if (json.has(USER_BIO)) bid = json.getString(USER_BIO);
            if (json.has(USER_LOCATION)) location = json.getString(USER_LOCATION);
            //jsonArray
            links = new HashMap<>();
            if(json.has(USER_LINKS)) {
                JSONObject linkJson = (JSONObject) json.get(USER_LINKS);
                Iterator iterator = linkJson.keys();
                while(iterator.hasNext()) {
                    String key = (String) iterator.next();
                    String val = (String)linkJson.get(key);
                    links.put(key,val);
                }
            }
            if (json.has(USER_BUCKETS_COUNT)) bucketsCount= json.getInt(USER_BUCKETS_COUNT);
            if (json.has(USER_COMMENTS_RECEIVED_COUNT)) comRecCount = json.getInt(USER_COMMENTS_RECEIVED_COUNT);
            if (json.has(USER_FOLLOWERS_COUNT)) flowersCount = json.getInt(USER_FOLLOWERS_COUNT);
            if (json.has(USER_FOLLOWINGS_COUNT)) flowingsCount = json.getInt(USER_FOLLOWINGS_COUNT);
            if (json.has(USER_LIKES_COUNT)) likesCount = json.getInt(USER_LIKES_COUNT);
            if (json.has(USER_LIKES_RECEIVED_COUNT)) likesRecCount = json.getInt(USER_LIKES_RECEIVED_COUNT);
            if (json.has(USER_PROJECTS_COUNT)) projectsCount = json.getInt(USER_PROJECTS_COUNT);
            if (json.has(USER_REBOUNDS_RECEIVED_COUNT)) reboundsRecCount = json.getInt(USER_REBOUNDS_RECEIVED_COUNT);
            if (json.has(USER_SHOTS_COUNT)) shotCount = json.getInt(USER_SHOTS_COUNT);
            if (json.has(USER_TEAMS_COUNT)) teamsCount = json.getInt(USER_TEAMS_COUNT);
            if (json.has(USER_CAN_UPLOAD_SHOT)) canUploadCount = json.getBoolean(USER_CAN_UPLOAD_SHOT);
            if (json.has(USER_TYPE)) type = json.getString(USER_TYPE);
            if (json.has(USER_PRO)) pro = json.getBoolean(USER_PRO);
            if (json.has(USER_BUCKETS_URL)) bucketsUrl = json.getString(USER_BUCKETS_URL);
            if (json.has(USER_FOLLOWERS_URL)) followersUrl = json.getString(USER_FOLLOWERS_URL);
            if (json.has(USER_FOLLOWINGS_URL)) followingUrl = json.getString(USER_FOLLOWINGS_URL);
            if (json.has(USER_LIKES_URL)) likesUrl = json.getString(USER_LIKES_URL);
            if (json.has(USER_PROJECTS_URL)) projectsUrl = json.getString(USER_PROJECTS_URL);
            if (json.has(USER_SHOTS_URL)) shotsUrl = json.getString(USER_SHOTS_URL);
            if (json.has(USER_TEAMS_URL)) teamsUrl = json.getString(USER_TEAMS_URL);

            if (json.has(USER_CREATED_AT)) {
                createdAt = Calendar.getInstance();
                Date create_date = DribbbleInfo.SIMPLE_DATE_FORMAT.parse(json.getString(USER_CREATED_AT));
                createdAt.setTime(create_date);
            }
            if (json.has(USER_UPDATED_AT)) {
                updatedAt = Calendar.getInstance();
                Date updated_date = DribbbleInfo.SIMPLE_DATE_FORMAT.parse(json.getString(USER_UPDATED_AT));
                updatedAt.setTime(updated_date);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public static UserInfo getUserInfoInstance(JSONObject json) {
        if(userInfoInstance == null) {
            userInfoInstance = new UserInfo(json);
        }
        return userInfoInstance;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Map<String, String> getLinks() {
        return links;
    }

    public void setLinks(Map<String, String> links) {
        this.links = links;
    }

    public int getBucketsCount() {
        return bucketsCount;
    }

    public void setBucketsCount(int bucketsCount) {
        this.bucketsCount = bucketsCount;
    }

    public int getComRecCount() {
        return comRecCount;
    }

    public void setComRecCount(int comRecCount) {
        this.comRecCount = comRecCount;
    }

    public int getFlowersCount() {
        return flowersCount;
    }

    public void setFlowersCount(int flowersCount) {
        this.flowersCount = flowersCount;
    }

    public int getFlowingsCount() {
        return flowingsCount;
    }

    public void setFlowingsCount(int flowingsCount) {
        this.flowingsCount = flowingsCount;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public int getLikesRecCount() {
        return likesRecCount;
    }

    public void setLikesRecCount(int likesRecCount) {
        this.likesRecCount = likesRecCount;
    }

    public int getProjectsCount() {
        return projectsCount;
    }

    public void setProjectsCount(int projectsCount) {
        this.projectsCount = projectsCount;
    }

    public int getReboundsRecCount() {
        return reboundsRecCount;
    }

    public void setReboundsRecCount(int reboundsRecCount) {
        this.reboundsRecCount = reboundsRecCount;
    }

    public int getShotCount() {
        return shotCount;
    }

    public void setShotCount(int shotCount) {
        this.shotCount = shotCount;
    }

    public int getTeamsCount() {
        return teamsCount;
    }

    public void setTeamsCount(int teamsCount) {
        this.teamsCount = teamsCount;
    }

    public boolean isCanUploadCount() {
        return canUploadCount;
    }

    public void setCanUploadCount(boolean canUploadCount) {
        this.canUploadCount = canUploadCount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isPro() {
        return pro;
    }

    public void setPro(boolean pro) {
        this.pro = pro;
    }

    public String getBucketsUrl() {
        return bucketsUrl;
    }

    public void setBucketsUrl(String bucketsUrl) {
        this.bucketsUrl = bucketsUrl;
    }

    public String getFollowersUrl() {
        return followersUrl;
    }

    public void setFollowersUrl(String followersUrl) {
        this.followersUrl = followersUrl;
    }

    public String getFollowingUrl() {
        return followingUrl;
    }

    public void setFollowingUrl(String followingUrl) {
        this.followingUrl = followingUrl;
    }

    public String getProjectsUrl() {
        return projectsUrl;
    }

    public void setProjectsUrl(String projectsUrl) {
        this.projectsUrl = projectsUrl;
    }

    public String getLikesUrl() {
        return likesUrl;
    }

    public void setLikesUrl(String likesUrl) {
        this.likesUrl = likesUrl;
    }

    public String getShotsUrl() {
        return shotsUrl;
    }

    public void setShotsUrl(String shotsUrl) {
        this.shotsUrl = shotsUrl;
    }

    public String getTeamsUrl() {
        return teamsUrl;
    }

    public void setTeamsUrl(String teamsUrl) {
        this.teamsUrl = teamsUrl;
    }

    public Calendar getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Calendar createdAt) {
        this.createdAt = createdAt;
    }

    public Calendar getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Calendar updatedAt) {
        this.updatedAt = updatedAt;
    }
}

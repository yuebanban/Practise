package com.example.doukaili.baiduyun.Data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Dou.Kaili on 2016/4/18.
 */
public class Shot {
    public static final String
            SHOT_ID = "id",
            SHOT_TITLE = "title",
            SHOT_DESCRIPTION = "description",
            SHOT_WIDTH = "width",
            SHOT_HEIGHT = "height",
            SHOT_IMAGES = "images",
            SHOT_IMAGES_HIDPI = "hidpi",
            SHOT_IMAGES_NORMAL = "normal",
            SHOT_IMAGES_TEASER = "teaser",
            SHOT_VIEWS_COUNT = "views_count",
            SHOT_LIKES_COUNT = "likes_count",
            SHOT_COMMENTS_COUNT = "comments_count",
            SHOT_ATTACHMENTS_COUNT = "attachments_count",
            SHOT_REBOUNDS_COUNT = "rebounds_count",
            SHOT_BUCKETS_COUNT = "buckets_count",
            SHOT_CREATED_AT = "created_at",
            SHOT_UPDATED_AT = "updated_at",
            SHOT_HTML_URL = "html_url",
            SHOT_ATTACHMENTS_URL = "attachments_url",
            SHOT_BUCKETS_URL = "buckets_url",
            SHOT_COMMENTS_URL = "comments_url",
            SHOT_LIKES_URL = "likes_url",
            SHOT_PROJECTS_URL = "projects_url",
            SHOT_REBOUNDS_URL = "rebounds_url",
            SHOT_TAGS = "tags",
            SHOT_USER = "user",
            SHOT_TEAM = "team";
    private String id;
    private String title;
    private String description;
    private int width;
    private int height;
    /**
     * There are three images:
     * 1. hidpi aka large
     * 2. normal aka middle
     * 3. teaser aka small
     */
    private String[] images = new String[3];
    private int views_count;
    private int likes_count;
    private int comments_count;
    private int attachments_count;
    private int rebounds_count;
    private int buckets_count;
    private Calendar created_at;
    private Calendar updated_at;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public int getViews_count() {
        return views_count;
    }

    public void setViews_count(int views_count) {
        this.views_count = views_count;
    }

    public int getLikes_count() {
        return likes_count;
    }

    public void setLikes_count(int likes_count) {
        this.likes_count = likes_count;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public int getAttachments_count() {
        return attachments_count;
    }

    public void setAttachments_count(int attachments_count) {
        this.attachments_count = attachments_count;
    }

    public int getRebounds_count() {
        return rebounds_count;
    }

    public void setRebounds_count(int rebounds_count) {
        this.rebounds_count = rebounds_count;
    }

    public int getBuckets_count() {
        return buckets_count;
    }

    public void setBuckets_count(int buckets_count) {
        this.buckets_count = buckets_count;
    }

    public Calendar getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Calendar created_at) {
        this.created_at = created_at;
    }

    public Calendar getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Calendar updated_at) {
        this.updated_at = updated_at;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getAttachments_url() {
        return attachments_url;
    }

    public void setAttachments_url(String attachments_url) {
        this.attachments_url = attachments_url;
    }

    public String getBuckets_url() {
        return buckets_url;
    }

    public void setBuckets_url(String buckets_url) {
        this.buckets_url = buckets_url;
    }

    public String getComments_url() {
        return comments_url;
    }

    public void setComments_url(String comments_url) {
        this.comments_url = comments_url;
    }

    public String getLikes_url() {
        return likes_url;
    }

    public void setLikes_url(String likes_url) {
        this.likes_url = likes_url;
    }

    public String getProjects_url() {
        return projects_url;
    }

    public void setProjects_url(String projects_url) {
        this.projects_url = projects_url;
    }

    public String getRebounds_url() {
        return rebounds_url;
    }

    public void setRebounds_url(String rebounds_url) {
        this.rebounds_url = rebounds_url;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    private String html_url;
    private String attachments_url;
    private String buckets_url;
    private String comments_url;
    private String likes_url;
    private String projects_url;
    private String rebounds_url;
    private ArrayList<String> tags;
    private UserInfo user;
    private String team;

    public Shot(JSONObject object) {
        try {
            id = object.getString(SHOT_ID);
            title = object.getString(SHOT_TITLE);
            description = object.getString(SHOT_DESCRIPTION);
            width = object.getInt(SHOT_WIDTH);
            height = object.getInt(SHOT_HEIGHT);
            JSONObject imageJson = (JSONObject) object.get(SHOT_IMAGES);
            images[0] = imageJson.getString(SHOT_IMAGES_HIDPI);
            images[1] = imageJson.getString(SHOT_IMAGES_NORMAL);
            images[2] = imageJson.getString(SHOT_IMAGES_TEASER);
            for (int i=0; i<3; i++) {
                if (images[i].equals("null")) {
                    images[i] = null;
                }
            }
            views_count = object.getInt(SHOT_VIEWS_COUNT);
            likes_count = object.getInt(SHOT_LIKES_COUNT);
            comments_count = object.getInt(SHOT_COMMENTS_COUNT);
            attachments_count = object.getInt(SHOT_ATTACHMENTS_COUNT);
            rebounds_count = object.getInt(SHOT_REBOUNDS_COUNT);
            buckets_count = object.getInt(SHOT_BUCKETS_COUNT);

            created_at = Calendar.getInstance();
            String created_str = object.getString(SHOT_CREATED_AT);
            Date created_date = DribbbleInfo.SIMPLE_DATE_FORMAT.parse(created_str);
            created_at.setTime(created_date);

            updated_at = Calendar.getInstance();
            String updated_str = object.getString(SHOT_UPDATED_AT);
            Date updated_date = DribbbleInfo.SIMPLE_DATE_FORMAT.parse(updated_str);
            updated_at.setTime(updated_date);

            html_url = object.getString(SHOT_HTML_URL);
            attachments_url = object.getString(SHOT_ATTACHMENTS_URL);
            buckets_url = object.getString(SHOT_BUCKETS_URL);
            comments_url = object.getString(SHOT_COMMENTS_URL);
            likes_url = object.getString(SHOT_LIKES_URL);
            projects_url = object.getString(SHOT_PROJECTS_URL);
            rebounds_url = object.getString(SHOT_REBOUNDS_URL);
            JSONArray jsonArray = object.getJSONArray(SHOT_TAGS);
            tags = new ArrayList<>();
            for (int i=0; i<jsonArray.length(); i++) {
                tags.add(jsonArray.getString(i));
            }
            if (object.has(SHOT_USER)) {
                JSONObject userJson = object.getJSONObject(SHOT_USER);
                user = new UserInfo(userJson);
            }
            team = object.getString(SHOT_TEAM);


        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}

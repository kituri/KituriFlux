package com.flux.kituri.app.data;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.Table;

/**
 * Created by kirijin on 2016/4/12.
 */
@Table("meizhis")
public class Meizhi extends Entry {

        @Column("createdAt") private String createdAt;

        @Column("desc") private String desc;

        @Column("publishedAt") private String publishedAt;

        @Column("source") private String source;

        @Column("type") private String type;

        @Column("url") private String url;

        @Column("used") private boolean used;

        @Column("who") private String who;

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getCreatedAt() {
            return this.createdAt;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return this.desc;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getPublishedAt() {
            return this.publishedAt;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getSource() {
            return this.source;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getType() {
            return this.type;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrl() {
            return this.url;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public boolean getUsed() {
            return this.used;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public String getWho() {
            return this.who;
        }


}

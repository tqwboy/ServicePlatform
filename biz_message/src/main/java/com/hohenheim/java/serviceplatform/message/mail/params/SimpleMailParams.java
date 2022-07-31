package com.hohenheim.java.serviceplatform.message.mail.params;

import lombok.Data;

/**
 * @author Hohenheim
 * @date 2022/7/3
 * @description 邮件简单参数
 */
@Data
public class SimpleMailParams {
    /** 发件人邮箱 */
    private String from;

    /** 收件人邮箱 */
    private String[] to;

    /** 信件回复的收件人. 用户直接回复邮件时, reply-to 就是默认的收件人. 如果用户不指定它, from 就是默认的收件人. */
    public String replayTo;

    /** 抄送 */
    private String[] cc;

    /** 密送 */
    private String[] bcc;

    private String title;

    private String content;

    private SimpleMailParams(Builder builder) {
        setFrom(builder.from);
        setTo(builder.to);
        setReplayTo(builder.replayTo);
        setCc(builder.cc);
        setBcc(builder.bcc);
        setTitle(builder.title);
        setContent(builder.content);
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private String from;
        private String[] to;
        private String replayTo;
        private String[] cc;
        private String[] bcc;
        private String title;
        private String content;

        private Builder() {
        }

        public Builder from(String from) {
            this.from = from;
            return this;
        }

        public Builder to(String... to) {
            this.to = to;
            return this;
        }

        public Builder replayTo(String replayTo) {
            this.replayTo = replayTo;
            return this;
        }

        public Builder cc(String... cc) {
            this.cc = cc;
            return this;
        }

        public Builder bcc(String... bcc) {
            this.bcc = bcc;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public SimpleMailParams build() {
            return new SimpleMailParams(this);
        }
    }
}
package com.kdyzm.gentleman.intelligentcontroller.model;

import java.util.Arrays;

public class Post {
	private Long id;
	private String tags;
	private String[] tagsArr;
	private Long created_at;
	private Long creator_id;
	private String author;
	private Long change;
	private String source;
	private Long score;
	private String md5;
	private Long file_size;
	private String file_url;
	private Boolean is_shown_in_index;
	private String preview_url;
	private Long preview_width;
	private Long preview_height;
	private Long actual_preview_width;
	private Long actual_preview_height;
	private String sample_url;
	private Long sample_width;
	private Long sample_height;
	private Long sample_file_size;
	private String jpeg_url;
	private Long jpeg_width;
	private Long jpeg_height;
	private Long jpeg_file_size;
	private String rating;
	private Boolean has_children;
	private Long parent_id;
	private String status;
	private Long width;
	private Long height;
	private Boolean is_held;
	private String frames_pending_string;
	private String[] frames_pending;
	private String frames_string;
	private String[] frames;
	private String flag_detail;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
		this.setTagsArr(tags);
	}

	public String[] getTagsArr() {
		return tagsArr;
	}

	public void setTagsArr(String tags) {
		this.tagsArr = tags.split("\b+");
	}

	public Long getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Long created_at) {
		this.created_at = created_at;
	}

	public Long getCreator_id() {
		return creator_id;
	}

	public void setCreator_id(Long creator_id) {
		this.creator_id = creator_id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Long getChange() {
		return change;
	}

	public void setChange(Long change) {
		this.change = change;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Long getScore() {
		return score;
	}

	public void setScore(Long score) {
		this.score = score;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public Long getFile_size() {
		return file_size;
	}

	public void setFile_size(Long file_size) {
		this.file_size = file_size;
	}

	public String getFile_url() {
		return file_url;
	}

	public void setFile_url(String file_url) {
		this.file_url = file_url;
	}

	public Boolean getIs_shown_in_index() {
		return is_shown_in_index;
	}

	public void setIs_shown_in_index(Boolean is_shown_in_index) {
		this.is_shown_in_index = is_shown_in_index;
	}

	public String getPreview_url() {
		return preview_url;
	}

	public void setPreview_url(String preview_url) {
		this.preview_url = preview_url;
	}

	public Long getPreview_width() {
		return preview_width;
	}

	public void setPreview_width(Long preview_width) {
		this.preview_width = preview_width;
	}

	public Long getPreview_height() {
		return preview_height;
	}

	public void setPreview_height(Long preview_height) {
		this.preview_height = preview_height;
	}

	public Long getActual_preview_width() {
		return actual_preview_width;
	}

	public void setActual_preview_width(Long actual_preview_width) {
		this.actual_preview_width = actual_preview_width;
	}

	public Long getActual_preview_height() {
		return actual_preview_height;
	}

	public void setActual_preview_height(Long actual_preview_height) {
		this.actual_preview_height = actual_preview_height;
	}

	public String getSample_url() {
		return sample_url;
	}

	public void setSample_url(String sample_url) {
		this.sample_url = sample_url;
	}

	public Long getSample_width() {
		return sample_width;
	}

	public void setSample_width(Long sample_width) {
		this.sample_width = sample_width;
	}

	public Long getSample_height() {
		return sample_height;
	}

	public void setSample_height(Long sample_height) {
		this.sample_height = sample_height;
	}

	public Long getSample_file_size() {
		return sample_file_size;
	}

	public void setSample_file_size(Long sample_file_size) {
		this.sample_file_size = sample_file_size;
	}

	public String getJpeg_url() {
		return jpeg_url;
	}

	public void setJpeg_url(String jpeg_url) {
		this.jpeg_url = jpeg_url;
	}

	public Long getJpeg_width() {
		return jpeg_width;
	}

	public void setJpeg_width(Long jpeg_width) {
		this.jpeg_width = jpeg_width;
	}

	public Long getJpeg_height() {
		return jpeg_height;
	}

	public void setJpeg_height(Long jpeg_height) {
		this.jpeg_height = jpeg_height;
	}

	public Long getJpeg_file_size() {
		return jpeg_file_size;
	}

	public void setJpeg_file_size(Long jpeg_file_size) {
		this.jpeg_file_size = jpeg_file_size;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public Boolean getHas_children() {
		return has_children;
	}

	public void setHas_children(Boolean has_children) {
		this.has_children = has_children;
	}

	public Long getParent_id() {
		return parent_id;
	}

	public void setParent_id(Long parent_id) {
		this.parent_id = parent_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getWidth() {
		return width;
	}

	public void setWidth(Long width) {
		this.width = width;
	}

	public Long getHeight() {
		return height;
	}

	public void setHeight(Long height) {
		this.height = height;
	}

	public Boolean getIs_held() {
		return is_held;
	}

	public void setIs_held(Boolean is_held) {
		this.is_held = is_held;
	}

	public String getFrames_pending_string() {
		return frames_pending_string;
	}

	public void setFrames_pending_string(String frames_pending_string) {
		this.frames_pending_string = frames_pending_string;
	}

	public String[] getFrames_pending() {
		return frames_pending;
	}

	public void setFrames_pending(String[] frames_pending) {
		this.frames_pending = frames_pending;
	}

	public String getFrames_string() {
		return frames_string;
	}

	public void setFrames_string(String frames_string) {
		this.frames_string = frames_string;
	}

	public String[] getFrames() {
		return frames;
	}

	public void setFrames(String[] frames) {
		this.frames = frames;
	}

	public String getFlag_detail() {
		return flag_detail;
	}

	public void setFlag_detail(String flag_detail) {
		this.flag_detail = flag_detail;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", tags=" + tags + ", tagsArr=" + Arrays.toString(tagsArr) + ", created_at="
				+ created_at + ", creator_id=" + creator_id + ", author=" + author + ", change=" + change + ", source="
				+ source + ", score=" + score + ", md5=" + md5 + ", file_size=" + file_size + ", file_url=" + file_url
				+ ", is_shown_in_index=" + is_shown_in_index + ", preview_url=" + preview_url + ", preview_width="
				+ preview_width + ", preview_height=" + preview_height + ", actual_preview_width="
				+ actual_preview_width + ", actual_preview_height=" + actual_preview_height + ", sample_url="
				+ sample_url + ", sample_width=" + sample_width + ", sample_height=" + sample_height
				+ ", sample_file_size=" + sample_file_size + ", jpeg_url=" + jpeg_url + ", jpeg_width=" + jpeg_width
				+ ", jpeg_height=" + jpeg_height + ", jpeg_file_size=" + jpeg_file_size + ", rating=" + rating
				+ ", has_children=" + has_children + ", parent_id=" + parent_id + ", status=" + status + ", width="
				+ width + ", height=" + height + ", is_held=" + is_held + ", frames_pending_string="
				+ frames_pending_string + ", frames_pending=" + Arrays.toString(frames_pending) + ", frames_string="
				+ frames_string + ", frames=" + Arrays.toString(frames) + ", flag_detail=" + flag_detail + "]";
	}
}

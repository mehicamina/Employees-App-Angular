package ba.com.zira.stc.test_project.api.model;

import java.io.Serializable;

public class EmployeeProjectTree implements Serializable {
	 /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String key;
    private String id;
    private String name;
    private String title;
    private String parent;
    private String source;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String image) {
		this.source = image;
	}
}

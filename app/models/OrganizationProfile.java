package models;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.bson.types.ObjectId;

@Entity("organizations")
public class OrganizationProfile {

	
	
    //private ObjectId id;
	@Id
	private ObjectId id;
	private String name;
	private String address;
	private String website;
	private String about;
	public OrganizationProfile(){
		
	}
	/*public OrganizationProfile(String id, String name, String address,
			String website, String about) {
		
		this.id = id;
		this.name = name;
		this.address = address;
		this.website = website;
		this.about = about;
	}*/
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	
	
	
}

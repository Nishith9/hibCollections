package org.collections;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity 
/**@Entity (name="USER_DATA") creates a table with name USER_DATA instead of 
class-name UserData  **/
@Table (name="USER_DETAILS")
public class UserDetails {	
	@Id
	@GeneratedValue
	private int userId;
	private String userName;
	@ElementCollection
	
	/* The JoinTable annotation formats the collection name to USER_ADDRESS;
	 * joinColumns is the property of JoinTable annotation which can take the JoinColumn annotation to format columns	
	 * Hibernate Annotations:
	 * CollectionId is a hibernate annotation which generates primary key for the collection defined
	 * The attributes of collectionId are used to format the collection 'listOfAddresses'
	 * The GenericGenerator annotation is defined to help the collectionId annotation to generate the primary key
	 * sequence-gen is a type of generator
	 */
	@JoinTable (name = "USER_ADDRESS", joinColumns = @JoinColumn(name = "USER_ID"))
	@GenericGenerator(name = "sequence-gen", strategy = "sequence")
	@CollectionId(columns = { @Column(name = "AddressId") }, generator = "sequence-gen", type = @Type(type="long"))
	private Collection<Address> listOfAddresses = new ArrayList<Address>();	
	
	public Collection<Address> getListOfAddresses() {
		return listOfAddresses;
	}
	public void setListOfAddresses(Collection<Address> listOfAddresses) {
		this.listOfAddresses = listOfAddresses;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}

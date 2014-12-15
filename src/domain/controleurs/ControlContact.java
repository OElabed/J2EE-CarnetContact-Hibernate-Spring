package domain.controleurs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import domain.modeles.metier.Contact;
import domain.modeles.metier.ContactGroup;
import domain.modeles.metier.PhoneNumber;
import domain.modeles.metier.PhoneNumberType;
import domain.modeles.service.ContactGroupService;
import domain.modeles.service.ContactService;

/**
 * Un formulaire comportant des champs pour modifier toutes les valeurs associées à un contact.
 * 
 * @see Contact
 */
@ManagedBean
public class ControlContact implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * L'objet permettant l'accès à la couche service
	 */
	@ManagedProperty(value = "#{ContactService}")
	private ContactService service;

	/**
	 * L'objet permettant l'accès à la couche service pour les groupes de
	 * contacts
	 */
	@ManagedProperty(value = "#{ContactGroupService}")
	private ContactGroupService groupService;
	
	/**
	 * Le message d'erreur affiché
	 */
	private String error;
	
	/**
	 * Le prénom du contact
	 */
	private String firstName;
	
	/**
	 * Le nom de famille du contact
	 */
	private String lastName;
	
	/**
	 * L'adresse email du contact
	 */
	private String email;
	
	/**
	 * Le nom de la rue du contact
	 */
	private String street;
	
	/**
	 * La ville du contact
	 */
	private String city;
	
	/**
	 * Le code postal du contact
	 */
	private String zip;
	
	/**
	 * Le pays du contact
	 */
	private String country;

	/**
	 * Le numéro Siret du contact
	 */
	private Long numSiret;
	
	/**
	 * La liste des groupes de contacts existants
	 */
	private List<ContactGroup> groupList;
	
	/**
	 * Les groupes auxquels appartient le contact
	 */
	private Map<ContactGroup, Boolean> groups;
	
	/**
	 * Les numéros de téléphone
	 */
	private List<PhoneNumber> phones;
	
	/**
	 * Les types de numéro de téléphone existants
	 */
	private List<PhoneNumberType> phoneTypes;
	
	/**
	 * Constructeur vide
	 */
	public ControlContact() {
		groupList = new ArrayList<>();
		groups = new HashMap<>();	
		phoneTypes = new ArrayList<>();
		for (PhoneNumberType type: PhoneNumberType.values()) {
			phoneTypes.add(type);
		}	
		phones = new ArrayList<>();
	}
	
	/**
	 * Vérifie les champs du formulaire et affiche un message d'erreur le cas échéant
	 * 
	 * @return	true si le contenu du formulaire est valide, false sinon
	 */
	protected boolean validate() {
		setError("");
		for (int i = 0; i < getPhones().size(); i++) {
			PhoneNumber phone = getPhones().get(i);
			if (phone.getPhoneNumber().equals("")) {
				getPhones().remove(i);
				i--;
			}
		}
		/* Téléphone: au moins un */
		if (getPhones().isEmpty()) {
			setError("Il faut renseigner au moins un numéro de téléphone");
			return false;
		}
		return true;
	}
	
	/**
	 * Ajoute un numéro de téléphone
	 */
	public void addPhone() {
		getPhones().add(new PhoneNumber());
	}

	/**
	 * @return	le message d'erreur actuellement affichél
	 */
	public String getError() {
		return error;
	}

	/**
	 * Met à jour le message d'erreur si l'utilisateur fait une erreur de saisie
	 * 
	 * @param error
	 * 		le nouveau message d'erreur
	 */
	public void setError(String error) {
		this.error = error;
	}
	
	/**
	 * @return	le prénom du contact
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Modifie le prénom du contact
	 * 
	 * @param firstName
	 * 		le nouveau prénom
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return	le nom de famille du contact
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Modifie le nom de famille du contact
	 * 
	 * @param lastName
	 * 		le nouveau nom de famille
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return	l'adresse email du contact
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Modifie l'adresse email du contact
	 * 
	 * @param email
	 * 		la nouvelle adresse email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return la rue du contact
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * Modifie la rue du contact
	 * 
	 * @param street
	 * 		la nouvelle rue
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return	la ville du contact
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Modifie la ville du contact
	 * 
	 * @param city
	 * 		la nouvelle ville
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return	le code postal du contact
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * Modifie le code postal du contact
	 * 
	 * @param zip
	 * 		le nouveau code postal
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * @return	le pays du contact
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Modifie le pays du contact
	 * 
	 * @param country
	 * 		le nouveau pays
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return	la liste des groupes de contacts
	 */
	public List<ContactGroup> getGroupList() {
		return groupList;
	}

	/**
	 * Modifie la liste des groupes de contacts
	 * 
	 * @param groupList
	 * 		la nouvelle liste
	 */
	public void setGroupList(List<ContactGroup> groupList) {
		this.groupList = groupList;
	}

	/**
	 * @return	les groupes de contacts auquel le contact appartient
	 */
	public Map<ContactGroup, Boolean> getGroups() {
		return groups;
	}

	/**
	 * Modifie les groupes du contact
	 * 
	 * @param groups
	 * 		les nouveaux groupes du contact
	 */
	public void setGroups(Map<ContactGroup, Boolean> groups) {
		this.groups = groups;
	}

	/**
	 * @return	le numéro siret du contact, si c'est une entreprise
	 */
	public Long getNumSiret() {
		return numSiret;
	}

	/**
	 * Modifie le numéro siret du contact
	 * 
	 * @param numSiret
	 * 		le nouveau siret
	 */
	public void setNumSiret(Long numSiret) {
		this.numSiret = numSiret;
	}

	/**
	 * @return	la couche service
	 */
	public ContactService getService() {
		return service;
	}

	/**
	 * Fournit la couche service à utiliser
	 * 
	 * @param service
	 * 		l'objet de la couche service à utiliser
	 */
	public void setService(ContactService service) {
		this.service = service;
	}

	/**
	 * @return	la couche service pour les groupes de contact
	 */
	public ContactGroupService getGroupService() {
		return groupService;
	}

	/**
	 * Fournit la couche service à utiliser pour les groupes de contact
	 * 
	 * @param service
	 * 		l'objet de la couche service à utiliser
	 */
	public void setGroupService(ContactGroupService groupService) {
		this.groupService = groupService;
	}

	/**
	 * @return	les numéros de téléphone du contact
	 */
	public List<PhoneNumber> getPhones() {
		return phones;
	}

	/**
	 * Modifie la liste des téléphones du contact
	 * 
	 * @param phones
	 * 		la nouvelle liste de numéros
	 */
	public void setPhones(List<PhoneNumber> phones) {
		this.phones = phones;
	}

	/**
	 * @return	la liste des types de téléphone supportés par l'appli
	 */
	public List<PhoneNumberType> getPhoneTypes() {
		return phoneTypes;
	}

	/**
	 * Modifie la liste des types de téléphones supportés
	 * 
	 * @param phoneTypes
	 * 		la nouvelle liste de types
	 */
	public void setPhoneTypes(List<PhoneNumberType> phoneTypes) {
		this.phoneTypes = phoneTypes;
	}

}

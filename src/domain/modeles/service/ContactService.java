package domain.modeles.service;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import util.SpringUtil;
import domain.modeles.metier.Contact;
import domain.modeles.metier.ContactGroup;
import domain.modeles.metier.Entreprise;
import domain.modeles.metier.PhoneNumber;
import domain.modeles.metier.PhoneNumberType;
import domain.modeles.persistance.DAOContact;

/**
 * Le répertoire de contacts.
 * 
 * @see Contact
 *
 */
public class ContactService implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Spring application context
	 */
	private transient ApplicationContext context;
	
	/**
	 * La couche persistance pour les contacts
	 */
	private transient DAOContact dao;

	/**
	 * Constructeur vide
	 */
	public ContactService() {
		context = new ClassPathXmlApplicationContext(SpringUtil.CONTEXT_PATH);
		dao = (DAOContact) context.getBean("DAOContact");
	}

	/**
	 * Ajoute un nouveau contact au répertoire.
	 * 
	 * @param firstName
	 *            	le prénom du contact
	 * @param lastName
	 *            	le nom de famille du contact
	 * @param email
	 *            	l'adresse email du contact
	 * @param street
	 *           	la rue du contact
	 * @param city
	 *            	la ville du contact
	 * @param zip
	 *            	le code postal du contact
	 * @param country
	 *            	le pays du contact
	 * @param phones
	 * 				les numéros de téléphones du contact
	 * @param groups
	 * 				les groupes auxquels appartient le contact
	 * @return 
	 * 				l'objet contact obtenu
	 */
	public Contact addContact(String firstName, String lastName, String email,
			String street, String city, String zip, String country,
			List<PhoneNumber> phones, Map<ContactGroup, Boolean> groups) {
		
		Contact contact = createContact("Contact", firstName, lastName, email,
				street, city, zip, country, phones, groups);

		/* Enregistrement dao */
		dao.addContact(contact);

		return contact;
	}
	
	/**
	 * Ajoute une nouvelle entreprise au répertoire.
	 * 
	 * @param firstName
	 *            	le prénom du contact
	 * @param lastName
	 *            	le nom de famille du contact
	 * @param email
	 *            	l'adresse email du contact
	 * @param street
	 *           	la rue du contact
	 * @param city
	 *            	la ville du contact
	 * @param zip
	 *            	le code postal du contact
	 * @param country
	 *            	le pays du contact
	 * @param phones
	 * 				les numéros de téléphones du contact
	 * @param groups
	 * 				les groupes auxquels appartient le contact
	 * @param entreprise_siret
	 * 				le numéro siret de l'entreprise
	 * @return 
	 * 				l'objet contact obtenu
	 */
	public Contact addEntreprise(String firstName, String lastName,
			String email, String street, String city, String zip,
			String country, List<PhoneNumber> phones, Map<ContactGroup, Boolean> groups,
			long entreprise_siret) {

		Entreprise contact = (Entreprise) createContact("Entreprise",
				firstName, lastName, email, street, city, zip, country,
				phones, groups);
		contact.setNumSiret(entreprise_siret);

		/* Enregistrement dao */
		dao.addContact(contact);

		return contact;
	}

	/**
	 * Supprime un contact du répertoire.
	 * 
	 * @param idContact
	 *            l'identifiant numérique du contact à supprimer
	 */
	public void deleteContact(long idContact) {
		dao.deleteContact(idContact);
	}

	/**
	 * Modifie les coordonnées d'un contact existant dans le répertoire.
	 * 
	 * @param contact
	 * 				le contact préexistant dans le répertoire
	 * @param firstName
	 *            	le nouveau prénom du contact
	 * @param lastName
	 *            	le nouveau nom de famille du contact
	 * @param email
	 *            	la nouvelle adresse email du contact
	 * @param street
	 *           	la nouvelle rue du contact
	 * @param city
	 *            	la nouvelle ville du contact
	 * @param zip
	 *            	le nouveau code postal du contact
	 * @param country
	 *            	le nouveau pays du contact
	 * @param phones
	 * 				les numéros de téléphones du contact
	 * @param groups
	 * 				les nouveaux groupes auxquels appartient le contact
	 * @param numSiret
	 * 				le nouveau numéro siret de l'entreprise
	 * @retrun 
	 * 				true si la modification s'est bien passée, false sinon
	 */
	public boolean modifyContact(Contact contact, String firstName, String lastName,
			String email, String street, String city, String zip,
			String country, List<PhoneNumber> phones, Map<ContactGroup, Boolean> groups, long numSiret) {

		updateContact(contact, firstName, lastName, email, street, city, zip,
				country, phones, groups);
		if (contact instanceof Entreprise) {
			Entreprise ent = (Entreprise) contact;
			ent.setNumSiret(numSiret);
		}

		/* Enregistrement dao */
		try {
			dao.modifyContact(contact);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Récupère un contact dans le répertoire à partir de son id.
	 * 
	 * @param id
	 *          l'id du contact recherché
	 * @return 
	 * 			le contact recherché, ou null s'il n'existe pas
	 */
	public Contact getContact(long id) {
		return dao.getContact(id);
	}

	/**
	 * Récupère dans le répertoire la liste des contacts portant le prénom
	 * fourni.
	 * 
	 * @param firstname
	 *       	le prénom recherché
	 * @return 
	 * 			tous les contacts portant le prénom firstName
	 */
	public List<Contact> getContactByFirstName(String firstname) {
		return dao.getContactByFirstName(firstname);
	}

	/**
	 * Récupère dans le répertoire la liste des contacts portant le nom de
	 * famille fourni.
	 * 
	 * @param lastname
	 *         	le nom de famille recherché
	 * @return 
	 * 			tous les contacts portant le nom de famille lastName
	 */
	public List<Contact> getContactByLastName(String lastname) {
		return dao.getContactByLastName(lastname);
	}

	/**
	 * Récupère dans le répertoire la liste des contacts utilisant l'email
	 * fourni.
	 * 
	 * @param email
	 *         	l'adresse email recherchée
	 * @return 
	 * 			tous les contacts utilisant cet email
	 */
	public List<Contact> getContactByEmail(String email) {
		return dao.getContactByEmail(email);
	}

	/**
	 * Récupère tous le répertoire de contacts
	 * 
	 * @return 
	 * 			tous les contacts du répertoire
	 */

	public List<Contact> getContactAll() {
		List<Contact> result = dao.getAllContacts();
		
		/* Ajout des contacts par défaut à la première lecture d'une base vide */
		if (result.isEmpty()) {
			Properties properties = new Properties();
			try {
				properties.load(getClass().getResourceAsStream("contacts.properties"));
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			int i = 1;
			String firstName;
			while ((firstName = properties.getProperty("firstName" + i)) != null) {
				PhoneNumber phone = (PhoneNumber) context.getBean("PhoneNumber");
				phone.setPhoneKind(PhoneNumberType.values()[0].toString());
				phone.setPhoneNumber(properties.getProperty("phone" + i));
				List<PhoneNumber> phones = new ArrayList<PhoneNumber>();
				phones.add(phone);
				addContact(firstName, properties.getProperty("lastName" + i), properties.getProperty("email" + i), 
						properties.getProperty("street" + i), properties.getProperty("city" + i), 
						properties.getProperty("zip" + i), properties.getProperty("country" + i), 
						phones, new HashMap<ContactGroup, Boolean>());
				i++;
			}
			result = dao.getAllContacts();
		}
		return result;
	}

	/**
	 * Crée un nouveau contact à partir des données fournies
	 * 
	 * @param bean
	 * 				le nom du bean à utiliser pour l'instanciation du contact
	 * @param firstName
	 *            	le prénom du contact
	 * @param lastName
	 *            	le nom de famille du contact
	 * @param email
	 *            	l'adresse email du contact
	 * @param street
	 *           	la rue du contact
	 * @param city
	 *            	la ville du contact
	 * @param zip
	 *            	le code postal du contact
	 * @param country
	 *            	le pays du contact
	 * @param phones
	 * 				les numéros de téléphones du contact
	 * @param groups
	 * 				les groupes auxquels appartient le contact
	 * @return 
	 * 				l'objet contact obtenu
	 */
	private Contact createContact(String bean, String firstName,
			String lastName, String email, String street, String city,
			String zip, String country, List<PhoneNumber> phones, Map<ContactGroup, Boolean> groups) {
		/* Instanciation du contact */
		Contact contact = (Contact) context.getBean(bean);
		
		/* Mise à jour des valeurs du contact */
		updateContact(contact, firstName, lastName, email, street, city, zip,
				country, phones, groups);
		
		return contact;
	}

	/**
	 * Modifie les valeurs des attributs d'un contact avec les paramètres fournis
	 * 
	 * @param contact
	 * 				l'objet contact à remplir
	 * @param firstName
	 *            	le prénom du contact
	 * @param lastName
	 *            	le nom de famille du contact
	 * @param email
	 *            	l'adresse email du contact
	 * @param street
	 *           	la rue du contact
	 * @param city
	 *            	la ville du contact
	 * @param zip
	 *            	le code postal du contact
	 * @param country
	 *            	le pays du contact
	 * @param phones
	 * 				les numéros de téléphones du contact
	 * @param groups
	 * 				les groupes auxquels appartient le contact
	 * @return 
	 * 				l'objet contact obtenu
	 */
	private void updateContact(Contact contact, String firstName,
			String lastName, String email, String street, String city,
			String zip, String country, List<PhoneNumber> phones, Map<ContactGroup, Boolean> groups) {
		/* Le contact lui-même */
		contact.setFirstName(firstName);
		contact.setLastName(lastName);
		contact.setEmail(email);

		/* Adresse */
		contact.getAddress().setStreet(street);
		contact.getAddress().setCity(city);
		contact.getAddress().setZip(zip);
		contact.getAddress().setCountry(country);

		/* Téléphones: ajouts */
		for (PhoneNumber phone: phones) {
			if (!contact.getPhones().contains(phone)) {
				phone.setContact(contact);
				contact.getPhones().add(phone);
			}
		}
		/* Suppressions */
		Set<PhoneNumber> toDelete = new HashSet<>();
		for (PhoneNumber phone: contact.getPhones()) {
			if (!phones.contains(phone)) {
				toDelete.add(phone);
			}
		}
		for (PhoneNumber phone: toDelete) {
			contact.getPhones().remove(phone);
		}

		/* Groupes de contacts */
		contact.getBooks().clear();
		for (ContactGroup group : groups.keySet()) {
			if (groups.get(group)) {
				contact.getBooks().add(group);
				group.getContacts().add(contact);
			}
			if ((group.getContacts().contains(contact)) && (!contact.getBooks().contains(group))) {
				group.getContacts().remove(contact);
			}
		}
	}

}

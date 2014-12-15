package domain.modeles.persistance;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import domain.modeles.metier.Contact;
import domain.modeles.metier.ContactGroup;

public class SpringDAOContact extends HibernateDaoSupport implements DAOContact {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur vide
	 */
	public SpringDAOContact() {
	}

	@Override
	public void addContact(Contact contact) {
		getHibernateTemplate().save(contact);
	}

	@Override
	public void deleteContact(long id) {
		Contact contact = getContact(id);
		if (contact != null) {
			for (ContactGroup group: contact.getBooks()) {
				group.getContacts().remove(contact);
			}
			getHibernateTemplate().delete(contact);
		}
	}

	@Override
	public void modifyContact(Contact contact) {
		getHibernateTemplate().saveOrUpdate(contact);
	}

	@Override
	public Contact getContact(long id) {
		return (Contact) getHibernateTemplate().get(Contact.class, id);
	}

	/**
	 * Récupère la liste des contacts ayant pour valeur value pour l'attribut attribute.
	 * 
	 * @param attribute		
	 * 			l'attribut à examiner
	 * @param value
	 * 			la valeur recherchée
	 * @return
	 * 			la liste de contacts correspondante
	 */
	@SuppressWarnings("unchecked")
	private List<Contact> getContactByAttribute(String attribute, String value) {
		return (List<Contact>) getHibernateTemplate().find("from Contact contact where contact." + attribute + " LIKE '%" + value + "%'");
	}

	@Override
	public List<Contact> getContactByFirstName(String firstname) {
		return getContactByAttribute("firstName", firstname);
	}

	@Override
	public List<Contact> getContactByLastName(String lastname) {
		return getContactByAttribute("lastName", lastname);
	}

	@Override
	public List<Contact> getContactByEmail(String email) {
		return getContactByAttribute("email", email);
	}

	@SuppressWarnings("unchecked")
	public List<Contact> getAllContacts() {
		return (List<Contact>) getHibernateTemplate().find("from Contact contact");
	}

}

package domain.modeles.persistance;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import domain.modeles.metier.Contact;
import domain.modeles.metier.ContactGroup;

public class SpringDAOContactGroup extends HibernateDaoSupport implements
		DAOContactGroup {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructeur vide
	 */
	public SpringDAOContactGroup() {
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ContactGroup> getContactGroups() {
		return (List<ContactGroup>) getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(ContactGroup.class));
	}

	@Override
	public void addContactGroup(ContactGroup group) {
		getHibernateTemplate().save(group);
	}

	@Override
	public void deleteContactGroup(long id) {
		ContactGroup group = (ContactGroup) getHibernateTemplate().get(ContactGroup.class, id);
		if (group != null) {
			for (Contact contact: group.getContacts()) {
				contact.getBooks().remove(group);
			}
			getHibernateTemplate().delete(group);
		}		
	}

	@Override
	public ContactGroup getContactGroup(long id) {
		return (ContactGroup) getHibernateTemplate().get(ContactGroup.class, id);
	}

	@Override
	public void modifyGroup(ContactGroup group) {
		getHibernateTemplate().saveOrUpdate(group);
	}
}

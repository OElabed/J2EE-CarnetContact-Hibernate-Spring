package domain.modeles.metier;

import java.io.Serializable;

/**
 * Un object disposant d'un identifiant unique.
 * 
 * Deux objets instances de cette classe sont identiques si et seulement si:
 *  - leurs ids sont != 0
 *  - leurs ids sont identiques
 *  - ils sont instance de la même classe
 *
 */
public abstract class IdentifiedObject implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * L'id unique de cet objet
	 */
	private long id;
	
	/**
	 * Le numéro de version
	 */
	private long version;

	/**
	 * Constructeur initialisant l'id à 0
	 */
	protected IdentifiedObject() {
		id = 0;
	}

	/**
	 * @return 	l'identifiant numérique unique de cet objet
	 */
	public long getId() {
		return id;
	}

	/**
	 * Modifie la valeur de l'id de cet élément.
	 * 
	 * @param id	le nouvel id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return	le numéro de version de cet objet
	 */
	public long getVersion() {
		return version;
	}

	/**
	 * Définit le numéro de version de cet objet
	 * 
	 * @param version	le nouveau numéro de version
	 */
	public void setVersion(long version) {
		this.version = version;
	}

	@Override
	public boolean equals(Object obj) {
		/* Cas classiques: si l'autre objet est null, alors les deux objets sont différents.
		 * Si les deux variables pointent vers la même instance, alors les deux sont identiques.
		 */
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		/* Si l'id est à 0, cette instance n'a pas encore été persistée (les ids dans la bdd sont >= 1).
		 * Son id n'est donc pas encore un identifiant unique (toutes les nouvelles instances portent
		 * l'id 0, alors qu'après persistance elles auront des ids différents).
		 * Dans ce cas on part du principe que chaque instance avec id 0 est unique => donc return false.
		 * Note: cette clause n'est pas prise en compte dans le hashCode, des appels à equals sont donc nécessaires
		 * pour distinguer les instances non persistées.
		 */
		if (getId() == 0) {
			return false;
		}

		/* La seconde condition est nécessaire pour s'assurer que les deux objets ont la même classe
		 * (un Contact avec l'id 1 et un PhoneNumber avec l'id 1 ne sont pas identiques).
		 * CONSÉQUENCE: en cas d'héritage, deux objects descendant d'une classe commune avec une classe
		 * finale différente mais le même id seront considérés différents. Ex: une Entreprise avec id 1
		 * et un Contact avec id 1 sont distincts, alors qu'il s'agit de la même entrée dans la bdd.
		 * Ce n'est concrètement pas un problème parce que nous avons décidé que le choix "Contact simple
		 * ou Entreprise" se faisait uniquement à la création du Contact et n'était pas éditable ensuite.
		 * On ne peut donc pas avoir un Contact et une Entreprise avec le même id.
		 */
		if ((obj instanceof IdentifiedObject) && (getClass().equals(obj.getClass()))) {
			IdentifiedObject idobj = (IdentifiedObject) obj;
			
			/* Finalement si rien d'autre n'a départagé les deux objets, on s'en remet à l'id. */
			return idobj.getId() == getId();
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int result = 23;
		int multiplier = 3;
		result += multiplier * getClass().hashCode();
		result += multiplier * (int) getId();
		return result;
	}
}

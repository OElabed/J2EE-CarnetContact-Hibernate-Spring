package domain.modeles.metier;

import java.util.Set;

/**
 * Un classe métier représentant une entreprise.
 *
 */
public class Entreprise extends Contact {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Le numéro SIRET de l'entreprise
	 */
	private long numSiret;
	
	/**
	 * Constructeur vide.
	 */
	public Entreprise() {
	}
	
	/**
	 * Constructeur vide.
	 */
	public Entreprise(Address address, Set<PhoneNumber> phones, Set<ContactGroup> books) {
		super(address, phones, books);
	}

	/**
	 * Retourne le numéro SIRET de l'entreprise.
	 * 
	 * @return	le numéro SIRET
	 */
	public long getNumSiret() {
		return numSiret;
	}

	/**
	 * Modifie le numéro SIRET de l'entreprise.
	 * 
	 * @param numSiret
	 * 		le nouveau numéro SIRET
	 */
	public void setNumSiret(long numSiret) {
		this.numSiret = numSiret;
	}
}

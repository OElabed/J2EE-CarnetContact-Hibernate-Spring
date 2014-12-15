package domain.modeles.metier;

/**
 * Classe métier représentant l'adresse d'un contact.
 *
 */
public class Address extends IdentifiedObject {
	private static final long serialVersionUID = 1L;	
	/**
	 * Le nom de la rue.
	 */
	private String street;
	
	/**
	 * La nom de la ville.
	 */
	private String city;
	
	/**
	 * Le code postal.
	 */
	private String zip;
	
	/**
	 * Le pays.
	 */
	private String country;
	
	/**
	 * Constructeur vide.
	 */
	public Address() {
	}

	/**
	 * Retourne le nom de la rue de cette adresse.
	 * 
	 * @return	le nom de la rue
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * Modifie le nom de rue enregistré pour cette adresse.
	 * 
	 * @param street
	 * 		le nom de la nouvelle rue
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * Retourne le nom de ville enregistré pour cette adresse.
	 * 
	 * @return	le nom de la ville
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Modifie la ville enregistrée pour cette adresse.
	 * 
	 * @param city
	 * 		le nom de la nouvelle ville
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Retourne le code postal de cette adresse.
	 * 
	 * @return	le code postal
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * Modifie le code postal actuellement enregistré pour cette adresse.
	 * 
	 * @param zip
	 * 		le nouveau code postal
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * Retourne le nom du pays de cette adresse.
	 * 
	 * @return	le nom du pays
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Modifie le pays enregistré pour cette adresse.
	 * 
	 * @param country
	 * 		le nouveau pays
	 */
	public void setCountry(String country) {
		this.country = country;
	}
}

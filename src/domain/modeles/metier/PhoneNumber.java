package domain.modeles.metier;

/**
 * Une classe métier représentant un numéro de téléphone.
 *
 */
public class PhoneNumber extends IdentifiedObject {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Le type de téléphone (mobile, domicile, etc)
	 */
	private String phoneKind;
	
	/**
	 * Le numéro de téléphone lui-même
	 */
	private String phoneNumber;
	
	/**
	 * Le contact auquel appartient ce téléphone
	 */
	private Contact contact;
	
	public PhoneNumber() {
		phoneNumber = "";
		phoneKind = PhoneNumberType.values()[0].toString();
	}

	/**
	 * Retourne le type de téléphone associé à ce numéro.
	 * 
	 * @return	le type de téléphone
	 */
	public String getPhoneKind() {
		return phoneKind;
	}

	/**
	 * Modifie le type de téléphone enregistré pour ce numéro.
	 * 
	 * @param phoneKind
	 * 		le nouveau type de téléphone
	 */
	public void setPhoneKind(String phoneKind) {
		this.phoneKind = phoneKind;
	}

	/**
	 * Retourne le numéro de téléphone.
	 * 
	 * @return	le numéro de téléphone
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Modifie le numéro de téléphone.
	 * 
	 * @param phoneNumber
	 * 		le nouveau numéro
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Retourne le contact associé à ce numéro.
	 * 
	 * @return	le contact du numéro
	 */
	public Contact getContact() {
		return contact;
	}

	/**
	 * Modifie le contact associé à ce numéro.
	 * 
	 * @param contact
	 * 		le nouveau contact
	 */
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	
	@Override
	public String toString() {
		return getPhoneNumber();
	}
}

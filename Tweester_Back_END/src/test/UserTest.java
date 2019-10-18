package test;

public class UserTest {

	private String nom, prenom, login, password, mail, telephone,key;
	private char sexe;

	public UserTest(String nom, String prenom, String login, String password, String mail, String telephone,
			char sexe) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
		this.password = password;
		this.mail = mail;
		this.telephone = telephone;
		this.sexe = sexe;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "UserTest [nom=" + nom + ", prenom=" + prenom + ", login=" + login + ", password=" + password + ", mail="
				+ mail + ", telephone=" + telephone + ", sexe=" + sexe +" key="+key+ "]";
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public char getSexe() {
		return sexe;
	}

	public void setSexe(char sexe) {
		this.sexe = sexe;
	}

}

package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "parfumsFemme")
public class ParfumsFemme {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "nom_produit" )
	private String nomProduit;
	
	@Column(name = "description" )
	private String description;
	
	@Column(name = "prix" )
	private int prix;
	
	@Column(name = "quantite" )
	private int quantite;
	
	@Lob
	@Column(columnDefinition = "MEDIUMBLOB" )
	private String image; 
	
	public ParfumsFemme() {
		
	}
	public ParfumsFemme(String nomProduit, String description, int prix, int quantite, String image  ) {
		super();
		this.nomProduit = nomProduit;
		this.description = description;
		this.prix = prix;
		this.quantite = quantite;
		this.image = image;	
		}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNomProduit() {
		return nomProduit;
	}
	public void setNomProduit(String nomProduit) {
		this.nomProduit = nomProduit;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPrix() {
		return prix;
	}
	public void setPrix(int prix) {
		this.prix = prix;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

}

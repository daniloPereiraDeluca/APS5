package br.com.APS.chat;

import java.io.Serializable;
import java.util.ResourceBundle;

public class BundleUtil implements Serializable{

	private static final long serialVersionUID = -3854082983244960369L;

	private String resourceBundle;
	
	public BundleUtil(String resource){
		this.resourceBundle = resource;
		
	}
	public String getMessage(String key){
		return getBundle().getString(key);
	}
	
	public ResourceBundle getBundle(){
		return ResourceBundle.getBundle(resourceBundle);
	}
}

package reasoning;

import database.model.Resource;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Policy.java
//  @ Date : 4/17/2014
//  @ Author : 
//
//




/** */
public abstract class Policy{
	/** 
	 * Score associated with the violation of the policy
	 * */
	private float weigth;
	
	/**
	 * Parameters passed from Abstract factory
	 * */
	public Policy(String[] param)
	{
		
		
	}
	
	/** */
	public boolean evaluate(Resource r)
	{
		return false;
	
	}
	
	public abstract float computeQoSViolation(Resource r, Resource reference);

	public float getWeigth() {
		return weigth;
	}

	public void setWeigth(float weigth) {
		this.weigth = weigth;
	}
	
}
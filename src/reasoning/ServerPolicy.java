package reasoning;

import database.model.Resource;
import database.model.Server;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : ServerPolicy.java
//  @ Date : 4/17/2014
//  @ Author : 
//
//




/** */
public class ServerPolicy extends Policy
{
	public ServerPolicy(String[] param) {
		super(param);
		// TODO Auto-generated constructor stub
		//instantiate local fields with param values
	}

	/** */
	public int cpuMin;
	
	/** */
	public int cpuMax;
	
	/** */
	public int ramMin;
	
	/** */
	public int ramMax;

	
	/**
	 * The violation degree of a low level GPI/KPI policy representing the server
	 *  load values is calculated in a similar manner, the only difference is given
	 *   by that fact that in this case the server�s optimal computing resources
	 *    load values and the server�s actual load values are represented in the
	 *     Euclidean space. 
	 * */
	@Override
	public float computeQoSViolation(Resource r, Resource reference) {
		Server optimalLoad = (Server) reference;
		Server server = (Server) r;
		double absRam = Math.abs(optimalLoad.getRAM().getCapacity() - server.getRAM().getCapacity());
		double absCPU = Math.abs(optimalLoad.getCPU().getAllCPU() - server.getCPU().getAllCPU());
		double absHDD = Math.abs(optimalLoad.getHDD().getCapacity() - server.getHDD().getCapacity());
		return (float) Math.sqrt(Math.pow(absRam, 2) + Math.pow(absCPU,2) + Math.pow(absHDD, 2));
	
	}
}
package com.kiwilandtrains.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Desc: Method to represent City as a vertex in the graph 
 * 
 * @author zekigu
 *
 */
public class CityVertex{

	private String mName;
	private ArrayList<CityVertex> mAdjacentCities;
	
	CityVertex(String name) {
		mName = name;
		mAdjacentCities = new ArrayList<CityVertex>();
	}

	public String getName() {
		return mName;
	}

	public void setName(String mName) {
		this.mName = mName;
	}

	public ArrayList<CityVertex> getAdjacentCities() {
		return mAdjacentCities;
	}

	public void setAdjacentCities(ArrayList<CityVertex> mAdjacentCities) {
		this.mAdjacentCities = mAdjacentCities;
	}
	
	
}

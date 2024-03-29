package com.kiwilandtrains.model;

/**
 * 
 * Desc: Method to represent railroad bw two cities as an edge in the graph 
 * 
 * @author zekigu
 *
 */
public class RouteEdge {

	private int mDistance;
	private CityVertex mSrcVertex;
	private CityVertex mDestVertex;
	
	RouteEdge(int distance, CityVertex src, CityVertex dest) {
		mDistance = distance;
		mSrcVertex = src;
		mDestVertex = dest;
	}

	public CityVertex getDestVertex() {
		return mDestVertex;
	}

	public void setDestVertex(CityVertex mDestVertex) {
		this.mDestVertex = mDestVertex;
	}

	public CityVertex getSrcVertex() {
		return mSrcVertex;
	}

	public void setSrcVertex(CityVertex mSrcVertex) {
		this.mSrcVertex = mSrcVertex;
	}

	public int getDistance() {
		return mDistance;
	}

	public void setDistance(int mDistance) {
		this.mDistance = mDistance;
	}
}

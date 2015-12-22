package com.kiwilandtrains.test.vertexAndedge;

import com.kiwilandtrains.manager.CityGraphManager;

import junit.framework.TestCase;

public class TestVertexAndEdge extends TestCase {

	public void testAdjacentCities() {
		String graphData =  "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
		CityGraphManager.getInstance().initGraph(graphData);
		
		assertEquals("Witha given path " + graphData + ", A has 3 adjacent cities: " , 
						true, 
						CityGraphManager.getInstance().getCityGraph().getVertex("A", false).getAdjacentCities().size() == 3);
		assertEquals("Witha given path " + graphData + ", C has 3 adjacent cities: " , 
				true, 
				CityGraphManager.getInstance().getCityGraph().getVertex("C", false).getAdjacentCities().size() == 2);
	}
	
	public void testEdgeVertices() {
		String graphData =  "AB5, BC4";
		CityGraphManager.getInstance().initGraph(graphData);
		
		assertEquals("Witha given path " + graphData + ", AB edge has A as a src & B as a dest: " , 
				true, 
				CityGraphManager.getInstance().getCityGraph().getEdgeBetween("A", "B") != null);
		assertEquals("Witha given path " + graphData + ", there's no such edge DE " , 
				false, 
				CityGraphManager.getInstance().getCityGraph().getEdgeBetween("D", "E") != null);

	}
}

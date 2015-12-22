package com.kiwilandtrains.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

import javax.print.attribute.HashAttributeSet;

import com.kiwilandtrains.manager.CityGraphManager;

/**
 * 
 * Desc: This class includes methods mostly used to test the given graph against the given input set
 * 
 * 
 * @author zekigu
 *
 */
public class GraphUtils {

	/**
	 * 
	 * Desc: Method to get distance bw two paths given in the form of A-B-C
	 * 
	 * @param route
	 * @return
	 */
	public static int calculateDistanceOfRoute(String route) {
		
		// split cities into an array
		String[] cityList = route.split("-");
		
		int totalLength = 0;
		for(int i=0; i<cityList.length; i++) {
			if(i != (cityList.length -1)) {
				String src = cityList[i];
				String dest = cityList[i+1];
				
				// if edge found for this particulat src & dest
				RouteEdge e = null;
				if((e = CityGraphManager.getInstance().getCityGraph().getEdgeBetween(src, dest)) != null) {
					totalLength += e.getDistance();
				} else {
					totalLength = 0;
				}
			}
		}
		
		return totalLength;
	}
	
	/**
	 * 
	 * Desc: Method to get distance bw two paths given in the form of ABC
	 * 
	 * @param route
	 * @return
	 */
	public static int calculateDistanceOfRouteVersion2(String route) {
				
		int totalLength = 0;
		for(int i=0; i<route.length(); i++) {
			if(i != (route.length() -1)) {
				String src = String.valueOf(route.charAt(i));
				String dest = String.valueOf(route.charAt(i+1));
				
				// if edge found for this particulat src & dest
				RouteEdge e = null;
				if((e = CityGraphManager.getInstance().getCityGraph().getEdgeBetween(src, dest)) != null) {
					totalLength += e.getDistance();
				} else {
					totalLength = 0;
				}
			}
		}
		
		return totalLength;
	}
	
	/**
	 * 
	 * Desc: method to return if given route is cyclic or not. ie A-B-C-A
	 * 
	 * @param route
	 * @return boolean
	 */
	public static boolean isRouteCyclic(String route) {
		
		if(route.length() >= 2) {
			return (route.charAt(0) == route.charAt(route.length()-1));
		} else {
			return false;
		}
	}
	
	/**
	 * 
	 * Desc: Method to retreive length of a path.
	 * 
	 * @param p
	 * @return
	 */
	public static int calculateDistanceOfRoute(Path p) {
		
		int totalLength = 0;
		
		ArrayList<CityVertex> cityList = p.getNodeList();
		for(int i=0; i<cityList.size(); i++) {
			if(i != (cityList.size() -1)) {
				String src = cityList.get(i).getName();
				String dest = cityList.get(i+1).getName();
				
				// if edge found for this particulat src & dest
				RouteEdge e = null;
				if((e = CityGraphManager.getInstance().getCityGraph().getEdgeBetween(src, dest)) != null) {
					totalLength += e.getDistance();
				} else {
					System.out.println("NO SUCH ROUTE");
					return 0;
				}
			}
		}
		
		return totalLength;
	}
	
	/**
	 * 
	 * Desc: Method to check if path already exists in the given path list
	 * 
	 * @param pathList
	 * @param p
	 * @return
	 */
	public static boolean listContainsPath(ArrayList<Path> pathList, Path p) {
		
		boolean exists = false;
		
		for(int i=0; i < pathList.size(); i++) {
			if(pathList.get(i).getPathOriginalOrderAsAString().equals(p.getPathOriginalOrderAsAString())){
				exists = true;
				break;
			}
		}
		
		return exists;
	}
	
	/**
	 * 
	 * Desc: Method to get all paths to dest from pivot. The arraylist will not change the content of paths
	 * 	     thus it's save to use in another list.
	 * 
	 * @param dest
	 * @param pivot
	 * @param cityPathListMap
	 * @return
	 */
	public static ArrayList<Path> getPathListToVertexFromPivot(CityVertex dest, CityVertex pivot, Map<CityVertex, ArrayList<Path>> cityPathListMap) {
		
		// create a list of paths to dest vertex
		ArrayList<Path> pathsToVertex = new ArrayList<Path>();
		
		// iterate over the paths to find paths ending with dest vertex
		ArrayList<Path> pathListFromPivot = cityPathListMap.get(pivot);
		//System.out.println("Path count from " + pivot.getName() + " is " + pathListFromPivot.size());
		for(int i=0; i<pathListFromPivot.size(); i++) {
			
			Path p = pathListFromPivot.get(i);
			//System.out.println("Path for " + pivot.getName() + " is " + p.getPathDesc());
			if(p.getPathLastVertexInOriginalOrder().getName().equals(dest.getName())) {
				pathsToVertex.add(p);
			}
		}
		
		return pathsToVertex;
	}
	
	
	/**
	 * 
	 * Desc: Method to return path between x and y with at most number of stops -stopCount
	 * 
	 * @param src
	 * @param dest
	 * @param stopCount
	 * @return
	 */
	public static int subPathsBetweenTwoCitiesWithNumberOfStops(String src, String dest, int stopCount, boolean atMost) {
	
		// to put paths in a set without duplicating
		HashSet<String> subPathList = new HashSet<String>();
		
		// test all paths are printed correctly from pivot to all cities/vertices
		for(CityVertex cv : CityGraphManager.getInstance().getCityGraph().getSetOfVertex()) {						
			
			// path list of city vertex
			ArrayList<Path> list = CityGraphManager.getInstance().getCityGraph().getCityPathListMap().get(cv);			
			for(int i=0; i<list.size(); i++) {
								
				// get ordered path string
				String orderedPathStr = list.get(i).getPathOriginalOrderAsAString();
				String orderedPathStrCopy = list.get(i).getPathOriginalOrderAsAString();				
				
				// get indexes of src on the path
				int indexOfSrc = orderedPathStr.indexOf(src);
				while (indexOfSrc >= 0) {
				
					// for each set of vertices, compare it to all dest vertices
					int indexOfDest = orderedPathStrCopy.indexOf(dest);				    
					while (indexOfDest >= 0) {
						
						if(indexOfDest > indexOfSrc) {
							int pathLength = indexOfDest - indexOfSrc;
							
							if(atMost) {
								if(pathLength <= stopCount) {
									String subPath = orderedPathStr.substring(indexOfSrc, indexOfDest+ 1);				
									subPathList.add(subPath);
								}
							} else {
								if(pathLength == stopCount) {
									String subPath = orderedPathStr.substring(indexOfSrc, indexOfDest+ 1);
									subPathList.add(subPath);
								}
							}
						}
						
						indexOfDest = orderedPathStrCopy.indexOf(dest, indexOfDest + 1);
					}
				    
					indexOfSrc = orderedPathStr.indexOf(src, indexOfSrc + 1);
				}
								
			}
		}		
		
		return subPathList.size();
	}
	
	/**
	 * 
	 * Desc: Method to return path between x and y with at most number of stops -stopCount
	 * 
	 * @param src
	 * @param dest
	 * @return
	 */
	public static int minDistanceBetweenTwoCities(String src, String dest) {

		int shortestLength = 100000;
		
		// test all paths are printed correctly from pivot to all cities/vertices
		for(CityVertex cv : CityGraphManager.getInstance().getCityGraph().getSetOfVertex()) {						
			
			// path list of city vertex
			ArrayList<Path> list = CityGraphManager.getInstance().getCityGraph().getCityPathListMap().get(cv);			
			for(int i=0; i<list.size(); i++) {
								
				// get ordered path string
				String orderedPathStr = list.get(i).getPathOriginalOrderAsAString();
				String orderedPathStrCopy = list.get(i).getPathOriginalOrderAsAString();				
				
				// get indexes of src
				int indexOfSrc = orderedPathStr.indexOf(src);
				while (indexOfSrc >= 0) {
				
					int indexOfDest = orderedPathStrCopy.indexOf(dest);
				    
					while (indexOfDest >= 0) {
						
						if(indexOfDest > indexOfSrc) {
							int pathLength = indexOfDest - indexOfSrc;
														
							String subPath = orderedPathStr.substring(indexOfSrc, indexOfDest+ 1);				
							
							if(shortestLength > GraphUtils.calculateDistanceOfRouteVersion2(subPath)){
								shortestLength =GraphUtils.calculateDistanceOfRouteVersion2(subPath);
							}
							
						}
						
						indexOfDest = orderedPathStrCopy.indexOf(dest, indexOfDest + 1);
					}
				    
					indexOfSrc = orderedPathStr.indexOf(src, indexOfSrc + 1);
				}
								
			}
		}		
		
		return shortestLength;
		
	}
	
	/**
	 * 
	 * Desc: Method to return path between x and y
	 * 
	 * @param src
	 * @param dest
	 * @return
	 */
	public static int subPathsBetweenTwoCities(String src, String dest) {
		
		// to put paths in a set without duplicating
		HashSet<String> subPathList = new HashSet<String>();
		
		// test all paths are printed correctly from pivot to all cities/vertices
		for(CityVertex cv : CityGraphManager.getInstance().getCityGraph().getSetOfVertex()) {						
			
			// path list of city vertex
			ArrayList<Path> list = CityGraphManager.getInstance().getCityGraph().getCityPathListMap().get(cv);			
			for(int i=0; i<list.size(); i++) {
								
				// get ordered path string
				String orderedPathStr = list.get(i).getPathOriginalOrderAsAString();
				String orderedPathStrCopy = list.get(i).getPathOriginalOrderAsAString();				
				
				// get indexes of src on the path
				int indexOfSrc = orderedPathStr.indexOf(src);
				while (indexOfSrc >= 0) {
				
					// for each set of vertices, compare it to all dest vertices
					int indexOfDest = orderedPathStrCopy.indexOf(dest);				    
					while (indexOfDest >= 0) {
						
						if(indexOfDest > indexOfSrc) {
							String subPath = orderedPathStr.substring(indexOfSrc, indexOfDest+ 1);	
							int distOfPath = calculateDistanceOfRouteVersion2(subPath);
							if(distOfPath < 30) {
								subPathList.add(subPath);
							}
						}
						
						indexOfDest = orderedPathStrCopy.indexOf(dest, indexOfDest + 1);
					}
				    
					indexOfSrc = orderedPathStr.indexOf(src, indexOfSrc + 1);
				}
								
			}
		}		
		
		return subPathList.size();
	}

	
}

package bfs;

import java.util.*;

public class StationBFS {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 1번
//		int n = 6;
//		int[] passenger = {1,1,1,1,1,1};
//		int[][] train = {{1,2},{1,3},{1,4},{3,5},{3,6}};
		
		// 2번
//		int n = 4;
//		int[] passenger = {2,1,2,2};
//		int[][] train = {{1,2},{1,3},{2,4}};
//		
		
		// 3번
		int n = 5;
		int[] passenger = {1,1,2,3,4};
		int[][] train = {{1,2},{1,3},{1,4},{1,5}};
		
		int[] answer = solution(n, passenger, train);
		
		System.out.println(answer[0] + " " + answer[1]);
	}

	public static int[] solution(int n, int[] passenger, int[][] train) {
        
        boolean[] isVisited = new boolean[n+1];
        
        // vertex 초기화
        Station[] stations = new Station[n+1];
        for(int i = 0; i < n; i++) {
        	stations[i+1] = new Station(i+1, passenger[i]);
        }
        
        // edge 초기화
        for(int[] connect : train) {
        	stations[connect[0]].connect(stations[connect[1]]);
        	stations[connect[1]].connect(stations[connect[0]]);
        }
        
        bfs(isVisited, stations[1]);
        
        return getFinalStation(stations);
    }
	
	private static void bfs(boolean[] isVisited, Station startStation) {
		LinkedList<Station> queue = new LinkedList<>();
		isVisited[startStation.num] = true;
		queue.add(startStation);

		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				Station s = queue.pop();
				for (int j = 0; j < s.connects.size(); j++) {
					Station connected = s.connects.get(j);
					if (!isVisited[connected.num]) {
						isVisited[connected.num] = true;
						connected.passenger += s.passenger;
						queue.add(connected);
					}
				}
			}
		}
	}
	
	private static int[] getFinalStation(Station[] stations) {
		int max = 0;
		int idx = -1;
		for(int i = stations.length - 1; i > 0; i--) {
			if(stations[i].passenger > max) {
				max = stations[i].passenger;
				idx = stations[i].num;
			}
		}
		
		int[] answer = new int[2];
		
		answer[0] = idx;
		answer[1] = max;
		
		return answer;
	}
}

class Station {
	int num;
    int passenger;
    List<Station> connects;
    
    public Station(int num, int passenger) {
    	this.num = num;
        this.passenger = passenger;
        this.connects = new ArrayList<Station>();
    }
    
    public void connect(Station other) {
        connects.add(other);
    }

	@Override
	public String toString() {
		return "Station [num=" + num + ", passenger=" + passenger + "]";
	}
}
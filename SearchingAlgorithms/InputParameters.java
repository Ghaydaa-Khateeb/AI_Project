package SearchingAlgorithms;

public class InputParameters {
	private double streetDistance;
	private double arealDistance;
	private double travelTime;
	private double bestTravelTime;
	public InputParameters(double streetDistance, double arealDistance, double travelTime, double bestTravelTime) {
		super();
		this.streetDistance = streetDistance;
		this.arealDistance = arealDistance;
		this.travelTime = travelTime;
		this.bestTravelTime = bestTravelTime;
	}
	public double getStreetDistance() {
		return streetDistance;
	}
	public void setStreetDistance(double streetDistance) {
		this.streetDistance = streetDistance;
	}
	public double getArealDistance() {
		return arealDistance;
	}
	public void setArealDistance(double arealDistance) {
		this.arealDistance = arealDistance;
	}
	public double getTravelTime() {
		return travelTime;
	}
	public void setTravelTime(double travelTime) {
		this.travelTime = travelTime;
	}
	public double getBestTravelTime() {
		return bestTravelTime;
	}
	public void setBestTravelTime(double bestTravelTime) {
		this.bestTravelTime = bestTravelTime;
	}
	@Override
	public String toString() {
		return "InputParameters [streetDistance=" + streetDistance + ", arealDistance=" + arealDistance
				+ ", travelTime=" + travelTime + ", bestTravelTime=" + bestTravelTime + "]";
	}
	

}

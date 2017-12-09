
package cy.com.inlocatersslogger;

public class LogRecord {

	private long ts;
	private double lng;
	private double lat;
	private String bssid;
	private int rss;

	public LogRecord(long ts, double lat, double lng, String bssid, int rss) {
		super();
		this.ts = ts;
		this.lng = lng;
		this.lat = lat;
		this.bssid = bssid;
		this.rss = rss;
	}

	public String toString() {
		String str = new String();
		str = String.valueOf(ts) + " " + String.valueOf(lat) + " " + String.valueOf(lng) + " " + String.valueOf(bssid) + " " + String.valueOf(rss)
				+ "\n";
		return str;
	}

}

package in.silive.techtrishnabeta2.model;

/*
 * this class returns the values of items in the list at specific position
 */

public class ListItemClass {
	private String name;
	private String number;
	private int icon;

	public ListItemClass() {
		// default constructor
	}

	// constructor if the list has all the three required fields
	public ListItemClass(String name, String num, int icon) {
		this.name = name;
		this.number = num;
		this.icon = icon;
	}

	// constructor if the list only has name and number
	public ListItemClass(String name, String num) {
		this.name = name;
		this.number = num;
	}

	// constructor if the list has only icon and name
	public ListItemClass(String name, int icon) {
		this.name = name;
		this.icon = icon;
	}

	// method to get the name
	public String getName() {
		return this.name;
	}

	// method to get the number
	public String getNum() {
		return this.number;
	}

	// method to get the icon
	public int getIcon() {
		return this.icon;
	}
}

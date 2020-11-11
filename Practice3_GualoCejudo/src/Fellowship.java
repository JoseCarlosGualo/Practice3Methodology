/*********************************************************************
*
* Class Name: Fellowship
* Author/s name: Jose Carlos Gualo Cejudo
* Release/Creation date: 20/04/2020
* Class description: In this class we have the implementation of the Fellowship class, from which we can obtain information 
* about a fellowship, or we also can create another one
*
**********************************************************************
*/
public class Fellowship {

	private int id, start_month, finish_month;
	private double monthly_salary;

	public Fellowship() {
	}

	public Fellowship(int id, int start_month, int finish_month, double monthly_salary) {
		this.id = id;
		this.start_month = start_month;
		this.finish_month = finish_month;
		this.monthly_salary = monthly_salary;
	}

	public int getId() {
		return id;
	}

	public int get_duration() {
		return this.finish_month - this.start_month + 1;
	}

	public int getStart_month() {
		return start_month;
	}

	public int getFinish_month() {
		return finish_month;
	}

	public double getMonthly_salary() {
		return monthly_salary;
	}

	public double getTotal_salary() {
		return (monthly_salary * this.get_duration());
	}

	public String toString() {
		return "Fellowship number: " + this.id + " starts at month: " + this.start_month + " and finishes at month: "
				+ this.finish_month + " with a monthly salary of: " + this.monthly_salary + " and a total salary of: "
				+ this.getTotal_salary();
	}
}

package Application;

import java.util.ArrayList;

public class FinalAwnser
{
    private String business;
    private String similiarBusiness;
    private ArrayList<Double> array;
    private double medoid;

    public FinalAwnser()
    {

    }
    public FinalAwnser(String business, String similiarBusiness, ArrayList<Double> array, double medoid) {
        this.business = business;
        this.similiarBusiness = similiarBusiness;
        this.array = array;
        this.medoid = medoid;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getSimiliarBusiness() {
        return similiarBusiness;
    }

    public void setSimiliarBusiness(String similiarBusiness) {
        this.similiarBusiness = similiarBusiness;
    }

    public ArrayList<Double> getArray() {
        return array;
    }

    public void setArray(ArrayList<Double> array) {
        this.array = array;
    }

    public double getMedoid() {
        return medoid;
    }

    public void setMedoid(double medoid) {
        this.medoid = medoid;
    }

    @Override
    public String toString() {
        return "FinalAwnser{" +
                "business='" + business + '\'' +
                ", similiarBusiness='" + similiarBusiness + '\'' +
                ", array=" + array +
                ", medoid=" + medoid +
                '}';
    }
}

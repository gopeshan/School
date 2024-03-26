// Gopeshan Mathanachandran
// 20341531

public class Country extends Entity {
    private String capital;

    // Default constructor
    public Country() {
        this("", new Date(), "", 0.0);
    }
    
    // Parameterized constructor
    public Country(String name, Date born, String capital, double difficulty) {
        super(name, born, difficulty);
        this.capital = capital;
    }
    
    // Copy constructor
    public Country(Country country) {
        super(country);
        this.capital = country.capital;
    }

    // Getter for capital
    public String getCapital() {
        return capital;
    }

    // Setter for capital
    public void setCapital(String capital) {
        this.capital = capital;
    }

    // Return the entity type as a country
    @Override
    public String entityType() {
        return "This entity is a country!";
    }

    // Clone method to create a deep copy of the Country object
    @Override
    public Country clone() {
        return new Country(this);
    }

    // Return a string representation of the Country object
    @Override
    public String toString() {
        return super.toString() + "\nCapital: " + capital;
    }
}

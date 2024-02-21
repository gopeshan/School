public class Entity {

    private String name;
    private Date birthDate;

    public Entity(String name, Date birthDate) {
        if (isValidEntityData(name, birthDate)) {
            this.name = name;
            this.birthDate = new Date(birthDate);
        } else {
            handleInvalidEntityData();
        }
    }

    public Entity() {
        this.name = "No Name";
        this.birthDate = new Date();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDate(Date birthDate) {
        if (isValidEntityData(name, birthDate)) {
            this.birthDate.setDate(birthDate.getMonth(), birthDate.getDay(), birthDate.getYear());
        } else {
            handleInvalidEntityData();
        }
    }

    public String getName() {
        return this.name;
    }

    public Date getBirthDate() {
        return new Date(this.birthDate);
    }

    public String toString() {
        return String.format("%s, born on %s", this.name, this.birthDate.toString());
    }

    public boolean equals(Entity entity) {
        return entity != null && this.name.equals(entity.getName()) && this.birthDate.equals(entity.getBirthDate());
    }

    private boolean isValidEntityData(String name, Date birthDate) {
        return name != null && !name.isEmpty() && birthDate != null;
    }

    private void handleInvalidEntityData() {
        System.out.println("Invalid Entity Data. Major Error");
        System.exit(0);
    }
}
